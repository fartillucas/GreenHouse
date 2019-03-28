package raspberry.logic.subscribers;


import raspberry.communication.CommunicationFacade;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

		public void externalTemperatureSubscriber(){
			CommunicationFacade.getInstance().getGreenhouseConnection().readTemp2();

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

