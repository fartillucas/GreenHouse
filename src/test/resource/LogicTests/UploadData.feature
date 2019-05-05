Feature: Upload data

  Scenario: Raspberry successfully uploading data
    Given There is a server
    And the greenhouse measurements are zero
    And the system is initialized here too
    When It is time to upload data
    Then Data is successfully sent to the server