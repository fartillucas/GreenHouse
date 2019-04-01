package raspberry.logic.currentmeasurements;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;

public class CurrentMeasurementsFacade implements ICurrentMeasurementsFacade {

//	private static CurrentMeasurementsFacade instance;

	private CurrentMeasurements currentMeasurements;

//	public static CurrentMeasurementsFacade getInstance() {
//		//TODO singleton vs interface
//		if (CurrentMeasurementsFacade.instance == null) {
//			CurrentMeasurementsFacade.instance = new CurrentMeasurementsFacade();
//		}
//
//		return CurrentMeasurementsFacade.instance;
//	}

	public CurrentMeasurementsFacade() {
		this.currentMeasurements = new CurrentMeasurements();
	}

	@Override
	public Double getInternalTemperature() {
		return currentMeasurements.getInternalTemperature();
	}

	@Override
	public void setInternalTemperature(Double temp) {
		this.currentMeasurements.setInternalTemperature(temp);
	}

	@Override
	public Double getExternalTemperature() {
		return currentMeasurements.getExternalTemperature();
	}

	@Override
	public void setExternalTemperature(Double temp2) {
		this.currentMeasurements.setExternalTemperature(temp2);
	}

	@Override
	public Double getHumdity() {
		return currentMeasurements.getHumidity();
	}

	@Override
	public void setMoisture(Double moist) {
		this.currentMeasurements.setHumidity(moist);
	}

	@Override
	public Double getWaterlevel() {
		return currentMeasurements.getWaterlevel();
	}

	@Override
	public void setWaterLevel(Double level) {
		this.currentMeasurements.setWaterlevel(level);
	}
}
