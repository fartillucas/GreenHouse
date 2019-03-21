package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;



public class InternalTemperatureSubscriber {



	public void internalTemperatureSubscriber() {
		CommunicationFacade.getInstance().getGreenhouseConnection().readTemp1();

	}

	}



