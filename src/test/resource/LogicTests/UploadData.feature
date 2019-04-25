Feature: Upload data

  Scenario: Raspberry successfully uploading data
    Given There is a connection to the database
    And the system is initialized here too
    When It is time to upload data
    Then Data is successfully stored on database