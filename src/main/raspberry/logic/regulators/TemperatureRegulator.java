package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class TemperatureRegulator {
	public void temperatureRegulator(){
		CommunicationFacade.getInstance().getGreenhouseConnection().setTemperature();
	}
}