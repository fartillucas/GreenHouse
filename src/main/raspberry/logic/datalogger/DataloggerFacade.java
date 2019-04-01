package raspberry.logic.datalogger;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;

public class DataloggerFacade {

    private ICurrentMeasurementsFacade currentMeasurementsFacade;


    public void injectCurrentMeasurementsFacade(ICurrentMeasurementsFacade currentMeasurementsFacade){
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

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

}
