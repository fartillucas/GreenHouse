package raspberry.logic.schedule;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ISchedule;

import java.util.List;

public class Schedule implements ISchedule {


    @Override
    public ErrorCode apply(List<String> data) {
        return ErrorCode.NOTAPPLIED;
    }
}
