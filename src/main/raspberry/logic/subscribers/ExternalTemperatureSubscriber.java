package raspberry.logic.subscribers;


import raspberry.communication.CommunicationFacade;

public class ExternalTemperatureSubscriber {

		public void externalTemperatureSubscriber(){
			CommunicationFacade.getInstance().getGreenhouseConnection().readTemp2();

		}

	}
