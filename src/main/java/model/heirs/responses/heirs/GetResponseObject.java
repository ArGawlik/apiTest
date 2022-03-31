package model.heirs.responses.heirs;

import io.restassured.response.Response;
import model.heirs.responses.RsObject;

import java.util.List;

public class GetResponseObject extends RsObject {
    private static final String RESULT_LOCATOR = "data";
    private static final String RESULT_NAME_LOCATOR = "username";

    public GetResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public List<String> getAllUsers() {
        return response.jsonPath().getList(RESULT_LOCATOR);
    }

    public String getUserName() {
        return response.jsonPath().get(RESULT_NAME_LOCATOR);
    }

    public List<String> getPets() {
        return response.jsonPath().getList("status");
    }
}
