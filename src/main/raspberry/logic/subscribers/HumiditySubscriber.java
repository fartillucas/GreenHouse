package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;

import static java.lang.Thread.sleep;

public class HumiditySubscriber implements Runnable{

	public void humiditySubscriber () {
		CommunicationFacade.getInstance().getGreenhouseConnection().readMoist();
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

