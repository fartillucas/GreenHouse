Feature: Creating a subscriber to send internal environment measurements.

  Background:
    Given the server exists
    And the system is started


  Scenario: Subscription is Successful
    Given a valid subscription request
    When server side sends subscription request
    Then the subscriber is subscribed
    And greenhouse sends internal environment measurements every cycle

  Scenario: Subscription failed
    Given a invalid subscription request
    When server side sends subscription request
    Then the new subscription is not subscribed
    And error is sent
    And greenhouse doesn't send internal environment to new subscriber every cycle