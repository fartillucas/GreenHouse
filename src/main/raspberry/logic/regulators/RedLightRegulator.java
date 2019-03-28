package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.SetPoints;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class RedLightRegulator implements Runnable{
	public void redLightRegulator (int level)  {
		CommunicationFacade.getInstance().getGreenhouseConnection().setRedLight(level);
	}

	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			int scheduleRedLightLevel = setPoints.getRedLight();
			redLightRegulator(scheduleRedLightLevel);

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
