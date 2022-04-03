package model.heirs.responses.heirs;

import io.restassured.response.Response;
import model.heirs.responses.RsObject;
import java.util.List;

public class GetResponseObject extends RsObject {
    public GetResponseObject(String rsName, Response response) {
        super(rsName, response);
    }

    public String getUserName() {
        return response.jsonPath().get("username");
    }

    public String getPetName() {
        return response.jsonPath().get("name");
    }

    public String getUserLastName() {
        return response.jsonPath().get("lastName");
    }

    public List<String> getPetsStatusList() {
        return response.jsonPath().getList("status");
    }

}
