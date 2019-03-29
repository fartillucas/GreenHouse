package raspberry.logic.currentmeasurements;

import raspberry.Acquaintance.ICurrentMeasurements;

public class CurrentMeasurementsFacade implements ICurrentMeasurements {

	private static CurrentMeasurementsFacade instance;

	private CurrentMeasurements currentMeasurements;

	public static CurrentMeasurementsFacade getInstance() {
		//TODO singleton vs interface
		if (CurrentMeasurementsFacade.instance == null) {
			CurrentMeasurementsFacade.instance = new CurrentMeasurementsFacade();
		}

		return CurrentMeasurementsFacade.instance;
	}

	public CurrentMeasurementsFacade() {
		this.currentMeasurements = new CurrentMeasurements();
	}

	@Override
	public Double getTemp() {
		return currentMeasurements.getTemp();
	}

	@Override
	public void setTemp(Double temp) {
		this.currentMeasurements.setTemp(temp);
	}

	@Override
	public Double getTemp2() {
		return currentMeasurements.getTemp2();
	}

	@Override
	public void setTemp2(Double temp2) {
		this.currentMeasurements.setTemp2(temp2);
	}

	@Override
	public Double getMoist() {
		return currentMeasurements.getMoist();
	}

	@Override
	public void setMoist(Double moist) {
		this.currentMeasurements.setMoist(moist);
	}

	@Override
	public Double getLevel() {
		return currentMeasurements.getLevel();
	}

	@Override
	public void setLevel(Double level) {
		this.currentMeasurements.setLevel(level);
	}
}
