package steps;

import APIClient.BooksApi;
import context.TestContext;
import dto.Book;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.ResponseHandler;

import java.util.List;

import static context.TestContext.Keys.BOOKS_API;
import static context.TestContext.Keys.RESPONSE;
import static utils.PropertyReader.getPassword;
import static utils.PropertyReader.getUser;

public class BaseStepDefinition {

    private final TestContext context;

    public BaseStepDefinition(TestContext context) {
        this.context = context;
    }

    @Given("I am authenticated as a valid user")
    public void iAmAuthenticatedAsAValidUser() {
        context.set(BOOKS_API, new BooksApi(getUser(), getPassword()));
    }

    @Given("I am not authenticated")
    public void iAmNotAuthenticated() {
        context.set(BOOKS_API, new BooksApi(getUser(), "invalid_password"));
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatusCode) {
        ResponseHandler.validateStatusCode(context.get(RESPONSE, Response.class), expectedStatusCode);
    }

    @Given("No book exists with a non-existing ID")
    public void noBookExistsWithANonExistentId() {
        List<Book> books = ResponseHandler.fromJsonArray(context.get(BOOKS_API, BooksApi.class).getAllBooks(), Book[].class);
        int maxId = books.stream().mapToInt(Book::getId).max().orElse(0);
        context.set(TestContext.Keys.NON_EXISTING_BOOK_ID, String.valueOf(maxId + 1));
    }

    @And("response contains message {string}")
    public void responseContainsMessage(String message) {
        String responseMessage = context.get(RESPONSE, Response.class).asString();
        if (responseMessage != null) {
            assert responseMessage.contains(message) : "Response message does not contain expected message";
        } else {
            throw new AssertionError("Response message is null");
        }
    }
}
