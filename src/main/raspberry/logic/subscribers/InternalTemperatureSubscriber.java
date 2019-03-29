package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class InternalTemperatureSubscriber implements Runnable{

    private ICurrentMeasurements currentMeasurements;

    public InternalTemperatureSubscriber(ICurrentMeasurements currentMeasurements){
        this.currentMeasurements = currentMeasurements;
    }

    @Override
    public void run() {
        Double temp= OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp1();
        currentMeasurements.setTemp(temp);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
