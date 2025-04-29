Feature: Delete a book from the API

  Background:
    Given I am authenticated as a valid user

  Scenario: Delete a book successfully
    Given A book exists in the system
    When I send a DELETE request to books API to delete the book by ID
    Then the response status should be 200
    And response contains message "Book has been deleted successfully"

  Scenario: Delete a non-existing book
    Given No book exists with a non-existing ID
    When I send a DELETE request to the non-existing book ID
    Then the response status should be 404

  Scenario: Delete a book without authentication
    Given I am not authenticated
    When I send a DELETE request to books API to delete the book by ID
    Then the response status should be 401