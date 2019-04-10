package raspberry.glue;

import raspberry.communication.Communication;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;
import raspberry.logic.Interpreters.InterpreterFacade;
import raspberry.logic.OutFacadeLogic;
import raspberry.logic.RaspberryAPI;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.datalogger.DataloggerFacade;
import raspberry.logic.livedata.LiveDataGetterFacade;
import raspberry.logic.regulators.RegulatorFacade;
import raspberry.logic.schedule.Schedule;
import raspberry.logic.subscribers.SubscribersFacade;

public class Starter {

    private static LiveDataGetterFacade liveDataGetterFacade;
    private static RegulatorFacade regulatorFacade;
    private static SubscribersFacade subscribersFacade;
    private static RaspberryAPI raspberryAPI;
    private static DataloggerFacade dataloggerFacade;

    public static void main(String[] args) {
        Starter.start();
    }

    public static void start() {
        raspberryAPI = new RaspberryAPI();
        OutFacadeLogic outFacadeLogic = OutFacadeLogic.getInstance();
        Communication communicationFacade = new Communication();

        outFacadeLogic.injectCommunicationFacade(communicationFacade);

        glueCommunication(communicationFacade);
        glueLogic(raspberryAPI);

        initializeCommunication();

        raspberryAPI.initialise();
    }

    private static void glueLogic(RaspberryAPI raspberryAPI){
        CurrentMeasurementsFacade currentMeasurementsFacade = new CurrentMeasurementsFacade();
        liveDataGetterFacade = new LiveDataGetterFacade();
        regulatorFacade = new RegulatorFacade();
        subscribersFacade = new SubscribersFacade();
        Schedule schedule = new Schedule();
        dataloggerFacade = new DataloggerFacade();
        InterpreterFacade interpreterFacade = new InterpreterFacade();

        liveDataGetterFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        subscribersFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectSchedule(schedule);
        dataloggerFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);

        interpreterFacade.injectSchedule(schedule);
        interpreterFacade.injectLiveDataGetter(liveDataGetterFacade);
        interpreterFacade.injectMeasurements(currentMeasurementsFacade);

        raspberryAPI.injectInterpreter(interpreterFacade);

        initializeLogic(interpreterFacade, liveDataGetterFacade, subscribersFacade, regulatorFacade, dataloggerFacade);
    }

    private static void glueCommunication(Communication communicationFacade){
        DatabaseConnectionFacade databaseConnectionFacade = new DatabaseConnectionFacade();
        GreenhouseConnectionFacade greenhouseConnectionFacade = new GreenhouseConnectionFacade();

        communicationFacade.injectDatabaseConnection(databaseConnectionFacade);
        communicationFacade.injectGreenhouse(greenhouseConnectionFacade);

    }

    private static void initializeLogic(InterpreterFacade interpreter, LiveDataGetterFacade liveDataGetterFacade, SubscribersFacade subscribersFacade, RegulatorFacade regulatorFacade, DataloggerFacade dataloggerFacade){
        interpreter.initialize();
        liveDataGetterFacade.initialize();
        subscribersFacade.initialize();
        regulatorFacade.initialize();
        dataloggerFacade.initialize();

    }

    private static void initializeCommunication(){
    }

    public static void stopThreads(){
        Starter.raspberryAPI.stopListening();
        Starter.liveDataGetterFacade.stopThreads();
        Starter.regulatorFacade.stopThreads();
        Starter.subscribersFacade.stopThreads();
        Starter.dataloggerFacade.stopThreads();
    }

}
