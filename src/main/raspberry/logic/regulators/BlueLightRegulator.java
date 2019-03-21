package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class BlueLightRegulator {
	public void blueLightRegulator ()  {
		CommunicationFacade.getInstance().getGreenhouseConnection().setBlueLight();
	}
}
