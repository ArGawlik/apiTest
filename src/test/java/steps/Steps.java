package steps;

import com.store.CategoriesStore;
import com.store.RxStore;
import com.store.TagsStore;
import entities.Category;
import entities.Tag;
import entities.enums.PetStatus;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.heirs.requests.RqObject;
import model.heirs.requests.heirs.PostRequestObject;
import model.heirs.responses.RsObject;
import model.heirs.responses.heirs.DeleteResponseObject;
import model.heirs.responses.heirs.GetResponseObject;
import model.heirs.responses.heirs.PostResponseObject;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Steps {
    private CategoriesStore categoriesStore = CategoriesStore.getInstance();
    private RxStore rxStore = RxStore.getInstance();
    private TagsStore tagsStore = TagsStore.getInstance();

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
        List<String> dataList = dataTable.row(1);
        String id = dataList.get(0);
        Category category = categoriesStore.getCategoryFromStore(dataList.get(1));
        String name = dataList.get(2);
        String[] photoUrls = dataList.get(3).split(", ");
        Tag[] tags1 = Arrays.stream(dataList.get(4).split(", "))
                .map(x -> tagsStore.getTagFromStore(x)).toArray(Tag[]::new);
        PetStatus status = PetStatus.valueOf(dataList.get(5));
        postRequestObject.createRequestForAddingNewPet(id, category, name, photoUrls, tags1, status);
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
}
