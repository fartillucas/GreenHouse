package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class BlueLightRegulator implements Runnable{

	private boolean continueRunning;
	private Integer lastSetpoint;

	private ISchedule schedule;

	public BlueLightRegulator(ISchedule schedule){
		this.schedule = schedule;
		continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning) {
			try {
				ReadableSetpoints setPoints = schedule.getSetpoints();
				Integer scheduleBlueLightLevel = setPoints.getBlueLight();

				if(scheduleBlueLightLevel == null) {
					regulate(0);
				} else if (scheduleBlueLightLevel.equals(lastSetpoint)) {
					regulate(scheduleBlueLightLevel);
				}

				this.lastSetpoint = scheduleBlueLightLevel;

				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}

	private void regulate (int level)  {
		OutFacadeLogic.getInstance().getGreenhouseConnection().setBlueLight(level);
	}
}
