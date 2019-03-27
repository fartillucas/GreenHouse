package LogicTests;


import Mocks.Mocks.GreenhouseMock;
import Mocks.Mocks.ServerMock;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.Greenhouse;
import raspberry.logic.Starter;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MaintainInternalInvironment {
	private Thread currentSystem = null;
	private String testSchedule ="{	\"procedure\": \"applySchedule\",\"startdate\" : \"21-03-2019\",\"days\" : 5,\"day1\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day2\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80}, },\"day3\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day4\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day5\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},}}";


	@Given("^there is a server present$")
	public void thereIsAServerPresent() throws Throwable {
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

	@Given("^an initialized system$")
	public void anInitializedSystem() throws Throwable {
		this.currentSystem = new Thread(()->{
			Starter.start();
		});
		this.currentSystem.setName("Client");
		this.currentSystem.start();

		sleep(1000);
	}

	@And("^the system contains a schedule$")
	public void theSystemContainsASchedule() throws Throwable {
		ServerMock.getInstance().sendMessage(this.testSchedule, 0);

	}

	@Given("^Measurements are within acceptable parameters$")
	public void measurementsAreWithinAcceptableParameters() throws Throwable {
		GreenhouseMock.getInstanance().setInternalTemperature(20.0);
		GreenhouseMock.getInstanance().setHumidity(40.0);
		GreenhouseMock.getInstanance().setWaterLevel(5.0);



	}

	@Then("^do nothing$")
	public void doNothing() throws Throwable {
		assertFalse(GreenhouseMock.getInstanance().fanIsStarted());
		assertFalse(GreenhouseMock.getInstanance().waterPumpIsStarted());

	}

	@When("^The internal temperature is too high$")
	public void theInternalTemperatureIsTooHigh() throws Throwable {
		GreenhouseMock.getInstanance().setInternalTemperature(25.0);


	}

	@And("^and external temperature is below threshold$")
	public void andExternalTemperatureIsBelowThreshold() throws Throwable {
		GreenhouseMock.getInstanance().setExternalTemperature(18.0);

	}

	@Then("^start fan$")
	public void startFan() throws Throwable {
		assertTrue(GreenhouseMock.getInstanance().fanIsStarted());


	}

	@And("^and external temperature is above threshold$")
	public void andExternalTemperatureIsAboveThreshold() throws Throwable {
		GreenhouseMock.getInstanance().setExternalTemperature(300.0);
	}

	@When("^all the plants are submerged$")
	public void allThePlantsAreSubmerged() throws Throwable {
		GreenhouseMock.getInstanance().setWaterLevel(15.0);

	}

	@When("^the water level is to low$")
	public void theWaterLevelIsToLow() throws Throwable {
		GreenhouseMock.getInstanance().setWaterLevel(0.0);

	}

	@Then("^start water pump$")
	public void startWaterPump() throws Throwable {
		assertTrue(GreenhouseMock.getInstanance().waterPumpIsStarted());

	}

	@When("^humidity is too high$")
	public void humidityIsTooHigh() throws Throwable {
		GreenhouseMock.getInstanance().setHumidity(90.0);

	}

	@And("^external temperature is above threshold$")
	public void externalTemperatureIsAboveThreshold() throws Throwable {
		GreenhouseMock.getInstanance().setExternalTemperature(200.0);
	}

	@When("^humidity is to high$")
	public void humidityIsToHigh() throws Throwable {
		GreenhouseMock.getInstanance().setHumidity(85.0);

	}

	@And("^external temperature is below threshold$")
	public void externalTemperatureIsBelowThreshold() throws Throwable {
		GreenhouseMock.getInstanance().setExternalTemperature(10.0);

	}

	@When("^humidity is too low$")
	public void humidityIsTooLow() throws Throwable {
		GreenhouseMock.getInstanance().setHumidity(25.0);

	}


}
