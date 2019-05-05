package LogicTests;

import Mocks.Mocks.GreenhouseMock;
import Mocks.Mocks.ServerMock;
import com.sun.security.ntlm.Server;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import raspberry.communication.communicationAquaintance.IDatabaseConnectionFacade;
import raspberry.communication.databaseconnection.DatabaseConnectionFacade;
import raspberry.communication.databaseconnection.tools.DatabaseConnector;
import raspberry.glue.Starter;

import java.net.BindException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class UploadData {
    String greenhouseID = "standardGreenhouse";
    Timestamp timestamp =  new Timestamp(new Date().getTime());
    double internalTemperature = 0;
    double externalTemperature = 0;
    double humditiy = 0;
    double waterlevel = 0;

    IDatabaseConnectionFacade databaseConnectionFacade;
    private int port;
    private ServerMock serverMock;
    private Thread currentSystem;

    @Given("^There is a server$")
    public void thereIsAServer() throws Throwable {
        //this.resetThreads(serverMock);
        port = 8090;

        this.serverMock = null;

        while (this.serverMock == null) {
            try{
                this.serverMock = new ServerMock(port);
            } catch (BindException e) {
                port++;
            }
        }

        sleep(1000);
    }

    @And("^the system is initialized here too$")
    public void theSystemIsInitializedHereToo() throws Throwable {
        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.setDaemon(true);
        this.currentSystem.start();

        sleep(1000);
    }

    @And("^the greenhouse measurements are zero$")
    public void theGreenhouseMeasurementsAreZero() throws Throwable {
        GreenhouseMock.getInstance().setHumidity(humditiy);
        GreenhouseMock.getInstance().setWaterLevel(waterlevel);
        GreenhouseMock.getInstance().setInternalTemperature(internalTemperature);
        GreenhouseMock.getInstance().setExternalTemperature(externalTemperature);
    }

    @When("^It is time to upload data$")
    public void itIsTimeToUploadData() throws Throwable {
        sleep(16000);
    }

    @Then("^Data is successfully sent to the server$")
    public void dataIsSuccessfullySentToTheServer() throws Throwable {
        try {
            boolean recievedDatalog = this.serverMock.isReceivedDatalog();
            assertTrue(recievedDatalog);

            JSONObject datalog = this.serverMock.getDatalogMessage();

            assertEquals(greenhouseID, datalog.getString("greenhouseID"));
            assertTrue( datalog.getLong("time of Reading")-20000 < timestamp.getTime() || timestamp.getTime() < datalog.getLong("time of Reading"));
            assertEquals(internalTemperature,  datalog.getDouble("internal temperature"), 0.1);
            assertEquals(externalTemperature,  datalog.getDouble("extenal temperature"), 0.1);
            assertEquals(humditiy,  datalog.getDouble("humidity"), 0.1);
            assertEquals(waterlevel,  datalog.getDouble("waterlevel"), 0.1);
        } finally {
            this.resetThreads(serverMock);
        }
    }

    private void resetThreads(ServerMock serverMock){
        serverMock.stopThreads();

        Starter.stopThreads();

        GreenhouseMock.getInstance().clear();
    }


}
