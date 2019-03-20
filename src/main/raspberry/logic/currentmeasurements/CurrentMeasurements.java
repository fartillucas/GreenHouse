package raspberry.logic.currentmeasurements;

public class CurrentMeasurements {

	private double internalTemperature;
	private double externalTemperature;
	private double humidity;
	private double waterLevel;

	public CurrentMeasurements(double internalTemperature, double externalTemperature, double humidity, double waterLevel) {
		this.internalTemperature = internalTemperature;
		this.externalTemperature = externalTemperature;
		this.humidity = humidity;
		this.waterLevel = waterLevel;
	}

	public double getInternalTemperature() {

		return internalTemperature;
	}

	public void setInternalTemperature(double internalTemperature) {
		this.internalTemperature = internalTemperature;
	}

	public double getExternalTemperature() {
		return externalTemperature;
	}

	public void setExternalTemperature(double externalTemperature) {
		this.externalTemperature = externalTemperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(double waterLevel) {
		this.waterLevel = waterLevel;
	}
}

