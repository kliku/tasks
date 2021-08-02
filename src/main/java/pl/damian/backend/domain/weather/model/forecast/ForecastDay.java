package pl.damian.backend.domain.weather.model.forecast;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.damian.backend.domain.weather.model.Astro;
import pl.damian.backend.domain.weather.model.Day;
import pl.damian.backend.domain.weather.model.Hour;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDay {
    private String date;
    private Day day;
    private Astro astro;
    private List<Hour> hour;

    public ForecastDay(String date, Day day, Astro astro, List<Hour> hour) {
        this.date = date;
        this.day = day;
        this.astro = astro;
        this.hour = hour;
    }

    public ForecastDay() {
    }

    public String getDate() {
        return date;
    }

    public Day getDay() {
        return day;
    }

    public Astro getAstro() {
        return astro;
    }

    public List<Hour> getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return "ForecastDay{" +
                "date='" + date + '\'' +
                ", day=" + day +
                ", astro=" + astro +
                ", hour=" + hour +
                '}';
    }
}
