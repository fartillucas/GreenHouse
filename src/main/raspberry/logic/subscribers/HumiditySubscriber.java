package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class HumiditySubscriber implements Runnable{

	private boolean continueRunning;
	private ICurrentMeasurements currentMeasurements;

    public HumiditySubscriber(ICurrentMeasurements currentMeasurements){
        this.currentMeasurements = currentMeasurements;
        continueRunning = true;
    }

	@Override
	public void run() {
		while (!Thread.interrupted() && continueRunning) {
			try {
				Double moist= OutFacadeLogic.getInstance().getGreenhouseConnection().readMoist();
				currentMeasurements.setMoist(moist);

				sleep(1000);
			} catch (InterruptedException e) {
				continueRunning = false;
			}
		}
	}
}
