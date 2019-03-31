package raspberry.communication.communicationAquaintance;

import java.sql.Timestamp;

public interface IDatabaseConnectionFacade {

    void insertLog(String greenhouseId, Timestamp timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel);

}
