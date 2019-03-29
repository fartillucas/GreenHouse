package raspberry.logic.livedata;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ILiveDataGetter;
import raspberry.logic.IPAddressPort;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;

public class LiveDataGetterFacade implements ILiveDataGetter {

//    private static LiveDataGetterFacade instance;

    private ICurrentMeasurements currentMeasurementsFacade;
    private LiveDataGetter liveDataGetter;

    public LiveDataGetterFacade() {
    }

//    public static LiveDataGetterFacade getInstance(){
//        if(instance == null) {
//            instance = new LiveDataGetterFacade();
//        }
//        return instance;
//    }

    @Override
    public ErrorCode setConnection(IPAddressPort ipAddressPort){
        return liveDataGetter.setConnection(ipAddressPort);
    }

    public void injectCurrentMeasurementsFacade(ICurrentMeasurements currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void initialize(){
        this.liveDataGetter = new LiveDataGetter(currentMeasurementsFacade);
        this.liveDataGetter.setName("LiveDataGetter");
        this.liveDataGetter.start();
    }
}
