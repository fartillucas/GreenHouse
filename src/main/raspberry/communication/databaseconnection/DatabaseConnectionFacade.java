package raspberry.communication.databaseconnection;

import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.databaseconnection.inserters.LogInserter;

import java.sql.Timestamp;
import java.util.Date;

public class DatabaseConnectionFacade implements IDatabaseConnectionFacade {
    @Override
    public void insertLog(String greenhouseId, Date timeOfReading, Double internalTemperature, Double extenalTemperature, Double humidity, Double waterlevel) {
       // new LogInserter().insert(greenhouseId, new Timestamp(timeOfReading.getTime()), (float)internalTemperature, (float)extenalTemperature, (float)humidity, (float)waterlevel);
    }
}
