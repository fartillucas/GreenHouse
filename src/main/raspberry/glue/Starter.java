package raspberry.glue;

import raspberry.communication.CommunicationFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;
import raspberry.logic.OutFacadeLogic;
import raspberry.logic.RaspberryAPI;

public class Starter {

    public static void main(String[] args) {
        Starter.start();
    }

    public static void start() {
        RaspberryAPI api = new RaspberryAPI();
        OutFacadeLogic outFacadeLogic = OutFacadeLogic.getInstance();
        CommunicationFacade communicationFacade = new CommunicationFacade();

        outFacadeLogic.injectCommunicationFacade(communicationFacade);

        glueCommunication(communicationFacade);

        api.initialise();
    }

    private static void glueLogic(){
    }

    private static void glueCommunication(CommunicationFacade communicationFacade){
        DatabaseConnectionFacade databaseConnectionFacade = new DatabaseConnectionFacade();
        GreenhouseConnectionFacade greenhouseConnectionFacade = new GreenhouseConnectionFacade();

        communicationFacade.injectDatabaseConnection(databaseConnectionFacade);
        communicationFacade.injectGreenhouse(greenhouseConnectionFacade);
    }
}
