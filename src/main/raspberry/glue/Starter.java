package raspberry.glue;

import raspberry.Acquaintance.ErrorCode;
import raspberry.Acquaintance.IWebAppConnectionFacade;
import raspberry.communication.CommunicationFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;
import raspberry.communication.webappconnection.WebAppConnectionFacade;
import raspberry.logic.Interpreters.InterpreterFacade;
import raspberry.logic.OutFacadeLogic;
import raspberry.logic.RaspberryAPI;
import raspberry.logic.currentmeasurements.CurrentMeasurementsFacade;
import raspberry.logic.datalogger.DataloggerFacade;
import raspberry.logic.livedata.LiveDataGetterFacade;
import raspberry.logic.regulators.RegulatorFacade;
import raspberry.logic.schedule.Schedule;
import raspberry.logic.subscribers.SubscribersFacade;
import raspberry.logic.watchdogpetter.WatchdogPetterFacade;

public class Starter {

    private static LiveDataGetterFacade liveDataGetterFacade;
    private static RegulatorFacade regulatorFacade;
    private static SubscribersFacade subscribersFacade;
    private static RaspberryAPI raspberryAPI;
    private static DataloggerFacade dataloggerFacade;
    private static WatchdogPetterFacade watchdogPetterFacade;


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
        dataloggerFacade = new DataloggerFacade();
        InterpreterFacade interpreterFacade = new InterpreterFacade();
        watchdogPetterFacade = new WatchdogPetterFacade();

        liveDataGetterFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        subscribersFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);
        regulatorFacade.injectSchedule(schedule);
        dataloggerFacade.injectCurrentMeasurementsFacade(currentMeasurementsFacade);


        interpreterFacade.injectSchedule(schedule);
        interpreterFacade.injectLiveDataGetter(liveDataGetterFacade);
        interpreterFacade.injectMeasurements(currentMeasurementsFacade);
        interpreterFacade.injectWatchdogFacade(watchdogPetterFacade);

        raspberryAPI.injectInterpreter(interpreterFacade);

        initializeLogic(interpreterFacade, liveDataGetterFacade, subscribersFacade, regulatorFacade, dataloggerFacade, watchdogPetterFacade);
    }

    private static void glueCommunication(CommunicationFacade communicationFacade){
        DatabaseConnectionFacade databaseConnectionFacade = new DatabaseConnectionFacade();
        GreenhouseConnectionFacade greenhouseConnectionFacade = new GreenhouseConnectionFacade();
        IWebAppConnectionFacade webAppConnectionFacade = new WebAppConnectionFacade();

        communicationFacade.injectDatabaseConnection(databaseConnectionFacade);
        communicationFacade.injectGreenhouse(greenhouseConnectionFacade);
        communicationFacade.injectWebAppConnectionFacade(webAppConnectionFacade);

    }

    private static void initializeLogic(InterpreterFacade interpreter, LiveDataGetterFacade liveDataGetterFacade, SubscribersFacade subscribersFacade, RegulatorFacade regulatorFacade, DataloggerFacade dataloggerFacade, WatchdogPetterFacade watchdogPetterFacade){
        interpreter.initialize();
        liveDataGetterFacade.initialize();
        subscribersFacade.initialize();
        regulatorFacade.initialize();
        dataloggerFacade.initialize();
        watchdogPetterFacade.initialize();

    }

    private static void initializeCommunication(){
    }

    public static void stopThreads(){
        Starter.raspberryAPI.stopListening();
        Starter.liveDataGetterFacade.stopThreads();
        Starter.regulatorFacade.stopThreads();
        Starter.subscribersFacade.stopThreads();
        Starter.dataloggerFacade.stopThreads();
        Starter.watchdogPetterFacade.stopThreads();
    }

}
