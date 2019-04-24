Feature: StopLiveData

  Background:
    Given
    And

  Scenario: live data is no longer needed in view
    Given there is a connection to the greenhouse
    When session ends
    Then live data stream is closed

  Scenario: live data is no longer needed in view
    Given there is a connection to the greenhouse
    When web page is closed
    Then live data stream is closed
