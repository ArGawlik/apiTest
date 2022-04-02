package model.heirs.responses.heirs;

import io.restassured.response.Response;
import model.heirs.responses.RsObject;

public class PostResponseObject extends RsObject {
    public PostResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public String getPetName() {return response.jsonPath().get("name");}
}
