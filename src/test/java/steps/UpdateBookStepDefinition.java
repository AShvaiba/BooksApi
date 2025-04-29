package steps;

import APIClient.BooksApi;
import context.TestContext;
import dto.Book;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ResponseHandler;
import utils.TestDataGenerator;

import static context.TestContext.Keys.*;
import static org.testng.Assert.assertEquals;
import static utils.TestDataGenerator.generateRandomNonPositiveDouble;
import static utils.TestDataGenerator.generateRandomNonPositiveInteger;

public class UpdateBookStepDefinition {

    private final TestContext context;

    public UpdateBookStepDefinition(TestContext context) {
        this.context = context;
    }

    @When("I send a PUT request to the books API to update all the fields in the book with a valid data")
    public void iSendAPUTRequestToTheBooksAPIToUpdateTheBookWithAValidData() {
        Book book = TestDataGenerator.generateRandomBook();
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .updateBook(String.valueOf(context.get(BOOK_ID, Integer.class)), book));
        context.set(CREATED_BOOK, book);
    }

    @When("I send a PUT request to the books API to update one field with valid data")
    public void iSendAPUTRequestToTheBooksAPIToUpdateTheFieldWithAValidData() {
        Book book = TestDataGenerator.updateRandomFieldInBook(context.get(CREATED_BOOK, Book.class));
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .updateBook(String.valueOf(context.get(BOOK_ID, Integer.class)), book));
        context.set(CREATED_BOOK, book);
    }

    @When("I send a PUT request to the books API to update the field {string} with invalid data")
    public void iSendAPUTRequestToTheBooksAPIToUpdateTheFieldWithInvalidData(String invalidField) {
        Book book = context.get(CREATED_BOOK, Book.class);
        switch (invalidField.toLowerCase()) {
            case "pages" -> book.setPages(generateRandomNonPositiveInteger());
            case "price" -> book.setPrice(generateRandomNonPositiveDouble());
            default -> throw new IllegalArgumentException("Invalid field: " + invalidField);
        }

        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .updateBook(String.valueOf(context.get(BOOK_ID, Integer.class)), book));
        context.set(CREATED_BOOK, book);
    }

    @When("I send a PUT request to the non-existing book ID")
    public void iSendAPUTRequestToTheNonExistingBookID() {
        Book book = TestDataGenerator.generateRandomBook();
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .updateBook(context.get(NON_EXISTING_BOOK_ID, String.class), book));
    }

    @And("the response should contain a book with updated data")
    public void theResponseShouldContainABookWithUpdatedData() {
        Book updatedBook = context.get(CREATED_BOOK, Book.class);
        Book responseBook = ResponseHandler.fromJson(context.get(RESPONSE, Response.class), Book.class);
        assertEquals(updatedBook, responseBook, "Updated book should match the response data");
    }
}
