package raspberry.logic.datalogger;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.logic.OutFacadeLogic;

import java.util.Date;

import static java.lang.Thread.sleep;

public class DataloggerRunnable implements Runnable {

    private boolean stopped;
    private ICurrentMeasurementsFacade currentMeasurementsFacade;

    public DataloggerRunnable(ICurrentMeasurementsFacade currentMeasurementsFacade){
        this.currentMeasurementsFacade = currentMeasurementsFacade;
        stopped = false;
    }

    @Override
    public void run() {
        while(!stopped){
            try {
                Double internalTemperature = null;
                Double externalTemperature = null;
                Double humidity = null;
                Double waterlevel = null;

                internalTemperature = currentMeasurementsFacade.getInternalTemperature();
                externalTemperature = currentMeasurementsFacade.getExternalTemperature();
                humidity = currentMeasurementsFacade.getHumdity();
                waterlevel = currentMeasurementsFacade.getWaterlevel();

                OutFacadeLogic.getInstance().uploadDatalog(new Date(), internalTemperature, externalTemperature, humidity, waterlevel);
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                stopped = true;
            }
        }
    }

}
