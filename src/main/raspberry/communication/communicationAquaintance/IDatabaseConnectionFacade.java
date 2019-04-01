package raspberry.communication.communicationAquaintance;


import java.util.Date;

public interface IDatabaseConnectionFacade {

    void insertLog(String greenhouseId, Date timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel);

}
