package model.heirs.requests.heirs;


import model.heirs.requests.RqObject;

public class DeleteRequestObject extends RqObject {
    public DeleteRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForDeletingPet(int petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }

    public void createRequestForDeletetingUser(String username) {
        setBaseUri("https://petstore.swagger.io/v2/user/" + username);
    }
}
