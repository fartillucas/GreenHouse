package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

	private boolean continueRunning;
	private ICurrentMeasurements currentMeasurements;

	public ExternalTemperatureSubscriber(ICurrentMeasurements currentMeasurements){
		this.currentMeasurements = currentMeasurements;
		this.continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning) {
			Double temp = OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp2();
			currentMeasurements.setTemp2(temp);

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}
}
