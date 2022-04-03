package model.heirs.requests.heirs;

import entities.enums.PetStatus;
import log.Log;
import model.heirs.requests.RqObject;
import org.apache.http.HttpStatus;

public class GetRequestObject extends RqObject {
    public GetRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForGettingPetById(String petId) {
        setBaseUri("https://petstore.swagger.io/v2/pet/" + petId);
        setCommonParams();
//        requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
//                .when().get(prepareUri.apply("/v2/pet/" + petId));
    }

    public void createRequestForGettingPetsByStatus(PetStatus status) {
        setBaseUri("https://petstore.swagger.io/v2/pet/findByStatus");
        requestSpecification.queryParam("status", status.name());
        setCommonParams();
    }

    public void createRequestForGettingUserByUserName(String username) {
        setBaseUri("https://petstore.swagger.io/v2/user/" + username);
        setCommonParams();
//        requestSpecification.expect().statusCode(HttpStatus.SC_OK).log().ifError()
//                .when().get(prepareUri.apply("/v2/user/" + username));
    }
}
