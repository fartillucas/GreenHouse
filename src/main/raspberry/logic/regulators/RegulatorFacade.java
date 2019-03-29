package raspberry.logic.regulators;

import raspberry.Acquaintance.ICurrentMeasurements;
import raspberry.Acquaintance.ISchedule;

public class RegulatorFacade {

    private ICurrentMeasurements currentMeasurementsFacade;
    private ISchedule schedule;

    public void injectCurrentMeasurementsFacade(ICurrentMeasurements currentMeasurementsFacade) {
        this.currentMeasurementsFacade = currentMeasurementsFacade;
    }

    public void injectSchedule(ISchedule schedule) {
        this.schedule = schedule;
    }
}
