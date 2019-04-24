Feature: DetectConnectionIssue

  Background: running tests


    Scenario: running tests
      Given some tests to run
      When running tests
      Then they fail
