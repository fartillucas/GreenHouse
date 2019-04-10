Feature: RetryConnection

  Background:
    Given
    And

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


  Scenario: Connection to server is unresponsive
    Given the connection to the server is timed out
    And connection to internet is OK
    When the system finds out the connection is timed out
    Then retry connection

  Scenario: internet connection is cut
    Given  internet connection is cut
    When  internet connection is restored
    Then server socket is refreshed
    And new IP and gate is sent to database
