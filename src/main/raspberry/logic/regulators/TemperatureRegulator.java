package raspberry.logic.regulators;

import raspberry.communication.CommunicationFacade;

public class TemperatureRegulator {
	public void temperatureRegulator(Double temp){
		CommunicationFacade.getInstance().getGreenhouseConnection().setTemperature();
	}
}
