package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class FanSpeedRegulator {
	public void fanSpeedRegulator(int speed){
		CommunicationFacade.getInstance().getGreenhouseConnection().setFanSpeed();
	}
}
