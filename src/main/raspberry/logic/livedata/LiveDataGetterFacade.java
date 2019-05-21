package raspberry.logic.livedata;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.Acquaintance.ILiveDataGetter;
import raspberry.logic.IPAddressPort;

public class LiveDataGetterFacade implements ILiveDataGetter {

    private ICurrentMeasurementsFacade currentMeasurementsFacade;
    private LiveDataGetter liveDataGetter;

    public LiveDataGetterFacade() {
    }

    @Override
    public ErrorCode setConnection(IPAddressPort ipAddressPort){
        this.liveDataGetter = new LiveDataGetter(currentMeasurementsFacade);
        this.liveDataGetter.setName("LiveDataGetter");
        this.liveDataGetter.setDaemon(true);
        ErrorCode error =  liveDataGetter.setConnection(ipAddressPort);
        this.liveDataGetter.start();
        return error;
    }

    public void injectCurrentMeasurementsFacade(ICurrentMeasurementsFacade currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void initialize(){
        this.liveDataGetter = new LiveDataGetter(currentMeasurementsFacade);
        this.liveDataGetter.setName("LiveDataGetter");
        this.liveDataGetter.setDaemon(true);
        this.liveDataGetter.start();
    }

    public void stopThreads() {
        if (this.liveDataGetter != null) {
            this.liveDataGetter.interrupt();
        }
    }
}
