Feature: AddGreenhouse

  Background:
    Given user is logged in
    And There a connection to the database

  Scenario: A greenhouse is added successfully and added to database
    Given logged in
    And there is a connection to the database
    And the greenhouse has a unique ID
    And the greenhouse ID is correctly submitted
    When “add Greenhouse” is pressed
    Then ownership of the greenhouse is added to the database
    And and the greenhouse gets automatically selected

  Scenario: Wrong greenhouse ID
    Given logged in
    And there is a connection to the database
    And the greenhouse has a unique ID
    And the greenhouse ID is incorrectly submitted
    When “add Greenhouse” is pressed
    Then “Error 40 - wrong greenhouse id” is returned

  Scenario: no connection to database
    Given logged in
    And there is no connection to the database
    When “add Greenhouse” is pressed
    Then “404 database not found” is returned
