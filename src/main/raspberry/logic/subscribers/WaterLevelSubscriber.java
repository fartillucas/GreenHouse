package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	public void waterLevelSubscriber(){
	}
	@Override
	public void run() {
		Double level= CommunicationFacade.getInstance().getGreenhouseConnection().readWaterLevel();
		CurrentMeasurementsFacade.getInstance().setLevel(level);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

