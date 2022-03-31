package model.heirs.responses.heirs;

import io.restassured.response.Response;
import model.heirs.responses.RsObject;

public class DeleteResponseObject extends RsObject {
    public DeleteResponseObject(String rsName, Response response) {
        super(rsName, response);
    }
}
