package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class FanSpeedRegulator {
	public void fanSpeedRegulator(){
		CommunicationFacade.getInstance().getGreenhouseConnection().setFanSpeed();
	}
}
