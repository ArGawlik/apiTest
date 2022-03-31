package model.heirs.requests.heirs;

import entities.enums.PetStatus;
import model.heirs.requests.RqObject;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingUsers() {
        setBaseUri("https://gorest.co.in/public/v2/users");
        setCommonParams();
    }

    public void createRequestForGettingPetById(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }

    public void createRequestForGettingPetsByStatus(PetStatus status) {
        setBaseUri("https://petstore.swagger.io/v2/pet/findByStatus?status=" + "available");
        setCommonParams();
    }

    public void createRequestForGettingUserByUserName(String id) {
        setBaseUri("https://petstore.swagger.io/v2/user/" + id);
        setCommonParams();
    }
}
