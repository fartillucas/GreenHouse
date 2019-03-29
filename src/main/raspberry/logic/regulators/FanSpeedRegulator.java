package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{

	private ICurrentMeasurements currentMeasurements;
	private ISchedule schedule;

	public FanSpeedRegulator(ICurrentMeasurements currentMeasurements, ISchedule schedule){
		this.currentMeasurements = currentMeasurements;
		this.schedule = schedule;
	}

	@Override
	public void run() {
		while (true){
			try {
				ReadableSetpoints setpoints = schedule.getSetpoints();
				double scheduleTemp = setpoints.getTemperature();

				Double currentTemp = currentMeasurements.getTemp();

				Double tempDifference = currentTemp-scheduleTemp;

				if (tempDifference <= 0){
					regulate(0);
				} else if (tempDifference>10) {
					regulate(2);
				}else {
					regulate(1);
				}

				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void regulate(int speed){
		OutFacadeLogic.getInstance().getGreenhouseConnection().setFanSpeed(speed);
	}
}
