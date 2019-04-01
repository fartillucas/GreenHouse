package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

	private boolean continueRunning;
	private ICurrentMeasurementsFacade currentMeasurements;

	public ExternalTemperatureSubscriber(ICurrentMeasurementsFacade currentMeasurements){
		this.currentMeasurements = currentMeasurements;
		this.continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning) {
			Double temp = OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp2();
			currentMeasurements.setExternalTemperature(temp);

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}
}
