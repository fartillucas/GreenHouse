package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class WaterLevelRegulator implements Runnable{

	private ICurrentMeasurementsFacade currentMeasurements;
	private boolean continueRunning;
	private ISchedule schedule;

	public WaterLevelRegulator(ICurrentMeasurementsFacade currentMeasurements, ISchedule schedule){
		this.currentMeasurements = currentMeasurements;
		this.schedule = schedule;
		continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning){
			try {
				ReadableSetpoints setPoints = schedule.getSetpoints();
				Double scheduleWaterLevel = setPoints.getWaterlevel();

				Double currentWaterLevel = currentMeasurements.getWaterlevel();

				if (scheduleWaterLevel != null && currentWaterLevel != null){
					if (currentWaterLevel<scheduleWaterLevel) {
						regulate(5);
					} else {
						regulate(0);
					}
				} else {
					regulate(0);
				}

				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}

	private void regulate(int sec){
		OutFacadeLogic.getInstance().getGreenhouseConnection().addWater(sec);
	}
}
