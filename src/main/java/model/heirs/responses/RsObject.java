package model.heirs.responses;

import io.restassured.response.Response;
import model.RxObject;

public class RsObject extends RxObject {
    protected Response response;

    public RsObject(String rsName, Response response) {
        super(rsName);
        this.response = response;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getPetName() {
        return response.jsonPath().get("name");
    }

    public String getMessage() {return response.jsonPath().get("message"); }
}
