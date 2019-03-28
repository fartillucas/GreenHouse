package raspberry.logic;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;
import raspberry.communication.greenhouseconnection.GreenhouseConnectionFacade;

public class OutFacadeLogic {

    private static OutFacadeLogic instance;

    private ICommunicationsFacade communicationFacade;

    public static OutFacadeLogic getInstance() {
        if (OutFacadeLogic.instance == null) {
            OutFacadeLogic.instance = new OutFacadeLogic();
        }
        return OutFacadeLogic.instance;
    }

    public void injectCommunicationFacade(ICommunicationsFacade communicationFacade) {
        this.communicationFacade = communicationFacade;
    }

    public IGreenhouseConnectionFacade getGreenhouseConnection() {
        return this.communicationFacade.getGreenhouseConnection();
    }

}
