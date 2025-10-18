Feature: Login functionality

  Scenario: Login with valid credentials
    Given I navigate to login page
    When User has entered valid email address "senelnimsara@gmail.com" into the email field
    And User has entered valid password "12345" into password field
    And User clicks on login button
    Then User should get sucessfully Logged in

  Scenario: Login with invalid credentials
    Given I navigates to login pagemave
    And User has entered invalid password "123452999" into password field
    And User clicks on login button
    Then User should get a proper warning message about credentials match

  Scenario:  Login with valid email and invalid password
    Given User has navigates to login page
    When User enters valid email address "senelnimsara@gmail.com" into the email field
    When User enters invalid password "12343211" into the password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch

  Scenario:  Login with invalid email and valid password
    Given User has navigates to login page
    When User enters invalid email address "senelnimsara12@gmail.com" into the email field
    When User enters valid password "12345" into the password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch

  Scenario:  Login without providing any credentials
    Given User has navigates to login page
    When User dont enter any email address to email field
    When User dont enter any password to password field
    And User clicks on login button
    Then User should get a proper warning message about credentials mismatch