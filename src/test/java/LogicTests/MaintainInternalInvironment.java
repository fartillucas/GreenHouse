package LogicTests;


import Mocks.Mocks.GreenhouseMock;
import Mocks.Mocks.ServerMock;
import com.sun.security.ntlm.Server;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import raspberry.glue.Starter;
import raspberry.logic.RaspberryAPI;
import raspberry.logic.SetPoints;
import raspberry.logic.schedule.Schedule;

import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MaintainInternalInvironment {

	private Thread currentSystem;
	private String testSchedule ="{	\"procedure\": \"applySchedule\",\"startdate\" : \"21-03-2019\",\"days\" : 5,\"day1\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day2\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80}, },\"day3\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day4\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},},\"day5\" : {\"block1\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block2\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block3\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block4\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block5\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block6\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block7\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block8\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block9\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block10\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block11\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},\"block12\":{\"temperature\":20, \"humidity\":40, \"waterlevel\":5, \"light_blue\":80, \"light_red\":80},}}";
	private int port;
	private ServerMock serverMock;

	@Given("^there is a server present$")
	public void thereIsAServerPresent() throws Throwable {
		sleep(1000);
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

	@Given("^an initialized system$")
	public void anInitializedSystem() throws Throwable {
		this.currentSystem = new Thread(()->{
			Starter.start();
		});
		this.currentSystem.setName("Client");
		this.currentSystem.setDaemon(true);
		this.currentSystem.start();

		GreenhouseMock.getInstance().resetMock();

		sleep(1000);
	}

	@And("^the system contains a schedule$")
	public void theSystemContainsASchedule() throws Throwable {
		serverMock.sendMessage(this.testSchedule, 0);
	}

	@Given("^Measurements are within acceptable parameters$")
	public void measurementsAreWithinAcceptableParameters() throws Throwable {
		GreenhouseMock.getInstance().setInternalTemperature(20.0);
		GreenhouseMock.getInstance().setHumidity(40.0);
		GreenhouseMock.getInstance().setWaterLevel(5.0);
	}

	@Then("^do nothing$")
	public void doNothing() throws Throwable {
		sleep(2000);
		try {
			assertFalse(GreenhouseMock.getInstance().fanIsStarted());
			assertFalse(GreenhouseMock.getInstance().waterPumpIsStarted());
		} finally {
			try {
				if (serverMock != null && currentSystem != null) {
					resetThreads(serverMock);
				}
			} catch (Exception e){

			}
		}
	}

	@When("^The internal temperature is too high$")
	public void theInternalTemperatureIsTooHigh() throws Throwable {
		GreenhouseMock.getInstance().setInternalTemperature(25.0);
	}

	@And("^and external temperature is below threshold$")
	public void andExternalTemperatureIsBelowThreshold() throws Throwable {
		GreenhouseMock.getInstance().setExternalTemperature(18.0);
	}

	@Then("^start fan$")
	public void startFan() throws Throwable {
		sleep(2000);
		try {
			assertTrue(GreenhouseMock.getInstance().fanIsStarted());
		} finally {
			if (serverMock != null && currentSystem != null){
				resetThreads(serverMock);
			}
		}
	}

	@When("^all the plants are submerged$")
	public void allThePlantsAreSubmerged() throws Throwable {
		GreenhouseMock.getInstance().setWaterLevel(15.0);
	}

	@When("^the water level is to low$")
	public void theWaterLevelIsToLow() throws Throwable {
		GreenhouseMock.getInstance().setWaterLevel(0.0);
	}

	@Then("^start water pump$")
	public void startWaterPump() throws Throwable {
		sleep(2000);
		try{
			assertTrue(GreenhouseMock.getInstance().waterPumpIsStarted());
		} finally {
			if (serverMock != null && currentSystem != null){
				resetThreads(serverMock);
			}
		}
	}

	@When("^humidity is too high$")
	public void humidityIsTooHigh() throws Throwable {
		GreenhouseMock.getInstance().setHumidity(90.0);
	}

	@And("^external temperature is above threshold$")
	public void externalTemperatureIsAboveThreshold() throws Throwable {
		GreenhouseMock.getInstance().setExternalTemperature(200.0);
	}

	@When("^humidity is to high$")
	public void humidityIsToHigh() throws Throwable {
		GreenhouseMock.getInstance().setHumidity(85.0);

	}

	@And("^external temperature is below threshold$")
	public void externalTemperatureIsBelowThreshold() throws Throwable {
		GreenhouseMock.getInstance().setExternalTemperature(10.0);
	}

	@When("^humidity is too low$")
	public void humidityIsTooLow() throws Throwable {
		GreenhouseMock.getInstance().setHumidity(25.0);
	}

	private void resetThreads(ServerMock serverMock){
		serverMock.stopThreads();

		Starter.stopThreads();

		GreenhouseMock.getInstance().clear();
	}


	private List<HashMap<Integer, SetPoints>> interpret(JSONObject jsonSchedule) {

		List<HashMap<Integer, SetPoints>> schedule = new ArrayList<>();

		int numberOfDays = jsonSchedule.getInt("days");

		for (int i = 1; i <= numberOfDays; i++) {
			String dayString = jsonSchedule.get("day"+i).toString();

			JSONObject day = new JSONObject(dayString);

			HashMap<Integer, SetPoints> dayMap = new HashMap<>();

			for (int j = 1; j <= 12; j++) {
				String blockString = day.get("block"+j).toString();

				JSONObject block = new JSONObject(blockString);

				double temperature = block.getDouble("temperature");
				double humidity = block.getDouble("humidity");
				double waterlevel = block.getDouble("waterlevel");
				int blueLight = block.getInt("light_blue");
				int redLight = block.getInt("light_red");

				SetPoints setPoints = new SetPoints(temperature, humidity, waterlevel, blueLight, redLight);

				dayMap.put(j,setPoints);
			}

			schedule.add(dayMap);
		}


		return schedule;
	}


}
