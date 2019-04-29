package raspberry.Acquaintance;

import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;


public interface ICommunicationsFacade {

	IGreenhouseConnectionFacade getGreenhouseConnection();

	void insertLog(String greenhouseId, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel);


    boolean petWatchdog(String greenhouseID);

	void setServerInfo(String serverIP, int serverPort);

    boolean sendIPAddress(int currentPort, String greenhouseID);

    String startupMessage(String greenhouseID, int currentPort);
}

