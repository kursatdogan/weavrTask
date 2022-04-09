Feature: User should able to login

  Scenario: Login as a standard_user
    Given the user login with valid credentials
    Then the user should be able to see products page

  Scenario Outline: the user can not login with invalid credential
    Given the user should be on login page
    When the user enters invalid "<username>" and "<password>"
    Then the user should not able to login and see "<warningMessage>"

    Examples:
      | username                | password     | warningMessage                                                            |
      | standard_user           |              | Epic sadface: Password is required                                        |
      | locked_out_user         |              | Epic sadface: Password is required                                        |
      | problem_user            |              | Epic sadface: Password is required                                        |
      | performance_glitch_user |              | Epic sadface: Password is required                                        |
      |                         | secret_sauce | Epic sadface: Username is required                                        |
      | Standart_user           | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user         | Secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standartuser            | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | problem_user            | secretsauce  | Epic sadface: Username and password do not match any user in this service |
      | performance_glitch_user | SECRET_SAUCE | Epic sadface: Username and password do not match any user in this service |
      | STANDART_USER           | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | **************          | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | problem_user            | **********   | Epic sadface: Username and password do not match any user in this service |
      | 12345                   | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standart__user          | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user         | secret-sauce | Epic sadface: Username and password do not match any user in this service |
      | performance_glitch_user | secret+sauce | Epic sadface: Username and password do not match any user in this service |


