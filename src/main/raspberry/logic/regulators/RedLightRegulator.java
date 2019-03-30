package raspberry.logic.regulators;

import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.ReadableSetpoints;
import raspberry.logic.OutFacadeLogic;

import static java.lang.Thread.sleep;

public class RedLightRegulator implements Runnable{

    private boolean continueRunning;
    private Integer lastSetpoint;

    private ISchedule schedule;

    public RedLightRegulator(ISchedule schedule){
        this.schedule = schedule;
        this.lastSetpoint = null;
        continueRunning = true;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && continueRunning) {
            try {
                ReadableSetpoints setPoints = schedule.getSetpoints();
                Integer scheduleRedLightLevel = setPoints.getRedLight();

                if(scheduleRedLightLevel == null) {
                    regulate(0);
                } else if (scheduleRedLightLevel.equals(lastSetpoint)) {
                    regulate(scheduleRedLightLevel);
                }

                this.lastSetpoint = scheduleRedLightLevel;

                sleep(1000);
            } catch (InterruptedException e) {
                continueRunning = false;
            }
        }
    }

    private void regulate (int level)  {
        OutFacadeLogic.getInstance().getGreenhouseConnection().setRedLight(level);
    }
}
