package pl.damian.backend.domain.weather;

import pl.damian.backend.domain.exception.DomainException;
import pl.damian.backend.domain.weather.model.*;
import pl.damian.backend.domain.weather.model.current.Current;
import pl.damian.backend.domain.weather.model.current.CurrentWeather;
import pl.damian.backend.domain.weather.model.forecast.ForecastDay;
import pl.damian.backend.domain.weather.model.forecast.ForecastWeather;
import pl.damian.backend.domain.weather.model.history.HistoryWeather;
import pl.damian.backend.infrastructure.weather.weatherapi.WeatherInformationAPI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherService {
    private final WeatherInformation weatherInformation = new WeatherInformationAPI();

    public String checkCurrentWeather(String city) {
        CurrentWeather currentWeather = weatherInformation.getCurrentWeather(city)
                .orElseThrow(() -> new DomainException("Błąd z połączeniem."));
        return getLocationCurrentWeatherInformation(currentWeather.getLocation(), currentWeather.getCurrent());
    }

    public String checkForecastWeather(String city, int days) {
        ForecastWeather forecastWeather = weatherInformation.getForecastWeather(city, days)
                .orElseThrow(() -> new DomainException("Błąd z połączeniem."));
        return getForecastWeatherInformation(forecastWeather);
    }

    public String checkHistoryWeather(String city, String date) {
        validateDate(date);
        HistoryWeather historyWeather = weatherInformation.getHistoryWeather(city, date)
                .orElseThrow(() -> new DomainException("Błąd z połączeniem."));
        return getHistoryWeatherInformation(historyWeather);
    }

    private void validateDate(String date) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, dateFormat);
        } catch (RuntimeException e) {
            throw new DomainException("Niepoprawny format daty");
        }
    }

    private String getHistoryWeatherInformation(HistoryWeather historyWeather) {
        StringBuilder weatherInformation = new StringBuilder(getLocationWeatherInformation(
                historyWeather.getLocation()));
        for (ForecastDay day : historyWeather.getForecast().getForecastDays()) {
            weatherInformation.append(getForecastDayInformation(day));
        }
        return weatherInformation.toString();
    }

    private String getLocationCurrentWeatherInformation(Location location, Current current) {
        return String.format("%s%s", getLocationWeatherInformation(location), getCurrentWeatherInformation(current));
    }

    private String getCurrentWeatherInformation(Current current) {
        String polishWindDirection = WindDirection.valueOf(current.getDir()).getPolishWindDirection();
        String polishCodeDescription = ConditionCode.getConditionCode(
                current.getCondition().getConditionCode()).getPolishDescription();
        return String.format("\nOstania aktualizacja: %s\nTemperatura: %.2f \u2103" +
                        "\nPrędkość wiatru: %.2f km/h\nKierunek wiatru: %s\nCiśnienie atmosferyczne: %.1f hPa\n" +
                        "Wilgotność: %s \uff05\nOdczuwalna temperatura: %.1f \u2103\nWidoczność: %.1f km\n" +
                        "Podmuchy wiatru: %.1f km/h\nOpis warunku atmosferycznego: %s\n",
                current.getLastUpdate(), current.getTempC(), current.getWindKph(), polishWindDirection,
                current.getPressureMb(), current.getHumidityPerc(), current.getFeelsLikeC(), current.getVisibilityKm(),
                current.getGustWindKph(), polishCodeDescription);
    }

    private String getLocationWeatherInformation(Location location) {
        return String.format("\n  Kraj: %s\n  Miasto: %s\n  Współrzędne geograficzne: (%s,%s)\n  Strefa czasowa: %s\n  " +
                        "Czas lokalny: %s\n", location.getCountry(), location.getName(),
                location.getLat(), location.getLon(), location.getTimezone(),
                location.getLocalTime());
    }

    private String getForecastWeatherInformation(ForecastWeather forecastWeather) {
        StringBuilder weatherInformation = new StringBuilder(getLocationCurrentWeatherInformation(forecastWeather
                .getLocation(), forecastWeather.getCurrent()));
        for (ForecastDay day : forecastWeather.getForecast().getForecastDays()) {
            weatherInformation.append(getForecastDayInformation(day));
        }
        return weatherInformation.toString();
    }

    private String getForecastDayInformation(ForecastDay day) {
        StringBuilder weatherInformation = new StringBuilder("\nDzień: " + day.getDate());
        weatherInformation.append(getForecastDay_DayInformation(day.getDay()));
        for (Hour hour : day.getHour()) {
            weatherInformation.append(getForecastHourInformation(hour));
        }
        return weatherInformation.toString();
    }

    private String getForecastDay_DayInformation(Day day) {
        return String.format("\n Statystyki dnia\n  Maksymalna temperatura: %.1f \u2103\n  Minimalna temperatura: " +
                        "%.1f \u2103\n  Średnia temperatura: %.1f \u2103\n  Maksymalna prędkość wiatru: %.1f km/h\n  " +
                        "Ilość opadów: %.1f mm\n  Średnia wilgotność %.1f \uff05\n  Szansa opadów deszczu: %.0f \uff05\n" +
                        "  Szansa opadów śniegu: %.0f \uff05\n"
                , day.getMaxTemp(), day.getMinTemp(), day.getAvgTemp(), day.getMaxWind(), day.getTotalPercip(),
                day.getHumidity(), day.getDailyChanceRain(), day.getDailyChanceSnow());
    }

    private String getForecastHourInformation(Hour hour) {
        String polishWindDirection = WindDirection.valueOf(hour.getDir()).getPolishWindDirection();
        String polishCodeDescription = ConditionCode.getConditionCode(
                hour.getCondition().getConditionCode()).getPolishDescription();
        return String.format("\n Prognoza godziny %s\n  Temperatura %.1f \u2103\n  Prędkość wiatru: %.2f km/h\n  " +
                        "Kierunek wiatru: %s\n  Ciśnienie atmosferyczne: %.1f hPa\n  Wilgotność: %s \uff05\n" +
                        "  Odczuwalna temperatura: %.1f \u2103\n  Widoczność: %.1f km\n  Podmuchy wiatru: %.1f km/h\n  " +
                        "Opis warunku atmosferycznego: %s\n",
                hour.getTime(), hour.getTempC(), hour.getWindKph(), polishWindDirection,
                hour.getPressureMb(), hour.getHumidityPerc(), hour.getFeelsLikeC(), hour.getVisibilityKm(),
                hour.getGustWindKph(), polishCodeDescription);
    }
}
