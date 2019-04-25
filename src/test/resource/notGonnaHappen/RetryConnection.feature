Feature: RetryConnection

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
