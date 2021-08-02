package pl.damian.backend.domain.weather;

import pl.damian.backend.domain.weather.model.current.CurrentWeather;
import pl.damian.backend.domain.weather.model.forecast.ForecastWeather;
import pl.damian.backend.domain.weather.model.history.HistoryWeather;

import java.util.Optional;

public interface WeatherInformation {
    Optional<CurrentWeather> getCurrentWeather(String city);

    Optional<ForecastWeather> getForecastWeather(String city, int days);

    Optional<HistoryWeather> getHistoryWeather(String city, String date);
}
