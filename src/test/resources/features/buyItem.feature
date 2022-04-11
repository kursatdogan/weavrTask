
Feature: the user should able to buy any item

 Background:
   Given the user login with valid credentials
@smoke
 Scenario: the user can add any item to card
   When the user clicks the addToCart button
   And the user clicks to shopping trolley icon
   Then the user should able to navigate your cart page

  Scenario: the user can buy any item after add it to shopping trolley
    And the user get product prices for verification
    When the user clicks the addToCart button
    And the user on the your chart page
    Then the product prices should be same with cart prices
    When the user clicks checkout button
    Then the user should able to navigate info page

    Scenario: the name of items chosen from products page should be same with your cart page
      Given the user choose the products from products page
      When the user clicks to shopping trolley icon
      Then the name of the products should able to same with the products chosen from products page


  Scenario: the user enter the user information
    Given the user on the info page
    When the user enters the valid user information
    And the user clicks to continue button
    Then the user should able to checkout overview page

  Scenario: the user complete buying any item
    Given the user on the checkout page
    When the user clicks to finish button
    Then the user should able to see thanks you for your order text

  Scenario Outline: the user should not able to navigate to checkout page with wrong or empty user information
    Given the user on the info page
    When the user enters the "<firstName>", "<lastName>" and "<postalCode>"
    And the user clicks to continue button
    Then the user should able to see "<message>"

      Examples:
        | firstName | lastName | postalCode | message                              |
        | Michael   | Jordan   |            | Error: Postal Code is required       |
        |           | Jordan   | 1234       | Error: First Name is required        |
        | Michael   |          | 1234       | Error: Last Name is required         |

@smoke  @wip
  Scenario:
    When the user clicks addToCart button for more than one item
    And the user clicks to shopping trolley icon
    And the user remove one item from checkout page
    And the user clicks checkout button
    And the user enters the valid user information
    And the user clicks to continue button
    And the user clicks to finish button
    Then the user should able to see thanks you for your order text