package uploaddata;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.databaseconnection.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class UploadData {
    String greenhouseID = "test_greenhouse";
    Timestamp timestamp =  new Timestamp(new Date().getTime());
    double internalTemperature = 0;
    double externalTemperature = 0;
    double humditiy = 0;
    double waterlevel = 0;

    IDatabaseConnectionFacade databaseConnectionFacade;

    @Given("^There is a connection to the database$")
    public void thereIsAConnectionToTheDatabase() throws Throwable {
        // TODO make a connection
        databaseConnectionFacade = new DatabaseConnectionFacade();

    }

    @And("^the system is initialized here too$")
    public void theSystemIsInitializedHereToo() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //TODO whatever
    }

    @When("^It is time to upload data$")
    public void itIsTimeToUploadData() throws Throwable {
        databaseConnectionFacade.insertLog(greenhouseID, timestamp, internalTemperature, externalTemperature, humditiy, waterlevel);
    }

    @Then("^Data is successfully stored on database$")
    public void dataIsSuccessfullyStoredOnDatabase() throws Throwable {
        Connection databaseConnection = new DatabaseConnector().openConnection();
        Statement statement = databaseConnection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM datalog where greenhouse_id = '" + greenhouseID + "'");
        try {


            result.next();
            assertEquals(greenhouseID, result.getString("greenhouse_id"));
            assertTrue( result.getTimestamp("time_of_reading").getTime()-1000 < timestamp.getTime() || timestamp.getTime() < result.getTimestamp("time_of_reading").getTime());
            assertEquals(internalTemperature, result.getFloat("internal_temperature"), 0.1);
            assertEquals(externalTemperature, result.getFloat("external_temperature"), 0.1);
            assertEquals(humditiy, result.getFloat("humidity"), 0.1);
            assertEquals(waterlevel, result.getFloat("water_level"), 0.1);
        } catch (Exception e)
        {

        }
        finally {
            statement.execute("DELETE FROM datalog where greenhouse_id = '" + greenhouseID + "'");
        }
    }

}
