package derp;

import Mocks.ServerMock;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import raspberry.logic.Starter;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;

public class ApplySchedule {

    @Given("^that there is a server$")
    public void thatThereIsAServer() throws Throwable {
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

    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {
        Thread thread = new Thread(()->{
            Starter.start();
        });
        thread.setName("Client");
        thread.start();

        sleep(1000);
    }

    @When("^a valid schedule is received$")
    public void aValidScheduleIsReceived() throws Throwable {
        String JSONMessage = "{\"procedure\":\"applySchedule\",\"phonetype\":\"N95\",\"cat\":\"WP\"}";
        ServerMock.getInstance().sendMessage(JSONMessage, 0);
    }

    @Then("^the schedule is saved in the system$")
    public void theScheduleIsSavedInTheSystem() throws Throwable {
        boolean success = ServerMock.getInstance().getReplyStatus();

        assertTrue(success);
    }

    @And("^a schedule is in use$")
    public void aScheduleIsInUse() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the new schedule is saved in the system$")
    public void theNewScheduleIsSavedInTheSystem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^an invalid schedule is received$")
    public void anInvalidScheduleIsReceived() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the schedule s not saved in the system$")
    public void theScheduleSNotSavedInTheSystem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^an error is returned$")
    public void anErrorIsReturned() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
