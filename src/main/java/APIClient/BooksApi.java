package APIClient;

import APIBase.BaseApi;
import dto.Book;
import io.restassured.response.Response;

import static utils.PropertyReader.getBooksUri;

public class BooksApi extends BaseApi {

    public BooksApi(String user, String password) {
        super(user, password);
    }

    public Response getAllBooks() {
        return get(getBooksUri());
    }

    public Response getBookById(String bookId) {
        return get(getBooksUri() + "/" + bookId);
    }

    public Response createNewBook(Book book) {
        return post(gson.toJson(book), getBooksUri());
    }

    public Response updateBook(String bookId, Book book) {
        return put(gson.toJson(book), getBooksUri() + "/" + bookId);
    }

    public Response deleteBookById(String bookId) {
        return delete(getBooksUri() + "/" + bookId);
    }
}
