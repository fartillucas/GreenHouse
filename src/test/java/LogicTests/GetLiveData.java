package LogicTests;

import Mocks.ServerMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import raspberry.logic.Starter;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;


public class GetLiveData {

    private Thread currentSystem = null;
    private String JSONmessage;

    @Given("^the server exists$")
    public void theServerExists() throws Throwable {
        Thread starter = new Thread(() -> {
            try {
                ServerMock.getInstance().listenForConnections();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        starter.setName("ServerMock");
        starter.start();

        sleep(1000);
    }

    @And("^the system is started$")
    public void theSystemIsStarted() throws Throwable {
        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.start();

        sleep(1000);
    }

    @Given("^a valid subscription request$")
    public void aValidSubscriptionRequest() throws Throwable{
         JSONmessage = "{\"procedure\": \"getLiveData\"," +
                "\"IPAdress\":\"127.0.0.1\"," +
                "\"port\":\"8090\"}";

    }

    @When("^server side sends subscription request$")
    public void serverSideSendsSubscriptionRequest() throws Throwable {
        ServerMock.getInstance().sendMessage(JSONmessage,0);
    }

    @Then("^the subscriber is subscribed$")
    public void theSubscriberIsSubscribed() throws Throwable {
        boolean connected = ServerMock.getInstance().isDataListenAlive();

        assertTrue(connected);
    }

    @And("^greenhouse sends internal environment measurements every cycle$")
    public void greenhouseSendsInternalEnvironmentMeasurementsEveryCycle() throws Throwable {
        JSONObject measurements = ServerMock.getInstance().readLiveData();
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
    }

    @Given("^a invalid subscription request$")
    public void aInvalidSubscriptionRequest() {

    }

    @Then("^the new subscription is not subscribed$")
    public void theNewSubscriptionIsNotSubscribed() {

    }

    @And("^error is sent$")
    public void errorIsSent() {

    }

    @And("^greenhouse doesn't send internal environment to new subscriber every cycle$")
    public void greenhouseDoesnTSendInternalEnvironmentToNewSubscriberEveryCycle() {
    }
}
