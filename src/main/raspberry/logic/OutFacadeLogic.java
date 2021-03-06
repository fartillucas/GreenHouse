package raspberry.logic;

import raspberry.Acquaintance.GreenhouseInfoEnum;
import raspberry.Acquaintance.ICommunicationsFacade;
import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;

public class OutFacadeLogic {

    private static OutFacadeLogic instance;
    private String greenhouseID = GreenhouseInfoEnum.GREENHOUSEINFO.getName();
    private String serverIP;
    private int serverPort;

    private ICommunicationsFacade communicationFacade;
    private int currentPort = GreenhouseInfoEnum.GREENHOUSEINFO.getPort();

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

    public void uploadDatalog(Date timeOfReading, Double internalTemperature, Double extenalTemperature, Double humidity, Double waterlevel) {
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

    public String startupMessage() {
        return this.communicationFacade.startupMessage(greenhouseID, this.currentPort);
    }

    public int getRaspberryPort(){
        return this.currentPort;
    }
}
