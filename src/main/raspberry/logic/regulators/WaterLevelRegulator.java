package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class WaterLevelRegulator implements Runnable{

	private final ICurrentMeasurements currentMeasurements;
	private ISchedule schedule;

	public WaterLevelRegulator(ICurrentMeasurements currentMeasurements, ISchedule schedule){
		this.currentMeasurements = currentMeasurements;
		this.schedule = schedule;
	}

	@Override
	public void run() {
		while (true){
			try {
				ReadableSetpoints setPoints = schedule.getSetpoints();
				double scheduleWaterLevel = setPoints.getWaterlevel();

				Double currentWaterLevel = currentMeasurements.getLevel();

				if (currentWaterLevel<scheduleWaterLevel) {
					regulate(5);
				} else {
					regulate(0);
				}
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void regulate(int sec){
		OutFacadeLogic.getInstance().getGreenhouseConnection().addWater(sec);
	}
}
