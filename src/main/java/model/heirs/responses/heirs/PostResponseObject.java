package model.heirs.responses.heirs;

import io.restassured.response.Response;
import model.heirs.responses.RsObject;

public class PostResponseObject extends RsObject {
    private static final String RESULT_ID_LOCATOR = "message";

    public PostResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public String getUserId() {
        return response.jsonPath().get(RESULT_ID_LOCATOR);
    }

}
