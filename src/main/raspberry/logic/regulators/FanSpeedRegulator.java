package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class FanSpeedRegulator implements Runnable{
	public void fanSpeedRegulator(int speed){
		CommunicationFacade.getInstance().getGreenhouseConnection().setFanSpeed(1);
	}

	@Override
	public void run() {
		while (true){
			try {
				/*Double currentTemp = CommunicationFacade.getInstance().getGreenhouseConnection().readTemp1();
				Double scheduleTemp = Schedule.getInstance();  //TODO get setpoints & get temp from schedule
				Double tempDifference = currentTemp-scheduleTemp;
				if (tempDifference <= 0)
					fanSpeedRegulator(0);
					else if (tempDifference>10)
						fanSpeedRegulator(2);
							else
								fanSpeedRegulator(1);*/



				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
