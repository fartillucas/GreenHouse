package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacede;

import static java.lang.Thread.sleep;

public class HumiditySubscriber implements Runnable{


	public void humiditySubscriber () {

	}
	@Override
	public void run() {
		try {
			Double moist= CommunicationFacade.getInstance().getGreenhouseConnection().readMoist();
			CurrentMeasurementsFacede.getInstance().setMoist(moist);

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

