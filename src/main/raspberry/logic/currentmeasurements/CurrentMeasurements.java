package raspberry.logic.currentmeasurements;

public class CurrentMeasurements {

	private Double temp; //internal temperature
	private Double temp2; //external temperature
	private Double moist; //humidity
	private Double level; //water level

	public CurrentMeasurements(double internalTemperature, double externalTemperature, double humidity, double waterLevel) {
		this.temp = internalTemperature;
		this.temp2 = externalTemperature;
		this.moist = humidity;
		this.level = waterLevel;
	}

	public CurrentMeasurements() {
	}

	public Double getTemp() {return temp;}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public Double getTemp2() {
		return temp2;
	}

	public void setTemp2(double temp2) {
		this.temp2 = temp2;
	}

	public Double getMoist() {
		return moist;
	}

	public void setMoist(double moist) {
		this.moist = moist;
	}

	public Double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}
}

