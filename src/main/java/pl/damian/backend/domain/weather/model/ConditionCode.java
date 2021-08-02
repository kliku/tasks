package pl.damian.backend.domain.weather.model;

public enum ConditionCode {
    CODE_1000("Bezchmurnie"),
    CODE_1003("Częściowo zachmurzone"),
    CODE_1006("Pochmurnie"),
    CODE_1009("Zachmurzenie"),
    CODE_1030("Zamglenie"),
    CODE_1063("Możliwe przejściowe deszcze"),
    UNKNOWN("Brak statusu");


    private final String polishDescription;

    ConditionCode(String polishDescription) {
        this.polishDescription = polishDescription;
    }

    public String getPolishDescription() {
        return polishDescription;
    }

    public static ConditionCode getConditionCode(int conditionCode) {
        ConditionCode conditionCodeEnum = UNKNOWN;
        try {
            conditionCodeEnum = ConditionCode.valueOf("CODE_" + conditionCode);
        } catch (IllegalArgumentException ignored) { }
       return conditionCodeEnum;
    }
}
