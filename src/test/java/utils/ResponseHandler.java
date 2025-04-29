package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ResponseHandler {
    private static final Gson gson = new Gson();

    public static <T> T fromJson(Response response, Class<T> t) {
        return gson.fromJson(response.getBody().asString(), t);
    }

    public static <T> List<T> fromJsonArray(Response response, Class<T[]> t) {
        return Arrays.asList(new Gson().fromJson(response.getBody().asString(), t));
    }

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertEquals(response.statusCode(), expectedStatusCode, "Unexpected status code");
    }
}
