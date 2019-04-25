package raspberry.logic;

import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;

public class OutFacadeLogic {

    private static OutFacadeLogic instance;
    private String greenhouseID = "standardGreenhouse";
    private String serverIP;
    private int serverPort;

    private ICommunicationsFacade communicationFacade;
    private int currentPort;

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

    public void uploadDatalog(Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel) {
        communicationFacade.insertLog(greenhouseID, timeOfReading, internalTemperature, extenalTemperature, humidity, waterlevel);
    }

    public boolean petWatchdog() {
        return communicationFacade.petWatchdog(greenhouseID);
    }

    public void setServerInfo(String serverIP, int serverPort) {
        communicationFacade.setServerInfo(serverIP, serverPort);
    }

    public boolean SendIPAddress() {
        return communicationFacade.sendIPAddress(this.currentPort, greenhouseID);
    }

    public void setCurrentServerPort(int port) {
        this.currentPort = port;
    }
}
