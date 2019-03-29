package raspberry.logic.regulators;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class WaterLevelRegulator implements Runnable{

	@Override
	public void run() {
		while (true){
			try {
				SetPoints setPoints = Schedule.getInstance().getSetpoints();
				double scheduleWaterLevel = setPoints.getWaterlevel();

				Double currentWaterLevel = CurrentMeasurementsFacade.getInstance().getLevel();

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
