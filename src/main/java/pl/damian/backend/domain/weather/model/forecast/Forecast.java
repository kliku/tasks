package pl.damian.backend.domain.weather.model.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Forecast {
    @JsonProperty("forecastday")
    private List<ForecastDay> forecastDays;

    public List<ForecastDay> getForecastDays() {
        return forecastDays;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "forecastDays=" + forecastDays +
                '}';
    }
}
