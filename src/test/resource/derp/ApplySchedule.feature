Feature: Applying a schedule to a greenhouse

  Scenario: A valid schedule is received
    Given the system is initialized
    When a valid schedule is received
    Then the schedule is saved in the system

  Scenario: A valid schedule is received
    Given the system is initialized
    And a schedule is in use
    When a valid schedule is received
    Then the new schedule is saved in the system

  Scenario: An invalid schedule is received
    Given the system is initialized
    When an invalid schedule is received
    Then the schedule s not saved in the system
    And an error is returned
