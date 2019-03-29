package raspberry.logic.subscribers;

import raspberry.logic.OutFacadeLogic;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

import static java.lang.Thread.sleep;

public class HumiditySubscriber implements Runnable{

	@Override
	public void run() {
		try {
			Double moist= OutFacadeLogic.getInstance().getGreenhouseConnection().readMoist();
			CurrentMeasurementsFacade.getInstance().setMoist(moist);

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
