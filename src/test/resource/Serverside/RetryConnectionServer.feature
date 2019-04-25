Feature: RetryConnection


  Scenario: watchdog fails
    Given a greenhouse have watchdog in system
    When the watchdog awakes for the $n ‘th time
    Then the server re-sends connection information to the greenhouse
    And an email is sent $email

  Scenario: watchdog fails
    Given a greenhouse have watchdog in system
    When the watchdog awakes for the $n ‘th time
    And the server resending connection information to the greenhouse fails
    Then an email is sent $email
