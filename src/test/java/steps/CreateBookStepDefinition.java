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

public class CreateBookStepDefinition {
    private final TestContext context;

    public CreateBookStepDefinition(TestContext context) {
        this.context = context;
    }

    @When("I send a POST request to the books API with valid data in body")
    public void iSendAPOSTRequestToTheBooksAPIWithValidDataInBody() {
        context.set(CREATED_BOOK, TestDataGenerator.generateRandomBook());
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .createNewBook(context.get(CREATED_BOOK, Book.class)));
    }

    @And("the response should contain the data being sent")
    public void theResponseShouldContainTheDataBeingSent() {
        Book book = ResponseHandler.fromJson(context.get(RESPONSE, Response.class), Book.class);
        assertEquals(context.get(CREATED_BOOK, Book.class), book, "Created book should match the sent data");
    }

    @When("I send a POST request to the books API with empty field in body")
    public void iSendAPOSTRequestToTheBooksAPIWithEmptyFieldInBody() {
        Book book = TestDataGenerator.generateRandomBookWithRandomEmptyField();
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class).createNewBook(book));
    }

    @When("I send a POST request to the books API with null field in body")
    public void iSendAPOSTRequestToTheBooksAPIWithNullFieldInBody() {
        Book book = TestDataGenerator.generateRandomBookWithNullField();
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class).createNewBook(book));
    }

    @When("I send a POST request to the books API with invalid field {string} in body")
    public void iSendAPOSTRequestToTheBooksAPIWithInvalidFieldInBody(String invalidField) {
        Book book = TestDataGenerator.generateRandomBookWithInvalidField(invalidField);
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class).createNewBook(book));
    }


}
