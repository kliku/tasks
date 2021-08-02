package pl.damian.backend.domain.weather.model;

public enum WindDirection {
    N("północny"),
    NNE("północno północno-wschodni"),
    NE("północno-wschodni"),
    ENE("wschodnio północno-wschodni"),
    E("wschodni"),
    ESE("wschodnio południowo-wschodni"),
    SE("południowo-wschodni"),
    SSE("południowy południowo-wschodni"),
    S("południowy"),
    SSW("południowy południowo-zachodni"),
    SW("południowo-zachodni"),
    WSW("zachodnio południowo-zachodni"),
    W("zachodni"),
    WNW("zachodnio północno-zachodni"),
    NW("północno-zachodn"),
    NNW("północno północno-zachodni");
    private final String polishWindDirection;

    WindDirection(String polishWindDirection) {
        this.polishWindDirection = polishWindDirection;
    }

    public String getPolishWindDirection() {
        return polishWindDirection;
    }
}
