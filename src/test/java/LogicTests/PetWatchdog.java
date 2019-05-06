package LogicTests;

import Mocks.Mocks.GreenhouseMock;
import Mocks.Mocks.ServerMock;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import raspberry.glue.Starter;

import java.net.BindException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;

public class PetWatchdog {
    private int port;
    private ServerMock serverMock;
    private Thread currentSystem;

    @Given("^there is a connection to the server$")
    public void thereIsAConnectionToTheServer() throws Throwable {
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

        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.setDaemon(true);
        this.currentSystem.start();

        sleep(1000);
    }

    @When("^it's time to pet$")
    public void itSTimeToPet() throws Throwable {
        sleep(10000);
    }

    @Then("^the greenhouse sends some kind of message to the server$")
    public void theGreenhouseSendsSomeKindOfMessageToTheServer() throws Throwable {
        try{
            assertTrue(this.serverMock.recievedPetting());
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }

    @Given("^there is no connection$")
    public void thereIsNoConnection() throws Throwable {

        this.currentSystem = new Thread(()->{
            Starter.start();
        });
        this.currentSystem.setName("Client");
        this.currentSystem.setDaemon(true);
        this.currentSystem.start();

        sleep(3000);

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

    @Then("^the next message is the IP address$")
    public void theNextMessageIsTheIPAddress() throws Throwable {
        try{
            assertTrue(this.serverMock.recievedIPAdress());
        } finally {
            if (serverMock != null && currentSystem != null){
                resetThreads(serverMock);
            }
        }
    }


    private void resetThreads(ServerMock serverMock){
        serverMock.stopThreads();

        Starter.stopThreads();

        GreenhouseMock.getInstance().clear();
    }

}
