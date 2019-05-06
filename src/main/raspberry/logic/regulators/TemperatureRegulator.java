package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class TemperatureRegulator implements Runnable {

    private boolean continueRunning;
    private ISchedule schedule;

	private Double lastSetpoint;

	public TemperatureRegulator(ISchedule schedule){
		this.schedule = schedule;
		this.lastSetpoint = null;
		continueRunning = true;
	}

	@Override
	public void run() {
        while (!Thread.interrupted() && continueRunning) {
            try {
                ReadableSetpoints setPoints = schedule.getSetpoints();
                Double scheduleTemp = setPoints.getTemperature();

//			Double currentTemp = CurrentMeasurementsFacade.getInstance().getInternalTemperature();
                if (scheduleTemp != null){
                    if (scheduleTemp != lastSetpoint) {
                        //TODO if we can not control the heater, but only a temperature setpoint, then the comparison doesn't make sense
//				if (currentTemp<scheduleTemp) {
                        regulate(scheduleTemp.intValue());
//				}
                    }
                }

                this.lastSetpoint = scheduleTemp;

                sleep(1000);
            } catch (InterruptedException e) {
                continueRunning = false;
            } catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

	private void regulate(int kelvin) {
		OutFacadeLogic.getInstance().getGreenhouseConnection().setTemperature(kelvin);
	}
}