Feature: CreateSchedule

  Background:
    Given There is a connection to the greenhouse
    And There a connection to the database

Scenario: A schedule is created successfully and sent to database and greenhouse
  Given A connection to greenhouse
  And a connection to the database
  And a valid schedule is available
  When apply is pressed
  Then the schedule is sent to the greenhouse and database correctly

Scenario: invalid schedule
  Given an invalid schedule is available
  When apply is pressed
  Then an error message invalid schedule is returned

Scenario: no connection to greenhouse
  Given no connection to greenhouse
  And a connection to the database
  And a valid schedule is available
  When apply is pressed
  Then no connection to greenhouse message is returned
  And nothing happens “you might have firewall trouble...”

Scenario: no greenhouse selected
  Given no greenhouse selected
  And a connection to the database
  And a valid schedule is available
  When apply is pressed
  Then no greenhouse selected message is returned

Scenario: no connection to database
  Given A connection to greenhouse
  And no connection to the database
  And a valid schedule is available
  When apply is pressed
  Then error message “technical issues…”
