package raspberry.logic.livedata;

import raspberry.Acquaintance.ErrorCode;
import raspberry.logic.IPAddressPort;

public class LiveDataGetterFacade {
    private static LiveDataGetterFacade instance;
    private LiveDataGetter liveDataGetter;



    private LiveDataGetterFacade() {
        this.liveDataGetter = new LiveDataGetter();
        this.liveDataGetter.setName("LiveDataGetter");
        this.liveDataGetter.start();
        System.out.println("\nRaspberry: datagetter is started");
    }

    public static LiveDataGetterFacade getInstance(){
        if(instance == null) {
            instance = new LiveDataGetterFacade();
        }
        return instance;
    }

    public ErrorCode setConnection(IPAddressPort ipAddressPort){
        return liveDataGetter.setConnection(ipAddressPort);
    }

}
