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
        return liveDataGetter.setConnection(ipAddressPort);
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
        this.liveDataGetter.interrupt();
    }
}
