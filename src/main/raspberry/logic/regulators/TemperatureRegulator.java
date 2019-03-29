package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class TemperatureRegulator implements Runnable {

	private double lastSetpoint;

	private ISchedule schedule;

	public TemperatureRegulator(ISchedule schedule){
		this.schedule = schedule;
	}

	@Override
	public void run() {
		try {
			ReadableSetpoints setPoints = schedule.getSetpoints();
			double scheduleTemp = setPoints.getTemperature();

//			Double currentTemp = CurrentMeasurementsFacade.getInstance().getTemp();

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