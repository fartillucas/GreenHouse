package derp;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import raspberry.logic.Starter;

import java.io.PrintWriter;
import java.net.Socket;

public class ApplySchedule {
    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {

        Thread starter = new Thread(

        );
        Starter.start();
    }

    @When("^a valid schedule is received$")
    public void aValidScheduleIsReceived() throws Throwable {

        Socket socket = new Socket("localhost",8080);

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");

        writer.print(jsonObj.toString());

    }

    @Then("^the schedule is saved in the system$")
    public void theScheduleIsSavedInTheSystem() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
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
