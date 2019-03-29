package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class ExternalTemperatureSubscriber implements Runnable{

	private ICurrentMeasurements currentMeasurements;

	public ExternalTemperatureSubscriber(ICurrentMeasurements currentMeasurements){
		this.currentMeasurements = currentMeasurements;
	}

	@Override
	public void run() {

		Double temp = OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp2();
		currentMeasurements.setTemp2(temp);

		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
