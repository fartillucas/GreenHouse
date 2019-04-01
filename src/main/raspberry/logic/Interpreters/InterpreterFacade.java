package raspberry.logic.Interpreters;

import raspberry.Acquaintance.*;

public class InterpreterFacade implements IInterpreter {

    private ICurrentMeasurementsFacade measurements;
    private ILiveDataGetter liveDataGetter;
    private ISchedule schedule;

    private ProcedureInterpreter interpreter;

    public InterpreterFacade() {
    }

    @Override
    public ErrorCode interpret(String message) {
        return this.interpreter.interpret(message);
    }

    public void injectMeasurements(ICurrentMeasurementsFacade measurements) {
        this.measurements = measurements;
    }

    public void injectLiveDataGetter(ILiveDataGetter liveDataGetter) {
        this.liveDataGetter = liveDataGetter;
    }

    public void injectSchedule(ISchedule schedule) {
        this.schedule = schedule;
    }

    public void initialize(){
        this.interpreter = new ProcedureInterpreter(schedule, liveDataGetter);
    }
}
