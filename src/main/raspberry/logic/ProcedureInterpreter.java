package raspberry.logic;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.logic.schedule.Schedule;

import java.util.HashMap;
import java.util.List;

public class ProcedureInterpreter {

    private ScheduleInterpreter scheduleInterpreter;

    public ProcedureInterpreter(){
        this.scheduleInterpreter = new ScheduleInterpreter();
    }

    public ErrorCode interpret(String message) {

        JSONObject jsonMessage = new JSONObject(message);

        String procedure = jsonMessage.getString("procedure");

        switch(procedure){
            case "applySchedule":
                List<HashMap<Integer, SetPoints>> schedule = null;

                try {
                    schedule = this.scheduleInterpreter.interpret(jsonMessage);
                    return Schedule.getInstance().apply(schedule);
                } catch (Exception e){
                    return ErrorCode.WRONGFORMAT;
                }

            case "getLiveData":
                return ErrorCode.NOTAPPLIED;
            default:
                return ErrorCode.UNDEFINEDPROCEDURE;
        }
    }
}
