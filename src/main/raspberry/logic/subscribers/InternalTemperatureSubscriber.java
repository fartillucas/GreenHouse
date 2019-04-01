package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class InternalTemperatureSubscriber implements Runnable{

    private boolean continueRunning;
    private ICurrentMeasurementsFacade currentMeasurements;

    public InternalTemperatureSubscriber(ICurrentMeasurementsFacade currentMeasurements){
        this.currentMeasurements = currentMeasurements;
        continueRunning = true;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && continueRunning) {
            Double temp= OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp1();
            currentMeasurements.setInternalTemperature(temp);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                continueRunning = false;
            }
        }
    }
}
