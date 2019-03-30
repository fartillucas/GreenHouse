package LogicTests;

import Mocks.Mocks.ServerMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import raspberry.Acquaintance.ErrorCode;
import raspberry.glue.Starter;

import java.net.BindException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ApplySchedule {

    private String testSchedule ="{	\"procedure\": \"applySchedule\",\"startdate\" : \"21-03-2019\",\"days\" : 5,\"day1\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day2\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80}, },\"day3\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day4\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day5\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},}}";
    private String invalidTestSchedule = "{\"procedure\": \"applySchedule\",\"startdate\" : \"21-03-2019\",\"days\" : 1,\"day1\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},}}";
    private String preTestSchedule = "{\"procedure\": \"applySchedule\",\"startdate\" : \"21-03-2019\",\"days\" : 1,\"day1\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},}}";
    private Thread currentSystem = null;
    private int port;
    private ServerMock serverMock;

    @Given("^that there is a server$")
    public void thatThereIsAServer() throws Throwable {
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

    @Given("^the system is initialized$")
    public void theSystemIsInitialized() throws Throwable {
        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.setDaemon(true);
        this.currentSystem.start();

        sleep(1000);
    }

    @When("^a valid schedule is received$")
    public void aValidScheduleIsReceived() throws Throwable {
        serverMock.sendMessage(this.testSchedule, 0);
    }

    @Then("^the schedule is saved in the system$")
    public void theScheduleIsSavedInTheSystem() throws Throwable {
        //TODO better test when schedule can be retrieved
        try{
            boolean success = serverMock.getSuccess();

            assertTrue(success);
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }

    @And("^a schedule is in use$")
    public void aScheduleIsInUse() throws Throwable {
        serverMock.sendMessage(this.preTestSchedule,0);
    }

    @Then("^the new schedule is saved in the system$")
    public void theNewScheduleIsSavedInTheSystem() throws Throwable {
        try{
            //TODO better test when schedule can be retrieved
            boolean success = serverMock.getSuccess();

            assertTrue(success);
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }

    @When("^an invalid schedule is received$")
    public void anInvalidScheduleIsReceived() throws Throwable {
        serverMock.sendMessage(this.invalidTestSchedule,0);
    }

    @Then("^the schedule s not saved in the system$")
    public void theScheduleSNotSavedInTheSystem() throws Throwable {
        //TODO better test when schedule can be retrieved
        try{
            boolean success = serverMock.getSuccess();

            assertFalse(success);
        } finally {
            this.currentSystem.interrupt();
        }
    }

    @And("^an error is returned$")
    public void anErrorIsReturned() throws Throwable {
        try {
            boolean formatError = ErrorCode.WRONGFORMAT.equals(serverMock.getReplyStatus());
            boolean appliedError = ErrorCode.NOTAPPLIED.equals(serverMock.getReplyStatus());
            boolean procedureError = ErrorCode.UNDEFINEDPROCEDURE.equals(serverMock.getReplyStatus());

            boolean error;

            if (formatError || appliedError || procedureError){
                error = true;
            } else {
                error = false;
            }

            assertTrue(error);
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
