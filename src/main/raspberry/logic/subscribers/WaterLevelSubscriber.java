package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	private boolean continueRunning;
	private ICurrentMeasurementsFacade currentMeasurements;

	public WaterLevelSubscriber(ICurrentMeasurementsFacade currentMeasurements){
		this.currentMeasurements = currentMeasurements;
		this.continueRunning = true;
	}

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning) {
			Double level = OutFacadeLogic.getInstance().getGreenhouseConnection().readWaterLevel();
			currentMeasurements.setWaterLevel(level);

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}
}

