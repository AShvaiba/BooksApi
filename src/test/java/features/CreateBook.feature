Feature: Create a new book in the API

  Background:
    Given I am authenticated as a valid user

  Scenario: Create a book successfully
    When I send a POST request to the books API with valid data in body
    Then the response status should be 200
    And the response should contain the data being sent

  Scenario: Create a book with empty field
    When I send a POST request to the books API with empty field in body
    Then the response status should be 400

  Scenario: Create a book with null field
    When I send a POST request to the books API with null field in body
    Then the response status should be 400

  Scenario Outline: Create a book with invalid field "<field>"
    When I send a POST request to the books API with invalid field "<field>" in body
    Then the response status should be 400

    Examples:
      | field |
      | pages |
      | price |

  @unauthorized
  Scenario: Create a book without authentication
    Given I am not authenticated
    When I send a POST request to the books API with valid data in body
    Then the response status should be 401