package pl.damian.backend.domain.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String name;
    private String country;
    private double lat;
    private double lon;
    @JsonProperty("tz_id")
    private String timezone;
    @JsonProperty("localtime")
    private String localTime;

    public Location(String name, String country, double lat, double lon, String timezone, String localTime) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.localTime = localTime;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getLocalTime() {
        return localTime;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", localTime='" + localTime + '\'' +
                '}';
    }
}
