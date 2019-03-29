package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SubscribersFacade {

    private ICurrentMeasurements currentMeasurementsFacade;

    public void injectCurrentMeasurementsFacade(ICurrentMeasurements currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public boolean initialize(){
        throw new NotImplementedException();
    }

}
