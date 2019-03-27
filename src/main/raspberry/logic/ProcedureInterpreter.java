package raspberry.logic;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.logic.livedata.LiveDataGetterFacade;
import raspberry.logic.schedule.Schedule;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.List;

public class ProcedureInterpreter {

    private ScheduleInterpreter scheduleInterpreter;
    private GetLiveDataInterpreter liveDataInterpreter;

    public ProcedureInterpreter(){
        this.scheduleInterpreter = new ScheduleInterpreter();
        this.liveDataInterpreter = new GetLiveDataInterpreter();
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
                try {
                    IPAddressPort connectionIformation = this.liveDataInterpreter.Interpret(jsonMessage);
                    return LiveDataGetterFacade.getInstance().setConnection(connectionIformation);
                } catch (InvalidIPAddressException e) {
                    return ErrorCode.INVALIDIPADDRESS;
                } catch (Exception e) {
                    return ErrorCode.WRONGFORMAT;
                }

            default:
                return ErrorCode.UNDEFINEDPROCEDURE;
        }
    }
}
