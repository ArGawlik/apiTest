package steps;

import com.store.CategoriesStore;
import com.store.RxStore;
import com.store.TagsStore;
import entities.Category;
import entities.Pet;
import entities.Tag;
import entities.User;
import entities.enums.PetStatus;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import model.heirs.requests.RqObject;
import model.heirs.requests.heirs.DeleteRequestObject;
import model.heirs.requests.heirs.GetRequestObject;
import model.heirs.requests.heirs.PostRequestObject;
import model.heirs.responses.RsObject;
import model.heirs.responses.heirs.DeleteResponseObject;
import model.heirs.responses.heirs.GetResponseObject;
import model.heirs.responses.heirs.PostResponseObject;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;

public class Steps {
    private final CategoriesStore categoriesStore = CategoriesStore.getInstance();
    private final RxStore rxStore = RxStore.getInstance();
    private final TagsStore tagsStore = TagsStore.getInstance();


    @Given("Pet has category with following parameters")
    public void petHasCategoryWithFollowingParameters(DataTable dataTable) {
        Category category = new Category(dataTable.cell(1, 0),
                dataTable.cell(1, 1));
        categoriesStore.putCategoryIntoStore(category.getId(), category);
    }

    @Given("Pet has tags with following parameters")
    public void petHasTagsWithFollowingParameters(DataTable dataTable) {
        for (int i = 1; i < dataTable.height(); i++) {
            Tag tag = new Tag(dataTable.cell(i, 0), dataTable.cell(i, 1));
            tagsStore.putTagIntoStore(tag.getId(), tag);
        }
    }

    @Given("User has {string} request with following parameters")
    public void userHasRequestWithFollowingParameters(String rqName, DataTable dataTable) {
        PostRequestObject postRequestObject = new PostRequestObject(rqName);
        switch (rqName) {
            case "addNewPetRQ" -> {
                List<String> dataList = dataTable.row(1);
                Pet pet = new Pet(dataList);
                postRequestObject.createRequestForAddingNewPet(pet);
            }
            case "addNewUserRQ" -> {
                List<String> dataList = dataTable.row(1);
                User user = new User(dataList);
                postRequestObject.createRequestForCreatingUser(user);
            }
            default -> throw new IllegalArgumentException("Unexpected value" + rqName);
        }

        rxStore.putDataIntoStore(rqName, postRequestObject);
    }

    @When("User sends {string} {string} request")
    public void userSendsRequest(String rqMethod, String rqName) {
        RqObject rqObject = (RqObject) rxStore.getDataFromStore(rqName);
        String rsName = rqName.replace("RQ", "RS");
        RsObject receivedResponse = switch (rqMethod) {
            case "GET" -> new GetResponseObject(rsName, rqObject.sendGetRequest());
            case "POST" -> new PostResponseObject(rsName, rqObject.sendPostRequest());
            case "DELETE" -> new DeleteResponseObject(rsName, rqObject.sendDeleteRequest());
            default -> throw new IllegalStateException("Unexpected value: " + rqMethod);
        };

        rxStore.putDataIntoStore(receivedResponse.getRxName(), receivedResponse);
    }

    @Then("{string} code is {string}")
    public void codeIs(String rsName, String rsCode) {
        RsObject rsObject = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(rsObject.getStatusCode(), Integer.parseInt(rsCode),
                "Response code doesn't match");
    }

    @And("Pet name in {string} response is {string}")
    public void petNameIs(String rsName, String petName) {
        RsObject postResponseObject = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(postResponseObject.getPetName(), petName,
                "Pet name is different");
    }

    @Given("User has {string} request with id {string}")
    public void userHasRequestWithId(String rqName, String petId) {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetById(petId);

        rxStore.putDataIntoStore(rqName, getRequestObject);
    }

    @And("Message for {string} response is {string}")
    public void messageForResponseIs(String rsName, String messageId) {
        RsObject rsObject = (RsObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(rsObject.getMessage(), messageId,
                "Message id is not as expected");
    }

    @Given("User has {string} request with username {string}")
    public void userHasRequestWithUsername(String rqName, String username) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingUserByUserName(username);

        rxStore.putDataIntoStore(rqName, getRequestObject);
    }

    @And("User last name for {string} is {string}")
    public void userLastNameForIs(String rsName, String lastName) {
        GetResponseObject getResponseObject = (GetResponseObject) rxStore.getDataFromStore(rsName);
        Assert.assertEquals(getResponseObject.getUserLastName(), lastName,
                "User's last name is not as expected");
    }

    @Given("User has {string} delete request with username {string}")
    public void userHasDeleteRequestWithUsername(String rqName, String username) {
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject(rqName);
        deleteRequestObject.createRequestForDeletetingUser(username);

        rxStore.putDataIntoStore(rqName, deleteRequestObject);
    }

    @Given("User has {string} request with status {string} parameter")
    public void userHasRequestWithStatusParameter(String rqName, String status) {
        GetRequestObject getRequestObject = new GetRequestObject(rqName);
        getRequestObject.createRequestForGettingPetsByStatus(PetStatus.valueOf(status));

        rxStore.putDataIntoStore(rqName, getRequestObject);
    }

    @And("All pets from {string} have status {string}")
    public void allPetsFromHaveStatus(String rsName, String status) {
        GetResponseObject getResponseObject = (GetResponseObject) rxStore.getDataFromStore(rsName);
        List<String> petStatusList = getResponseObject.getPetsStatusList();
        Assert.assertEquals(new HashSet<>(petStatusList).size(),  1,
                "Not all elements are the same");
        Assert.assertEquals(petStatusList.get(0), status);
    }
}
