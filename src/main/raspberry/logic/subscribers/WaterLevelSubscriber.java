package raspberry.logic.subscribers;

import raspberry.communication.CommunicationFacade;

public class WaterLevelSubscriber {

	public void waterLevelSubscriber(){
		CommunicationFacade.getInstance().getGreenhouseConnection().readWaterLevel();

	}
}
