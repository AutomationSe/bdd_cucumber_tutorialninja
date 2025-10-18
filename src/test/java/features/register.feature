Feature: Registration Functionality

  Scenario: User creates an account with mandatory fields
    Given User navigates to Register Account page
    When User enters the details into below fields
      | firstName | Senel                  |
      | lastName  | Nimsara                |
      | email     | senelnimsara@gmail.com |
      | telephone | 0725653523             |
      | password  | 12345                  |
    And User selects Privacy Policy
    And User clicks on Continue button
    Then User account should get created successfully

  Scenario: User creates an account with all fields
    Given User navigates to Register Account page
    When User enters the details into below fields
      | firstName       | Senel                   |
      | lastName        | Nimsara                 |
      | email           | senelnimsara1@gmail.com |
      | telephone       | 0725653523              |
      | password        | 12345
      | confirmpassword | 12345                   |
    And User selects Yes for Newsletter
    And User selects Privacy Policy
    And User clicks on Continue button
    Then User account should get created successfully

  Scenario: User creates a duplicate account
    Given User navigates to Register Account page
    When User enters the details into below fields
      | firstName | Arun                      |
      | lastName  | Motoori                   |
      | email     | senelnimsara@gmail.com    |
      | telephone | 0725653523                |
      | password  | 12345                     |
    And User selects Yes for Newsletter
    And User selects Privacy Policy
    And User clicks on Continue button
    Then User should get a proper warning about duplicate email

  Scenario: User tries to create an account without filling any details
    Given User navigates to Register Account page
    When User does not enter any details into fields
    And User clicks on Continue button
    Then User should get proper warning messages for mandatory fields
