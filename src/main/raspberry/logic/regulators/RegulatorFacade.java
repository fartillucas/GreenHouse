package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.IRegulatorFacade;
import raspberry.Acquaintance.ISchedule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegulatorFacade implements IRegulatorFacade {

    private ICurrentMeasurements currentMeasurementsFacade;
    private ISchedule schedule;

    private ExecutorService executor;

    public void injectCurrentMeasurementsFacade(ICurrentMeasurements currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void injectSchedule(ISchedule schedule) {
        this.schedule = schedule;
    }

    public void initialize() {
        BlueLightRegulator blueLightRegulator = new BlueLightRegulator(schedule);
        RedLightRegulator redLightRegulator = new RedLightRegulator(schedule);
        TemperatureRegulator temperatureRegulator = new TemperatureRegulator(schedule);
        WaterLevelRegulator waterLevelRegulator = new WaterLevelRegulator(currentMeasurementsFacade, schedule);
        FanSpeedRegulator fanSpeedRegulator = new FanSpeedRegulator(currentMeasurementsFacade, schedule);

        executor = Executors.newFixedThreadPool(5);
        executor.submit(blueLightRegulator);
        executor.submit(redLightRegulator);
        executor.submit(temperatureRegulator);
        executor.submit(waterLevelRegulator);
        executor.submit(fanSpeedRegulator);
    }

    public void stopThreads() {
        this.executor.shutdownNow();
    }
}
