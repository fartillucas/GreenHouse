package raspberry.logic;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;

public class ProcedureInterpreter {
    public ErrorCode interpret(String message) {

        JSONObject jsonObj = new JSONObject(message);

        String procedure = jsonObj.getString("procedure");
        System.out.println("Client procedure: "+procedure);

        switch(procedure){
            case "applySchedule":
                return ErrorCode.OK;
            case "getLiveData":
                return ErrorCode.NOTAPPLIED;
            default:
                return ErrorCode.UNDEFINEDPROCEDURE;
        }
    }
}
