package raspberry.logic.regulators;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{

	@Override
	public void run() {
		while (true){
			try {
				SetPoints setpoints = Schedule.getInstance().getSetpoints();
				double scheduleTemp = setpoints.getTemperature();

				Double currentTemp = CurrentMeasurementsFacade.getInstance().getTemp();

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
