package raspberry.logic;

import raspberry.Acquaintance.ICommunications;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;

public class OutFacadeLogic {

    private static OutFacadeLogic instance;
    private String greenhouseID = "standardGreenhouse";

    private ICommunications communicationFacade;

    public static OutFacadeLogic getInstance() {
        if (OutFacadeLogic.instance == null) {
            OutFacadeLogic.instance = new OutFacadeLogic();
        }
        return OutFacadeLogic.instance;
    }



    public void injectCommunicationFacade(ICommunications communicationFacade) {
        this.communicationFacade = communicationFacade;
    }

    public IGreenhouseConnectionFacade getGreenhouseConnection() {
        return this.communicationFacade.getGreenhouseConnection();
    }

    public void uploadDatalog(Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel) {
        communicationFacade.insertLog(greenhouseID, timeOfReading, internalTemperature, extenalTemperature, humidity, waterlevel);
    }
}
