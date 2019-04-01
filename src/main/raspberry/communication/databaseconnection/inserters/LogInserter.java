package raspberry.communication.databaseconnection.inserters;
/** Represents an Batch inserter
 * @author Michael P
 * @param BatchLogInserter constructor creates the batch containing batchid, Producttype, amount & defective.
 * @param insert method inserts the batch into the Batch table in the database.
 */
import raspberry.communication.databaseconnection.tools.DatabaseConnector;
import raspberry.communication.databaseconnection.tools.Insert;
import raspberry.communication.databaseconnection.tools.PrepareInfo;
import raspberry.communication.databaseconnection.tools.PrepareType;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogInserter{

    private String values;
    private String tables;
    private Connection connection;


    public LogInserter()  {
        this.values = "(?,?,?,?,?,?)";
        this.tables = "datalog(greenhouse_id, time_of_reading, internal_temperature, external_temperature, humidity, water_level)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(String greenhouseId,
                       Timestamp timeOfReading,
                       float internalTemperature,
                       float extenalTemperature,
                       float humidity,
                       float waterlevel) {
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, greenhouseId));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, timeOfReading));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.FLOAT, internalTemperature));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.FLOAT, extenalTemperature));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.FLOAT, humidity));
        wildCardInfo.add(new PrepareInfo(6, PrepareType.FLOAT, waterlevel));

        new Insert().insertion(connection, tables, values, wildCardInfo);


        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

