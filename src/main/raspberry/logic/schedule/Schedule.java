package raspberry.logic.schedule;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ISchedule;
import raspberry.logic.SetPoints;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Schedule implements ISchedule {

    private static Schedule instance;
    private double temperature;

    private List<HashMap<Integer, SetPoints>> schedule;
    private LocalDate appliedDate;


    public static ISchedule getInstance() {
        //TODO fix singleton vs interface

        if (Schedule.instance == null) {
            Schedule.instance = new Schedule();
        }

        return Schedule.instance;
    }

    private Schedule() {
    }

    @Override
    public ErrorCode apply(List<HashMap<Integer, SetPoints>> schedule) {
        if (schedule != null) {
            this.schedule = schedule;
            return ErrorCode.OK;
        } else {
            return ErrorCode.NOTAPPLIED;
        }
    }

    public static LocalDate getMeasurementDate() {
        return LocalDate.now();
    }

    private LocalTime getTimeOfDay () {
        return LocalTime.now();
    }

    public SetPoints getSetpoint(){
        LocalDate dateNow = getMeasurementDate();
        LocalTime currentTimeOfDay = getTimeOfDay();

        int minutes = currentTimeOfDay.getHour()*60+currentTimeOfDay.getMinute();
        int block = minutes/120;

        long index = DAYS.between(appliedDate, dateNow)%schedule.size();

        HashMap<Integer, SetPoints> day = schedule.get((int) index);

        SetPoints setpoints = day.get(block);

        return setpoints;
    }

    public double getTemperature() {
        SetPoints setPoints = this.getSetpoint();
        return setPoints.getTemperature();
    }
    public double getHumidity() {
        SetPoints setPoints = this.getSetpoint();
        return setPoints.getHumidity();
    }
    public double getWaterLevel() {
        SetPoints setPoints = this.getSetpoint();
        return setPoints.getWaterlevel();
    }

}
