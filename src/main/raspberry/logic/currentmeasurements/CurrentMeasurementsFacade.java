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
		return currentMeasurements.getTemp();
	}

	@Override
	public void setInternalTemperature(Double temp) {
		this.currentMeasurements.setTemp(temp);
	}

	@Override
	public Double getExternalTemperature() {
		return currentMeasurements.getTemp2();
	}

	@Override
	public void setExternalTemperature(Double temp2) {
		this.currentMeasurements.setTemp2(temp2);
	}

	@Override
	public Double getHumdity() {
		return currentMeasurements.getMoist();
	}

	@Override
	public void setMoisture(Double moist) {
		this.currentMeasurements.setMoist(moist);
	}

	@Override
	public Double getWaterlevel() {
		return currentMeasurements.getLevel();
	}

	@Override
	public void setWaterLevel(Double level) {
		this.currentMeasurements.setLevel(level);
	}
}
