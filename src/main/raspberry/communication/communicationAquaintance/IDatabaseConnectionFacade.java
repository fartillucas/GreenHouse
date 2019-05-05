package raspberry.communication.communicationAquaintance;


import java.util.Date;

public interface IDatabaseConnectionFacade {

    void insertLog(String greenhouseId, Date timeOfReading, Double internalTemperature, Double extenalTemperature, Double humidity, Double waterlevel);

}
