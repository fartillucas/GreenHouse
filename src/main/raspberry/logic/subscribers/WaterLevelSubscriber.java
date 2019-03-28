package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacede;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	public void waterLevelSubscriber(){
	}
	@Override
	public void run() {
		Double level= CommunicationFacade.getInstance().getGreenhouseConnection().readWaterLevel();
		CurrentMeasurementsFacede.getInstance().setLevel(level);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

