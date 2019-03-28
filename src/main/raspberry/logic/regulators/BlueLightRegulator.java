package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.SetPoints;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class BlueLightRegulator implements Runnable{
	public void blueLightRegulator (int level)  {
		CommunicationFacade.getInstance().getGreenhouseConnection().setBlueLight(level);
	}

	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			int scheduleBlueLightLevel = setPoints.getBlueLight();
			blueLightRegulator(scheduleBlueLightLevel);
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
