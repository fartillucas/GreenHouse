package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class BlueLightRegulator implements Runnable{

	private int lastSetpoint;

	private ISchedule schedule;

	public BlueLightRegulator(ISchedule schedule){
		this.schedule = schedule;
	}

	@Override
	public void run() {
		try {
			ReadableSetpoints setPoints = schedule.getSetpoints();
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
