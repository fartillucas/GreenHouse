package raspberry.Acquaintance;

import raspberry.communication.communicationAquaintance.IGreenhouseConnectionFacade;

import java.util.Date;


public interface ICommunications {

	IGreenhouseConnectionFacade getGreenhouseConnection();

	void insertLog(String greenhouseId, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel);


}

