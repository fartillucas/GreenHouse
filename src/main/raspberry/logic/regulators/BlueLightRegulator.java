package raspberry.logic.regulators;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.SetPoints;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class BlueLightRegulator implements Runnable{

	private int lastSetpoint;

	@Override
	public void run() {
		try {
			SetPoints setPoints = Schedule.getInstance().getSetpoints();
			int scheduleBlueLightLevel = setPoints.getBlueLight();

			if (scheduleBlueLightLevel != this.lastSetpoint) {
				regulate(scheduleBlueLightLevel);
			}

			this.lastSetpoint = scheduleBlueLightLevel;

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void regulate (int level)  {
		OutFacadeLogic.getInstance().getGreenhouseConnection().setBlueLight(level);
	}
}
