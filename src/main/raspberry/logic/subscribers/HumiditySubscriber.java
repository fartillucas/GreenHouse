package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;

public class HumiditySubscriber {

	public void humiditySubscriber () {
		CommunicationFacade.getInstance().getGreenhouseConnection().readMoist();
	}
}
