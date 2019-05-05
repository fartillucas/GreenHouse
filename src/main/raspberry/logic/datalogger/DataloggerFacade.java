package raspberry.logic.datalogger;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataloggerFacade {

    private ICurrentMeasurementsFacade currentMeasurementsFacade;
    private ExecutorService executor;

    public void injectCurrentMeasurementsFacade(ICurrentMeasurementsFacade currentMeasurementsFacade){
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void initialize(){
        executor = Executors.newFixedThreadPool(1);
        DataloggerRunnable dataLogger = new DataloggerRunnable(currentMeasurementsFacade);
        executor.submit(dataLogger);
    }

    public void stopThreads() {
        executor.shutdownNow();
    }

    /*
    double getInternalTemperature(){
        return currentMeasurementsFacade.getInternalTemperature();
    }

    double getExternalTemperature(){
        return currentMeasurementsFacade.getExternalTemperature();
    }

    double getHumidity(){
        return currentMeasurementsFacade.getHumdity();
    }

    double getWaterlevel(){
        return currentMeasurementsFacade.getWaterlevel();
    }
    */

}
