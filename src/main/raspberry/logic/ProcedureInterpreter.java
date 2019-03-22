package raspberry.logic;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;

public class ProcedureInterpreter {
    private ScheduleInterpreter scheduleInterpreter;

    public ProcedureInterpreter(){
        scheduleInterpreter = new ScheduleInterpreter();
    }

    public ErrorCode interpret(String message) {

        JSONObject jsonObj = new JSONObject(message);

        String procedure = jsonObj.getString("procedure");
        System.out.println("Client procedure: "+procedure);
        System.out.println(jsonObj.toString());

        switch(procedure){
            case "applySchedule":
                scheduleInterpreter.interpret(jsonObj);
                return ErrorCode.OK;
            case "getLiveData":
                return ErrorCode.NOTAPPLIED;
            default:
                return ErrorCode.UNDEFINEDPROCEDURE;
        }
    }
}
