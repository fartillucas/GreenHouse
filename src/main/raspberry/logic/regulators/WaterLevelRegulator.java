package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class WaterLevelRegulator {
	public void waterLevelRegulator(){
		CommunicationFacade.getInstance().getGreenhouseConnection().addWater();
	}
}
