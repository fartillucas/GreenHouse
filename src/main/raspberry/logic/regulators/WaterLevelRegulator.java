package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.SetPoints;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.schedule.Schedule;

import static java.lang.Thread.sleep;

public class WaterLevelRegulator implements Runnable{
	public void waterLevelRegulator(int sec){
		CommunicationFacade.getInstance().getGreenhouseConnection().addWater(sec);
	}

	@Override
	public void run() {
		while (true){
			try {
				SetPoints setPoints = Schedule.getInstance().getSetpoints();
				Double currentWaterLevel = CurrentMeasurementsFacade.getInstance().getLevel();
				double scheduleWaterLevel = setPoints.getWaterlevel();
				if (currentWaterLevel<scheduleWaterLevel)
					waterLevelRegulator(5);
						else
							waterLevelRegulator(0);

				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
