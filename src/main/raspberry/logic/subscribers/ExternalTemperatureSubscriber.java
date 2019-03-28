package raspberry.logic.subscribers;


import raspberry.communication.CommunicationFacade;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

		public void externalTemperatureSubscriber(){

		}
	@Override
	public void run() {
		Double temp= CommunicationFacade.getInstance().getGreenhouseConnection().readTemp2();
		CurrentMeasurementsFacade.getInstance().setTemp2(temp);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

