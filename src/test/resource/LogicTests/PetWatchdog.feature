Feature: PetWatchdog

  Background:
    Given
    And

  Scenario: the watchdog is successfully petted
    Given petted dog watch is
    When the dog is being pet
    Then reset some countdown

  Scenario: the watchdog is unsuccessfully petted
    Given petted dog watch is
    When the dog is not being pet
    Then watchdog wakes up

  Scenario: the watchdog is successfully petted
    Given there is a connection to the server
    When the greenhouse sends some kind of message to the server
    Then the watchdog on the server does nothing
