package raspberry.logic.subscribers;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

	@Override
	public void run() {

		Double temp = OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp2();
		CurrentMeasurementsFacade.getInstance().setTemp2(temp);

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
