package pl.damian.backend.domain.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.damian.backend.domain.weather.model.Location;
import pl.damian.backend.domain.weather.model.current.Current;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeather {
    private Location location;
    private Current current;
    private Forecast forecast;

    public ForecastWeather(Location location, Current current, Forecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public ForecastWeather() {
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    @Override
    public String toString() {
        return "ForecastWeather{" +
                "location=" + location +
                ", current=" + current +
                ", forecast=" + forecast +
                '}';
    }
}
