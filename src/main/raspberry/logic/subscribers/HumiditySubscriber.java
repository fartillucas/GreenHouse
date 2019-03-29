package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class HumiditySubscriber implements Runnable{

    private ICurrentMeasurements currentMeasurements;

    public HumiditySubscriber(ICurrentMeasurements currentMeasurements){
        this.currentMeasurements = currentMeasurements;
    }

	@Override
	public void run() {
		try {
			Double moist= OutFacadeLogic.getInstance().getGreenhouseConnection().readMoist();
			currentMeasurements.setMoist(moist);

			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
