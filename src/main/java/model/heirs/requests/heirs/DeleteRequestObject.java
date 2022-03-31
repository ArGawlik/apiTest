package model.heirs.requests.heirs;


import model.heirs.requests.RqObject;

public class DeleteRequestObject extends RqObject {
    public DeleteRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForDeletingUser(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
    }
}
