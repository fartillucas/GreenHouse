Feature: RegisterUser
Scenario: a user registers with the required information
Given acceptable user credidentials
When register is clicked
Then the user is saved in the database
And user is redirected to login screen

