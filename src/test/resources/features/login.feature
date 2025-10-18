@LoginSteps
Feature: Login functionality

  Scenario: Login with valid credentials
    Given I navigate to login page
    When User has entered valid email address "senemari4@gmail.com" into the email field
    And User has entered valid password "12345" into password field
    And User clicks on login button
    Then User should get successfully logged in

  Scenario: Login with invalid credentials
    Given I navigate to login page
    When User has entered invalid email address "invalid@example.com" into the email field
    And User has entered invalid password "123452999" into password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login with valid email and invalid password
    Given I navigate to login page
    When User has entered valid email address "senemari4@gmail.com" into the email field
    And User has entered invalid password "12343211" into password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login with invalid email and valid password
    Given I navigate to login page
    When User has entered invalid email address "senelnimsara12@gmail.com" into the email field
    And User has entered valid password "12345" into password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch

  Scenario: Login without providing any credentials
    Given I navigate to login page
    When User has entered valid email address "" into the email field
    And User has entered valid password "" into password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch