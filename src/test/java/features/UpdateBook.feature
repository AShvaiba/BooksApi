Feature: Update existing book in the API

  Background:
    Given I am authenticated as a valid user

  Scenario: Update all fields in a book successfully
    Given A book exists in the system
    When I send a PUT request to the books API to update all the fields in the book with a valid data
    Then the response status should be 200
    And the response should contain the data being sent

  Scenario: Update one field in a book
    Given A book exists in the system
    When I send a PUT request to the books API to update one field with valid data
    Then the response status should be 200
    And the response should contain a book with updated data

  Scenario Outline: Update field "<field>" with invalid data
    Given A book exists in the system
    When I send a PUT request to the books API to update the field "<field>" with invalid data
    Then the response status should be 400

    Examples:
      | field       |
      | pages       |
      | price       |

  Scenario: Update a non-existing book
    Given No book exists with a non-existing ID
    When I send a PUT request to the non-existing book ID
    Then the response status should be 404

  Scenario: Update a book without authentication
    Given I am not authenticated
    When I send a PUT request to the books API to update all the fields in the book with a valid data
    Then the response status should be 401
