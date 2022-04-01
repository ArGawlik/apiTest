package model.heirs.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RxObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RqObject extends RxObject {
    private static final String BASE_URI = "https://petstore.swagger.io";

    public final Function<String, String> prepareUri = uri -> String.format("%s%s", BASE_URI, uri);

    protected RequestSpecification requestSpecification;

    public RqObject(String rqName) {
        super(rqName);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        this.requestSpecification = RestAssured.given().auth().oauth2("8d3edc50fd5dbb75c78aa0e6b003827314f21f4aa8f03facd79465e78ef22e66");
        this.requestSpecification = RestAssured.given().auth().oauth2("8d3edc50fd5dbb75c78aa0e6b003827314f21f4aa8f03facd79465c96ce44c55");
    }

    protected void setBaseUri() {
        setBaseUri(BASE_URI);
    }

    protected void setBaseUri(String uri) {
        requestSpecification.baseUri(uri);
    }

    protected void setCommonParams() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        requestSpecification.headers(headers);
    }

    public Response sendGetRequest() {
        return requestSpecification.get();
    }

    public Response sendPostRequest() {
        return requestSpecification.post();
    }

    public Response sendDeleteRequest() {
        return requestSpecification.delete();
    }
}
