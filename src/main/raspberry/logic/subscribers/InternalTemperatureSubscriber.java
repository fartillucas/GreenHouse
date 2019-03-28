package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;


public class InternalTemperatureSubscriber implements Runnable{

	public void internalTemperatureSubscriber() {

	}
	@Override
	public void run() {
		Double temp= CommunicationFacade.getInstance().getGreenhouseConnection().readTemp1();
		CurrentMeasurementsFacade.getInstance().setTemp(temp);

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}




