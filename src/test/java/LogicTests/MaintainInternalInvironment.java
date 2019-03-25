package LogicTests;

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

public class MaintainInternalInvironment {


	@Given("^Measurements are within acceptable parameters$")
	public void measurementsAreAcceptable() {

	}

	@Given("^The internal temperature is too high$")
	public void temperatureIsToHigh() {
	}



	@Given("^The internal temperature is too high$")
	public void temperatureIsToHighExternalAboveThreshold() {
		}


	@Given("^the internal temperature is too low$")
	public void temperatureIsToLow() {

	}
	@Given("^The water level is too high$")
	public void waterLevelToHigh() {
	}
	@Given("^The water level is too low$")
	public void waterLevelToLow() {
	}
	@Given("^Humidity is too high, and external temperature is above threshold$")
	public void humidityIsToHigh() {
	}
	@Given("^Humidity is too high,  and external temperature is below threshold$")
	public void humidityIsToHighExternalBelow() {
	}

	@Given("^Humidity is too low$")
	public void humidityIsToLow() {
	}

	@Then("^Do nothing$")
	public boolean doNothing () {

		return true;
	}

}