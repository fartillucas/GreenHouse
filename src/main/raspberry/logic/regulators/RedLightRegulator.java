package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class RedLightRegulator implements Runnable{

	private int lastSetpoint;

	private ISchedule schedule;

	public RedLightRegulator(ISchedule schedule){
		this.schedule = schedule;
	}

	@Override
	public void run() {
		try {
			ReadableSetpoints setPoints = schedule.getSetpoints();
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
