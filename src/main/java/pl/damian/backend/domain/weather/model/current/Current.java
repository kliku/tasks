package pl.damian.backend.domain.weather.model.current;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.damian.backend.domain.weather.model.Condition;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    @JsonProperty("last_updated")
    private String lastUpdate;
    @JsonProperty("temp_c")
    private double tempC;
    @JsonProperty("wind_kph")
    private double windKph;
    @JsonProperty("wind_dir")
    private String dir;
    @JsonProperty("pressure_mb")
    private double pressureMb;
    @JsonProperty("humidity")
    private int humidityPerc;
    @JsonProperty("feelslike_c")
    private double feelsLikeC;
    @JsonProperty("vis_km")
    private double visibilityKm;
    @JsonProperty("gust_kph")
    private double gustWindKph;
    @JsonProperty("condition")
    private Condition condition;

    public Current() {
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public double getTempC() {
        return tempC;
    }

    public double getWindKph() {
        return windKph;
    }

    public String getDir() {
        return dir;
    }

    public double getPressureMb() {
        return pressureMb;
    }

    public int getHumidityPerc() {
        return humidityPerc;
    }

    public double getFeelsLikeC() {
        return feelsLikeC;
    }

    public double getVisibilityKm() {
        return visibilityKm;
    }

    public double getGustWindKph() {
        return gustWindKph;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "Current{" +
                "lastUpdate='" + lastUpdate + '\'' +
                ", tempC=" + tempC +
                ", windKph=" + windKph +
                ", dir='" + dir + '\'' +
                ", pressureMb=" + pressureMb +
                ", humidityPerc=" + humidityPerc +
                ", feelsLikeC=" + feelsLikeC +
                ", visibilityKm=" + visibilityKm +
                ", gustWindKph=" + gustWindKph +
                ", condition=" + condition +
                '}';
    }

}
