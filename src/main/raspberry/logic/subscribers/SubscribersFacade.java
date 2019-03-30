package raspberry.logic.subscribers;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ISubscribersFacade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscribersFacade implements ISubscribersFacade {

    private ICurrentMeasurements currentMeasurementsFacade;

    private ExecutorService executor;

    public void injectCurrentMeasurementsFacade(ICurrentMeasurements currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void initialize(){
        ExternalTemperatureSubscriber externalTemperature = new ExternalTemperatureSubscriber(currentMeasurementsFacade);
        HumiditySubscriber humidity = new HumiditySubscriber(currentMeasurementsFacade);
        InternalTemperatureSubscriber internalTemperature = new InternalTemperatureSubscriber(currentMeasurementsFacade);
        WaterLevelSubscriber waterLevel = new WaterLevelSubscriber(currentMeasurementsFacade);

        executor = Executors.newFixedThreadPool(5);
        executor.submit(externalTemperature);
        executor.submit(internalTemperature);
        executor.submit(humidity);
        executor.submit(waterLevel);
    }

    public void stopThreads() {
        executor.shutdownNow();
    }
}
