package raspberry.logic.schedule;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ISchedule;
import raspberry.logic.SetPoints;

import java.util.HashMap;
import java.util.List;

public class Schedule implements ISchedule {

    private static Schedule instance;

    private List<HashMap<Integer, SetPoints>> schedule;

    public static ISchedule getInstance(){
        //TODO fix singleton vs interface

        if (Schedule.instance == null){
            Schedule.instance = new Schedule();
        }

        return Schedule.instance;
    }

    private Schedule(){
    }

    @Override
    public ErrorCode apply(List<HashMap<Integer, SetPoints>> schedule) {
        if (schedule != null){
            this.schedule = schedule;
            return ErrorCode.OK;
        } else {
            return ErrorCode.NOTAPPLIED;
        }
    }
}
