package LogicTests;

import Mocks.Mocks.ServerMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import raspberry.Acquaintance.ErrorCode;
import raspberry.glue.Starter;

import java.net.BindException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class GetLiveData {

    private Thread currentSystem = null;
    private String JSONmessage;
    private ServerMock serverMock;
    private int port;

    @Given("^the server exists$")
    public void theServerExists() throws Throwable {

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

    @And("^the system is started$")
    public void theSystemIsStarted() throws Throwable {
        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.setDaemon(true);
        this.currentSystem.start();

        sleep(1000);
    }

    @Given("^a valid subscription request$")
    public void aValidSubscriptionRequest() throws Throwable{
        JSONmessage = "{\"procedure\": \"getLiveData\"," +
                "\"IPAddress\":\"127.0.0.1\"," +
                "\"port\":"+this.port+"}";

    }

    @When("^server side sends subscription request$")
    public void serverSideSendsSubscriptionRequest() throws Throwable {
        serverMock.sendMessage(JSONmessage,0);
    }

    @Then("^the subscriber is subscribed$")
    public void theSubscriberIsSubscribed() throws Throwable {
        sleep(3000);
        boolean connected = serverMock.isDataListenAlive();

        assertTrue(connected);
    }

    @And("^greenhouse sends internal environment measurements every cycle$")
    public void greenhouseSendsInternalEnvironmentMeasurementsEveryCycle() throws Throwable {
        try{
            JSONObject measurements = serverMock.readLiveData();

            if(!measurements.isNull("InternalTemp")) {
                measurements.getDouble("InternalTemp");
            }

            if (!measurements.isNull("ExternalTemp")) {
                measurements.getDouble("ExternalTemp");
            }

            if (!measurements.isNull("Humidity")) {
                measurements.getDouble("Humidity");
            }

            if (!measurements.isNull("WaterLevel")) {
                measurements.getDouble("WaterLevel");
            }
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }

    @Given("^a invalid subscription request$")
    public void aInvalidSubscriptionRequest() throws Throwable {
        JSONmessage = "{\"procedure\": \"getLiveData\"," +
                "\"IPAdress\":\"127.0.8588.1\"," +
                "\"port\":\"809a\"}";
    }

    @Then("^the new subscription is not subscribed$")
    public void theNewSubscriptionIsNotSubscribed() throws Throwable {
        boolean connected = serverMock.isDataListenAlive();

        assertFalse(connected);
    }

    @And("^error is sent$")
    public void errorIsSent() throws Throwable {
        try {
            ErrorCode error = serverMock.getReplyStatus();

            assertFalse(error.equals(ErrorCode.OK));
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }

    private void resetThreads(ServerMock serverMock){
        serverMock.stopThreads();

        Starter.stopThreads();
    }

}
