package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;

import static java.lang.Thread.sleep;


public class InternalTemperatureSubscriber implements Runnable{



	public void internalTemperatureSubscriber() {
		CommunicationFacade.getInstance().getGreenhouseConnection().readTemp1();

	}

	@Override
	public void run() {

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}




