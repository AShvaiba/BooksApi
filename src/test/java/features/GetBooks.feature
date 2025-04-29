Feature: Read books from the API

  Background:
    Given I am authenticated as a valid user

  Scenario: Get all books successfully
    Given A book exists in the system
    When I send a GET request to the books API endpoint
    Then the response status should be 200
    And the response should contain a non-empty list of books

  Scenario: Get book by ID successfully
    Given A book exists in the system
    When I send a GET request to the book by its ID
    Then the response status should be 200

  Scenario: Get non-existing book by ID
    Given No book exists with a non-existing ID
    When I send a GET request to the non-existing book ID
    Then the response status should be 404

  @unauthorized
  Scenario: Get all books without authentication
    Given I am not authenticated
    When I send a GET request to the books API endpoint
    Then the response status should be 401