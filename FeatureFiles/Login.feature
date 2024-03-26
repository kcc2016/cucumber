Feature: Login Functionality

  Scenario Outline: Successful Login
    Given the user is at OrangeHRM login page
    When the user enters credentials in row number "<tr>" of excel file
    And clicks on login button
    Then home page is displayed

    Examples: 
      | tr |
      |  1 |

  Scenario Outline: Unsuccessful Login
    Given the user is at OrangeHRM login page
    When the user enters credentials in row number "<tr>" of excel file
    And clicks on login button
    Then login is unsuccessful

    Examples: 
      | tr |
      |  2 |
      |  3 |
