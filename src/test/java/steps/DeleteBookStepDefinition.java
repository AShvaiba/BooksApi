package steps;

import APIClient.BooksApi;
import context.TestContext;
import io.cucumber.java.en.When;

import static context.TestContext.Keys.*;
import static context.TestContext.Keys.NON_EXISTING_BOOK_ID;

public class DeleteBookStepDefinition {

    private final TestContext context;

    public DeleteBookStepDefinition(TestContext context) {
        this.context = context;
    }

    @When("I send a DELETE request to books API to delete the book by ID")
    public void iSendADELETERequestToTheBooksAPIToDeleteTheBookByItsID() {
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .deleteBookById(String.valueOf(context.get(BOOK_ID, Integer.class))));
    }

    @When("I send a DELETE request to the non-existing book ID")
    public void iSendADELETERequestToTheNonExistingBookID() {
        context.set(RESPONSE, context.get(BOOKS_API, BooksApi.class)
                .deleteBookById(context.get(NON_EXISTING_BOOK_ID, String.class)));
    }
}
