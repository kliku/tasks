package pl.damian.backend.domain.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Astro {
    @JsonProperty("sunrise")
    private String sunRise;
    @JsonProperty("sunset")
    private String sunSet;
    @JsonProperty("moon_phase")
    private String moonPhase;

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    @Override
    public String toString() {
        return "Astro{" +
                "sunRise='" + sunRise + '\'' +
                ", sunSet='" + sunSet + '\'' +
                ", moonPhase='" + moonPhase + '\'' +
                '}';
    }
}
