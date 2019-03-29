package raspberry.logic.regulators;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class TemperatureRegulator implements Runnable {

	private double lastSetpoint;

	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			double scheduleTemp = setPoints.getTemperature();

			Double currentTemp = CurrentMeasurementsFacade.getInstance().getTemp();

			if (scheduleTemp != lastSetpoint) {
				//TODO if we can not control the heater, but only a temperature setpoint, then the comparison doesn't make sense
//				if (currentTemp<scheduleTemp) {
					regulate((int) scheduleTemp);
//				}
			}

			this.lastSetpoint = scheduleTemp;

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void regulate(int kelvin) {
		OutFacadeLogic.getInstance().getGreenhouseConnection().setTemperature(kelvin);
	}
}