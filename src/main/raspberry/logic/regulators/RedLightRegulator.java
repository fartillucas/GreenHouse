package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.OutFacadeLogic;
import raspberry.logic.SetPoints;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class RedLightRegulator implements Runnable{

	private int lastSetpoint;

	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			int scheduleRedLightLevel = setPoints.getRedLight();

			if (scheduleRedLightLevel != lastSetpoint) {
				regulate(scheduleRedLightLevel);
			}

			this.lastSetpoint = scheduleRedLightLevel;

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void regulate (int level)  {
		OutFacadeLogic.getInstance().getGreenhouseConnection().setRedLight(level);
	}
}
