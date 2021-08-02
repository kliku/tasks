package pl.damian.backend.domain.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    @JsonProperty("code")
    private int conditionCode;

    public Condition(int conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Condition() {
    }

    public int getConditionCode() {
        return conditionCode;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "conditionCode=" + conditionCode +
                '}';
    }
}
