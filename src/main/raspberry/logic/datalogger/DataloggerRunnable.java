package raspberry.logic.datalogger;

import raspberry.logic.OutFacadeLogic;

import java.util.Date;

import static java.lang.Thread.sleep;

public class DataloggerRunnable implements Runnable {
    private boolean stopped;
    private DataloggerFacade dataloggerFacade;

    public DataloggerRunnable(DataloggerFacade dataloggerFacade){
        this.dataloggerFacade = dataloggerFacade;
    }

    public void stopThread(){
        this.stopped = true;
    }


    @Override
    public void run() {

        stopped = false;

        try {
            double internalTemperature = 0;
            double externalTemperature = 0;
            double humidity = 0;
            double waterlevel = 0;

        while(!stopped){
            //TODO DO SOMETHING
            internalTemperature = dataloggerFacade.getInternalTemperature();
            externalTemperature = dataloggerFacade.getExternalTemperature();
            humidity = dataloggerFacade.getHumidity();
            waterlevel = dataloggerFacade.getWaterlevel();

            OutFacadeLogic.getInstance().uploadDatalog(new Date(), internalTemperature, externalTemperature, humidity, waterlevel);
            sleep(1000);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
