package raspberry.logic.currentmeasurements;

public class CurrentMeasurements {

	private Double temp; //internal temperature
	private Double temp2; //external temperature
	private Double moist; //humidity
	private Double level; //water level
	private Double tempLock = 1.0;
	private Double temp2Lock = 1.0;
	private Double moistLock = 1.0;
	private Double levelLock = 1.0;

	public CurrentMeasurements(double internalTemperature, double externalTemperature, double humidity, double waterLevel) {
		this.temp = internalTemperature;
		this.temp2 = externalTemperature;
		this.moist = humidity;
		this.level = waterLevel;
	}

	public CurrentMeasurements() {
	}

	public Double getTemp() {
		synchronized (this.tempLock) {
			return temp;
		}
	}

	public void setTemp(Double temp) {
		synchronized (this.tempLock) {
			this.temp = temp;
		}
	}

	public Double getTemp2() {
		synchronized (temp2Lock) {
			return temp2;
		}
	}


	public void setTemp2(Double temp2) {
		synchronized (this.temp2Lock) {
			this.temp2 = temp2;
		}
	}

	public Double getMoist() {
		synchronized (this.moistLock) {
			return moist;
		}
	}

	public void setMoist(Double moist) {
		synchronized (this.moistLock) {
			this.moist = moist;
		}
	}

	public Double getLevel() {
		synchronized (this.levelLock) {
			return level;
		}
	}

	public void setLevel(Double level) {
		synchronized (this.levelLock) {
			this.level = level;
		}
	}
}

