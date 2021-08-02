package pl.damian.backend.domain.weather.model.current;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.damian.backend.domain.weather.model.Location;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    private Location location;
    private Current current;

    public CurrentWeather(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public CurrentWeather() {
    }

    public Location getLocation() {
        return location;
    }

    public Current getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }
}
