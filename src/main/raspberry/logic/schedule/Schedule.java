package raspberry.logic.schedule;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ISchedule;
import raspberry.logic.SetPoints;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Schedule implements ISchedule {
    //TODO doesn't yet need a facade??

//    private static Schedule instance;

    private List<HashMap<Integer, SetPoints>> schedule;
    private LocalDate appliedDate;


//    public static ISchedule getInstance() {
//        //TODO fix singleton vs interface
//
//        if (Schedule.instance == null) {
//            Schedule.instance = new Schedule();
//        }
//
//        return Schedule.instance;
//    }

    public Schedule() {
    }

    @Override
    public ErrorCode apply(List<HashMap<Integer, SetPoints>> schedule) {
        if (schedule != null) {
            this.schedule = schedule;
            this.appliedDate = getMeasurementDate();
            return ErrorCode.OK;
        } else {
            return ErrorCode.NOTAPPLIED;
        }
    }

    private LocalDate getMeasurementDate() {
        return LocalDate.now();
    }

    private LocalTime getTimeOfDay () {
        return LocalTime.now();
    }

    @Override
    public SetPoints getSetpoints(){
        LocalDate dateNow = getMeasurementDate();
        LocalTime currentTimeOfDay = getTimeOfDay();

        int minutes = currentTimeOfDay.getHour()*60+currentTimeOfDay.getMinute();
        int block = minutes/120;

        long index = DAYS.between(appliedDate, dateNow)%schedule.size();

        HashMap<Integer, SetPoints> day = schedule.get((int) index);

        SetPoints setpoints = day.get(block);

        return setpoints;
    }

    @Override
    public double getTemperature() {
        SetPoints setPoints = this.getSetpoints();
        return setPoints.getTemperature();
    }

    @Override
    public double getHumidity() {
        SetPoints setPoints = this.getSetpoints();
        return setPoints.getHumidity();
    }

    @Override
    public double getWaterLevel() {
        SetPoints setPoints = this.getSetpoints();
        return setPoints.getWaterlevel();
    }

    @Override
    public int getBlueLight() {
        SetPoints setPoints = this.getSetpoints();
        return setPoints.getBlueLight();
    }

    @Override
    public int getRedLight() {
        SetPoints setPoints = this.getSetpoints();
        return setPoints.getRedLight();
    }

}
