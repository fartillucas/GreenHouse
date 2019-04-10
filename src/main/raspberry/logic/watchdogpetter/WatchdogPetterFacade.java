package raspberry.logic.watchdogpetter;

import raspberry.Acquaintance.ICurrentMeasurementsFacade;
import raspberry.Acquaintance.IWatchdogPetterFacade;
import raspberry.logic.subscribers.ExternalTemperatureSubscriber;
import raspberry.logic.subscribers.HumiditySubscriber;
import raspberry.logic.subscribers.InternalTemperatureSubscriber;
import raspberry.logic.subscribers.WaterLevelSubscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WatchdogPetterFacade implements IWatchdogPetterFacade {




    private ExecutorService executor;

    public WatchdogPetterFacade(){
        executor = Executors.newFixedThreadPool(1);
    }



    public void initialize(){
       executor.submit(new WatchdogPetter());
    }

    public void stopThreads() {
        executor.shutdownNow();
    }

    @Override
    public boolean restartWatchdogPetter() {
        stopThreads();
        executor.submit(new WatchdogPetter());
        return true;
    }
}
