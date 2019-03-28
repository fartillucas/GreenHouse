package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.ScheduleInterpreter;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacede;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{
	public void fanSpeedRegulator(int speed){
		CommunicationFacade.getInstance().getGreenhouseConnection().setFanSpeed(speed);
	}

	@Override
	public void run() {
		while (true){
			try {
				SetPoints setpoints = Schedule.getInstance().getSetpoints();
				Double currentTemp = CurrentMeasurementsFacede.getInstance().getTemp();
				double scheduleTemp = setpoints.getTemperature();
				Double tempDifference = currentTemp-scheduleTemp;
				if (tempDifference <= 0)
					fanSpeedRegulator(0);
					else if (tempDifference>10)
						fanSpeedRegulator(2);
							else
								fanSpeedRegulator(1);
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
