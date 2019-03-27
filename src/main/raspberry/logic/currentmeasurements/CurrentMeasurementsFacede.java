package raspberry.logic.currentmeasurements;

import raspberry.Acquaintance.ICurrentMeasurements;

public class CurrentMeasurementsFacede implements ICurrentMeasurements {
	private static CurrentMeasurementsFacede instance;
	private CurrentMeasurements currentMeasurements;

	public static CurrentMeasurementsFacede getInstance() {
		//TODO singleton vs interface
		if (CurrentMeasurementsFacede.instance == null) {
			CurrentMeasurementsFacede.instance = new CurrentMeasurementsFacede();
		}
		return CurrentMeasurementsFacede.instance;
	}

	private CurrentMeasurementsFacede() {
		this.currentMeasurements = new CurrentMeasurements();
	}

	@Override
	public Double getTemp() {
		return currentMeasurements.getTemp();
	}

	@Override
	public void setTemp(Double temp) {

	}

	@Override
	public Double getTemp2() {
		return currentMeasurements.getTemp2();
	}

	@Override
	public void setTemp2(Double temp2) {

	}

	@Override
	public Double getMoist() {
		return currentMeasurements.getMoist();
	}

	@Override
	public void setMoist(Double moist) {

	}

	@Override
	public Double getLevel() {
		return currentMeasurements.getLevel();
	}

	@Override
	public void setLevel(Double level) {

	}
}
