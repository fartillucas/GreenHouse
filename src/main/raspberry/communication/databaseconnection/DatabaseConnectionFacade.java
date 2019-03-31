package raspberry.communication.databaseconnection;

import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.databaseconnection.inserters.LogInserter;

import java.sql.Timestamp;

public class DatabaseConnectionFacade implements IDatabaseConnectionFacade {
    @Override
    public void insertLog(String greenhouseId, Timestamp timeOfReading, double internalTemperature, double extenalTemperature, double humidity, double waterlevel) {
        new LogInserter().insert(greenhouseId, timeOfReading, (float)internalTemperature, (float)extenalTemperature, (float)humidity, (float)waterlevel);
    }
}
