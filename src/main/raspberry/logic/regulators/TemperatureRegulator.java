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

                if (scheduleTemp != null){
                    if (scheduleTemp != lastSetpoint) {
                        regulate(scheduleTemp.intValue());
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