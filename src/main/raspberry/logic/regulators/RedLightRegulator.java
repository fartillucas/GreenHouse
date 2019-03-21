package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class RedLightRegulator {
	public void redLightRegulator ()  {
		CommunicationFacade.getInstance().getGreenhouseConnection().setRedLight();
	}
}
