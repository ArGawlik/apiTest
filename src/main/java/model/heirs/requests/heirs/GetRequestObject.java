package model.heirs.requests.heirs;

import entities.enums.PetStatus;
import model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingPetById(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }

    public void createRequestForGettingPetsByStatus(PetStatus status) {
        setBaseUri("https://petstore.swagger.io/v2/pet/findByStatus");
        requestSpecification.queryParam("status", status.name());
        setCommonParams();
    }

    public void createRequestForGettingUserByUserName(String username) {
        setBaseUri("https://petstore.swagger.io/v2/user/" + username);
        setCommonParams();
    }
}
