package APIBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static utils.PropertyReader.getApiUrl;

public abstract class BaseApi {

    protected Gson gson;
    protected RequestSpecification requestSpecification;

    public BaseApi(String user, String password) {
        gson = new GsonBuilder().create();
        requestSpecification = given()
                .auth().basic(user, password)
                .header("Content-Type", "application/json")
                .baseUri(getApiUrl())
                .log().all();
    }

    public Response get(String uri) {
        return requestSpecification
                .log().all()
                .when()
                .get(uri)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response post(String body, String uri) {
        return requestSpecification
                .body(body)
                .log().all()
                .when()
                .post(uri)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response put(String body, String uri) {
        return requestSpecification
                .body(body)
                .log().all()
                .when()
                .put(uri)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response delete(String uri) {
        return requestSpecification
                .log().all()
                .when()
                .delete(uri)
                .then()
                .log().all()
                .extract().response();
    }
}
