package pl.damian.backend.domain.weather.model.history;

import pl.damian.backend.domain.weather.model.Location;
import pl.damian.backend.domain.weather.model.forecast.Forecast;

public class HistoryWeather {
    private Location location;
    private Forecast forecast;

    public HistoryWeather(Location location, Forecast forecast) {
        this.location = location;
        this.forecast = forecast;
    }

    public HistoryWeather() {
    }

    public Location getLocation() {
        return location;
    }

    public Forecast getForecast() {
        return forecast;
    }

}
