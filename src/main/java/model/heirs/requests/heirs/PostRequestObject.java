package model.heirs.requests.heirs;

import entities.Category;
import entities.Tag;
import entities.enums.PetStatus;
import model.heirs.requests.RqObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForCreatingUser(int id, String username, String firstName, String lastName, String email,
                                             String password, String phone, int userStatus) throws JSONException {
        setBaseUri("https://petstore.swagger.io/v2/user");
        setCommonParams();
        requestSpecification.body(createUserAsJsonObject(id, username, firstName, lastName, email, password, phone, userStatus).toString());
    }

    private JSONObject createUserAsJsonObject(int id, String username, String firstName, String lastName, String email,
                                              String password, String phone, int userStatus) throws JSONException {
        JSONObject requestBody = new JSONObject();
            requestBody.put("id", id);
            requestBody.put("username", username);
            requestBody.put("firstName", firstName);
            requestBody.put("lastName", lastName);
            requestBody.put("email", email);
            requestBody.put("password", password);
            requestBody.put("phone", phone);
            requestBody.put("userStatus", userStatus);
        return requestBody;
    }

    public void createRequestForAddingNewPet(String id, Category category, String name, String[] photoUrls,
                                             Tag[] tags, PetStatus status) throws JSONException {
        setBaseUri("https://petstore.swagger.io/v2/pet");
        setCommonParams();
        requestSpecification.body(createPetAsJsonObject(id, category, name, photoUrls, tags, status).toString());
    }

    private JSONObject createPetAsJsonObject(String id, Category category, String name, String[] photoUrls, Tag[] tags, PetStatus status) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("category", createCategoryAsJsonObject(category.getId(), category.getName()));
        requestBody.put("name", name);
        requestBody.put("photoUrls", createArrayForPhotoUrls(photoUrls));
        requestBody.put("tags", createTagAsJsonObject(tags));
        requestBody.put("status", status.name());
        return requestBody;
    }

    private JSONArray createTagAsJsonObject(Tag[] tags) {
        JSONArray jsonArray = new JSONArray();
        for (Tag tag: tags) {
            JSONObject requestBody = new JSONObject();
            requestBody.put("id", tag.getId());
            requestBody.put("name", tag.getName());
            jsonArray.put(requestBody);
        }
        return jsonArray;
    }

    private JSONArray createArrayForPhotoUrls(String[] urls) {
        JSONArray jsonArray = new JSONArray();
        for (String url: urls) {
            jsonArray.put(url);
        }
        return jsonArray;
    }

    private JSONObject createCategoryAsJsonObject(String id, String name) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("name", name);
        return requestBody;
    }

}
