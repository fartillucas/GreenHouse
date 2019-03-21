package raspberry.logic.currentmeasurements;

import raspberry.logic.ICurrentMeasurements;

public class CurrentMeasurementsFacede implements ICurrentMeasurements {
	private static CurrentMeasurementsFacede instance;

	public static CurrentMeasurementsFacede getInstance() {
		if (CurrentMeasurementsFacede.instance == null) {
			CurrentMeasurementsFacede.instance = new CurrentMeasurementsFacede();
		}
		return CurrentMeasurementsFacede.instance;
	}

	@Override
	public Double getTemp() {
		return null;
	}

	@Override
	public void setTemp(Double temp) {

	}

	@Override
	public Double getTemp2() {
		return null;
	}

	@Override
	public void setTemp2(Double temp2) {

	}

	@Override
	public Double getMoist() {
		return null;
	}

	@Override
	public void setMoist(Double moist) {

	}

	@Override
	public Double getLevel() {
		return null;
	}

	@Override
	public void setLevel(Double level) {

	}
}
