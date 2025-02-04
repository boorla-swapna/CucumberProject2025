Feature: Test Login With Different TestData

  Scenario Outline: Test Different Login Details
    Given I am on the login page
    When I enter "<username>" and "<password>"
    And I click on the login button
    Then I should see the message "<message>"

    Examples: 
      | username       | password       | message          |
      | valid.username | valid.password | Login successful |
      | valid.username | wrong.password | Invalid password |
