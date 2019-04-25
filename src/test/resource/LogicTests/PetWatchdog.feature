Feature: PetWatchdog

  Scenario: the watchdog is successfully petted
    Given there is a connection to the server
    When it's time to pet
    Then the greenhouse sends some kind of message to the server

  Scenario: can't connect
    Given there is no connection
    When it's time to pet
    Then the next message is the IP address

