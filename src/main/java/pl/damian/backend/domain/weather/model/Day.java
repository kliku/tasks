package pl.damian.backend.domain.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    @JsonProperty("maxtemp_c")
    private double maxTemp;
    @JsonProperty("mintemp_c")
    private double minTemp;
    @JsonProperty("avgtemp_c")
    private double avgTemp;
    @JsonProperty("maxwind_kph")
    private double maxWind;
    @JsonProperty("totalpercip_mm")
    private double totalPercip;
    @JsonProperty("avghumidity")
    private double humidity;
    @JsonProperty("daily_chance_of_rain")
    private double dailyChanceRain;
    @JsonProperty("daily_chance_of_snow")
    private double dailyChanceSnow;

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public double getTotalPercip() {
        return totalPercip;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getDailyChanceRain() {
        return dailyChanceRain;
    }

    public double getDailyChanceSnow() {
        return dailyChanceSnow;
    }

    @Override
    public String toString() {
        return "Day{" +
                "maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", avgTemp=" + avgTemp +
                ", maxWind=" + maxWind +
                ", totalPercip=" + totalPercip +
                ", humidity=" + humidity +
                ", dailyChanceRain='" + dailyChanceRain + '\'' +
                ", dailyChanceSnow='" + dailyChanceSnow + '\'' +
                '}';
    }
}
