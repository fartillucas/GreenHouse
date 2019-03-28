package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

import static java.lang.Thread.sleep;

public class ErrorSubscriber implements Runnable{

	public void errorSubscriber () {
		CommunicationFacade.getInstance().getGreenhouseConnection().readErrors();
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
