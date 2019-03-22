package raspberry.logic;

public class SetPoints {

    private double temperature;
    private double humidity;
    private double waterlevel;
    private double blueLight;
    private double redLight;

    public SetPoints(double temperature, double humidity, double waterlevel, double blueLight, double redLight) {
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

    public double getBlueLight() {
        return blueLight;
    }

    public double getRedLight() {
        return redLight;
    }
}
