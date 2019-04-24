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

public class UploadData {
    String databaseID = "test_db";
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
        databaseConnectionFacade.insertLog(databaseID, timestamp, internalTemperature, externalTemperature, humditiy, waterlevel);
    }

    @Then("^Data is successfully stored on database$")
    public void dataIsSuccessfullyStoredOnDatabase() throws Throwable {

        //TODO find out how this should be tested...
        Connection databaseConnection = new DatabaseConnector().openConnection();
        Statement statement = databaseConnection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM datalog");


        assertEquals(databaseID, result.getString(1));
        assertEquals(timestamp, result.getTimestamp(2));
        assertEquals(internalTemperature, result.getFloat(3));
        assertEquals(externalTemperature, result.getFloat(4));
        assertEquals(humditiy, result.getFloat(5));
        assertEquals(waterlevel, result.getFloat(6));
    }
}