package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class WaterLevelSubscriber implements Runnable{

	private ICurrentMeasurements currentMeasurements;

	public WaterLevelSubscriber(ICurrentMeasurements currentMeasurements){
		this.currentMeasurements = currentMeasurements;
	}

	@Override
	public void run() {
		Double level = OutFacadeLogic.getInstance().getGreenhouseConnection().readWaterLevel();
		currentMeasurements.setLevel(level);

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

