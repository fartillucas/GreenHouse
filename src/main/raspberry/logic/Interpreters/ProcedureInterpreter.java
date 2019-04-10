package raspberry.logic.Interpreters;

import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.ILiveDataGetter;
import raspberry.Acquaintance.ISchedule;
import raspberry.Acquaintance.IWatchdogPetterFacade;
import raspberry.logic.IPAddressPort;
import raspberry.logic.InvalidIPAddressException;
import raspberry.logic.SetPoints;
import raspberry.logic.livedata.LiveDataGetterFacade;
import raspberry.logic.schedule.Schedule;

import java.util.HashMap;
import java.util.List;

public class ProcedureInterpreter {

    private ScheduleInterpreter scheduleInterpreter;
    private GetLiveDataInterpreter liveDataInterpreter;
    private ISchedule scheduleFacade;
    private ILiveDataGetter liveDataGetter;
    private IWatchdogPetterFacade watchdogPetterFacade;

    public ProcedureInterpreter(ISchedule scheduleFacade, ILiveDataGetter liveDataGetter, IWatchdogPetterFacade watchdogPetterFacade){
        this.watchdogPetterFacade = watchdogPetterFacade;
        this.scheduleInterpreter = new ScheduleInterpreter();
        this.liveDataInterpreter = new GetLiveDataInterpreter();
        this.scheduleFacade = scheduleFacade;
        this.liveDataGetter = liveDataGetter;
    }

    public ErrorCode interpret(String message) {
        //TODO should the interpreter also dispatch the message?

        JSONObject jsonMessage = new JSONObject(message);

        String procedure = jsonMessage.getString("procedure");

        switch(procedure){
            case "applySchedule":
                try {
                    List<HashMap<Integer, SetPoints>> schedule = this.scheduleInterpreter.interpret(jsonMessage);
                    return scheduleFacade.apply(schedule);
                } catch (Exception e){
                    return ErrorCode.WRONGFORMAT;
                }
            case "getLiveData":
                try {
                    IPAddressPort connectionIformation = this.liveDataInterpreter.Interpret(jsonMessage);
                    return this.liveDataGetter.setConnection(connectionIformation);
                } catch (InvalidIPAddressException e) {
                    return ErrorCode.INVALIDIPADDRESS;
                } catch (Exception e) {
                    return ErrorCode.WRONGFORMAT;
                }
            case "retryConnection":
                if(watchdogPetterFacade.restartWatchdogPetter()) {
                    return ErrorCode.OK;
                }
                return ErrorCode.NOTAPPLIED;
            default:
                return ErrorCode.UNDEFINEDPROCEDURE;
        }
    }
}
