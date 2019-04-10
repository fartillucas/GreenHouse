package raspberry.logic.currentmeasurements;

public class CurrentMeasurements {

	private Double internalTemperature;
	private Double externalTemperature;
	private Double humidity;
	private Double waterlevel;

	//TODO This should be real locks?
	private Double internalTemperatureLock = 1.0;
	private Double externalTemperatureLock = 1.0;
	private Double humidityLock = 1.0;
	private Double waterlevelLock = 1.0;

	public CurrentMeasurements(double internalTemperature, double externalTemperature, double humidity, double waterLevel) {
		this.internalTemperature = internalTemperature;
		this.externalTemperature = externalTemperature;
		this.humidity = humidity;
		this.waterlevel = waterLevel;
	}

	public CurrentMeasurements() {
	}

	public Double getInternalTemperature() {
		synchronized (this.internalTemperatureLock) {
			return internalTemperature;
		}
	}

	public void setInternalTemperature(Double internalTemperature) {
		synchronized (this.internalTemperatureLock) {
			this.internalTemperature = internalTemperature;
		}
	}

	public Double getExternalTemperature() {
		synchronized (this.externalTemperatureLock) {
			return externalTemperature;
		}
	}


	public void setExternalTemperature(Double externalTemperature) {
		synchronized (this.externalTemperatureLock) {
			this.externalTemperature = externalTemperature;
		}
	}

	public Double getHumidity() {
		synchronized (this.humidityLock) {
			return humidity;
		}
	}

	public void setHumidity(Double humidity) {
		synchronized (this.humidityLock) {
			this.humidity = humidity;
		}
	}

	public Double getWaterlevel() {
		synchronized (this.waterlevelLock) {
			return waterlevel;
		}
	}

	public void setWaterlevel(Double waterlevel) {
		synchronized (this.waterlevelLock) {
			this.waterlevel = waterlevel;
		}
	}
}

