Feature: Maintaining the internal environment

  Background:
	  Given there is a server present
    Given an initialized system
    And the system contains a schedule

	Scenario: For each stable condition
		Given Measurements are within acceptable parameters
		Then do nothing

	Scenario: The temperature is too high, and external temperature is below threshold
		When The internal temperature is too high
		And and external temperature is below threshold
		Then start fan

	Scenario: The temperature is too high, and external temperature is above threshold
		When The internal temperature is too high
		And  and external temperature is above threshold
		Then do nothing

	Scenario: The water level is too high
		When all the plants are submerged
		Then do nothing

	Scenario: The water level is too low
		When the water level is to low
		Then start water pump

	Scenario: Humidity is too high,  and external temperature is above threshold
		When humidity is too high
		And external temperature is above threshold
		Then do nothing

	Scenario: Humidity is too high,  and external temperature is below threshold
		When humidity is to high
		And external temperature is below threshold
		Then start fan

	Scenario: Humidity is too low
		When humidity is too low
		Then do nothing







