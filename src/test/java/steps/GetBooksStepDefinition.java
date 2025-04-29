package steps;

import APIClient.BooksApi;
import context.TestContext;
import dto.Book;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ResponseHandler;
import utils.TestDataGenerator;

import java.util.List;

import static context.TestContext.Keys.*;
import static org.testng.Assert.assertTrue;

public class GetBooksStepDefinition {

    private final TestContext context;

    public GetBooksStepDefinition(TestContext context) {
        this.context = context;
    }

    @When("I send a GET request to the books API endpoint")
    public void iSendAGETRequestToTheBooksAPIEndpoint() {
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class).getAllBooks());
    }

    @When("I send a GET request to the book by its ID")
    public void iSendAGETRequestToTheBookByItsID() {
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .getBookById(String.valueOf(context.get(BOOK_ID, Integer.class))));
    }

    @When("I send a GET request to the non-existing book ID")
    public void iSendAGETRequestToTheNonExistingBookID() {
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .getBookById(context.get(NON_EXISTING_BOOK_ID, String.class)));
    }

    @And("the response should contain a non-empty list of books")
    public void theResponseShouldContainANonEmptyListOfBooks() {
        List<Book> books = ResponseHandler.fromJsonArray(context.get(RESPONSE, Response.class), Book[].class);
        assertTrue(books.size() > 0, "Book list should not be empty");
    }

    @And("A book exists in the system")
    public void aBookExistsInTheSystem() {
        Book book = TestDataGenerator.generateRandomBook();
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class).createNewBook(book));
        context.set(CREATED_BOOK, book);
        context.set(BOOK_ID, ResponseHandler.fromJson(context.get(RESPONSE, Response.class), Book.class).getId());
    }
}
