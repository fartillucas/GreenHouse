Feature: Applying a schedule to a greenhouse

  Background:
    Given that there is a server
    And the system is initialized

  Scenario: A valid schedule is received
    When a valid schedule is received
    Then the schedule is saved in the system

  Scenario: A valid schedule is received
    And a schedule is in use
    When a valid schedule is received
    Then the new schedule is saved in the system

  Scenario: An invalid schedule is received
    When an invalid schedule is received
    Then the schedule s not saved in the system
    And an error is returned
