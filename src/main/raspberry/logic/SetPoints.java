package raspberry.logic;

import raspberry.Acquaintance.ReadableSetpoints;

public class SetPoints implements ReadableSetpoints {

    private Double temperature;
    private Double humidity;
    private Double waterlevel;
    private Integer blueLight;
    private Integer redLight;

    public SetPoints(Double temperature, Double humidity, Double waterlevel, Integer blueLight, Integer redLight) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterlevel = waterlevel;
        this.blueLight = blueLight;
        this.redLight = redLight;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getWaterlevel() {
        return waterlevel;
    }

    public Integer getBlueLight() {
        return blueLight;
    }

    public Integer getRedLight() {
        return redLight;
    }
}
