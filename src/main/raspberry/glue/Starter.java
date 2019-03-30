package raspberry.glue;

import raspberry.communication.CommunicationFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;
import raspberry.logic.Interpreters.InterpreterFacade;
import raspberry.logic.OutFacadeLogic;
import raspberry.logic.RaspberryAPI;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.livedata.LiveDataGetterFacade;
import raspberry.logic.regulators.RegulatorFacade;
import raspberry.logic.schedule.Schedule;
import raspberry.logic.subscribers.SubscribersFacade;

public class Starter {

    private static LiveDataGetterFacade liveDataGetterFacade;
    private static RegulatorFacade regulatorFacade;
    private static SubscribersFacade subscribersFacade;
    private static RaspberryAPI raspberryAPI;

    public static void main(String[] args) {
        Starter.start();
    }

    public static void start() {
        raspberryAPI = new RaspberryAPI();
        OutFacadeLogic outFacadeLogic = OutFacadeLogic.getInstance();
        CommunicationFacade communicationFacade = new CommunicationFacade();

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
        InterpreterFacade interpreterFacade = new InterpreterFacade();

        liveDataGetterFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        subscribersFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectSchedule(schedule);

        interpreterFacade.injectSchedule(schedule);
        interpreterFacade.injectLiveDataGetter(liveDataGetterFacade);
        interpreterFacade.injectMeasurements(currentMeasurementsFacade);

        raspberryAPI.injectInterpreter(interpreterFacade);

        initializeLogic(interpreterFacade, liveDataGetterFacade, subscribersFacade, regulatorFacade);
    }

    private static void glueCommunication(CommunicationFacade communicationFacade){
        DatabaseConnectionFacade databaseConnectionFacade = new DatabaseConnectionFacade();
        GreenhouseConnectionFacade greenhouseConnectionFacade = new GreenhouseConnectionFacade();

        communicationFacade.injectDatabaseConnection(databaseConnectionFacade);
        communicationFacade.injectGreenhouse(greenhouseConnectionFacade);
    }

    private static void initializeLogic(InterpreterFacade interpreter, LiveDataGetterFacade liveDataGetterFacade, SubscribersFacade subscribersFacade, RegulatorFacade regulatorFacade){
        interpreter.initialize();
        liveDataGetterFacade.initialize();
        subscribersFacade.initialize();
        regulatorFacade.initialize();
    }

    private static void initializeCommunication(){

    }

    public static void stopThreads(){
        Starter.raspberryAPI.stopListening();
        Starter.liveDataGetterFacade.stopThreads();
        Starter.regulatorFacade.stopThreads();
        Starter.subscribersFacade.stopThreads();
    }

}
