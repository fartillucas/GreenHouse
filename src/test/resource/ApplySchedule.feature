Feature: Applying a schedule to a greenhouse

  Scenario: succesfully applying a schedule to a greenhouse
    Given A user is logged in and has a greenhouse selected
    When A user presses apply schedule
    Then The schedule is sent to the selected greenhouse and started