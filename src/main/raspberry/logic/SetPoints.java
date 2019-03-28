package raspberry.logic;

public class SetPoints {

    private double temperature;
    private double humidity;
    private double waterlevel;
    private int blueLight;
    private int redLight;

    public SetPoints(double temperature, double humidity, double waterlevel, int blueLight, int redLight) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterlevel = waterlevel;
        this.blueLight = blueLight;
        this.redLight = redLight;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWaterlevel() {
        return waterlevel;
    }

    public int getBlueLight() {
        return blueLight;
    }

    public int getRedLight() {
        return redLight;
    }
}
