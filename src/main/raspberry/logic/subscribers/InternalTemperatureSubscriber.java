package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class InternalTemperatureSubscriber implements Runnable{

    private boolean continueRunning;
    private ICurrentMeasurements currentMeasurements;

    public InternalTemperatureSubscriber(ICurrentMeasurements currentMeasurements){
        this.currentMeasurements = currentMeasurements;
        continueRunning = true;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && continueRunning) {
            Double temp= OutFacadeLogic.getInstance().getGreenhouseConnection().readTemp1();
            currentMeasurements.setTemp(temp);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                continueRunning = false;
            }
        }
    }
}
