package raspberry.logic.subscribers;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	@Override
	public void run() {
		Double level = OutFacadeLogic.getInstance().getGreenhouseConnection().readWaterLevel();
		CurrentMeasurementsFacade.getInstance().setLevel(level);

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

