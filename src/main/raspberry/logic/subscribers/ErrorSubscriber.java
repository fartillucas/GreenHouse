package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

public class ErrorSubscriber {

	public void errorSubscriber () {
		CommunicationFacade.getInstance().getGreenhouseConnection().readErrors();
	}
}
