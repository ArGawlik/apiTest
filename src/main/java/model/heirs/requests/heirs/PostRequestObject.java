package model.heirs.requests.heirs;

import entities.Pet;
import entities.Tag;
import entities.User;
import model.heirs.requests.RqObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostRequestObject extends RqObject {
    public PostRequestObject(String rqName) {
        super(rqName);
    }

    public void createRequestForCreatingUser(User user) throws JSONException {
        setBaseUri("https://petstore.swagger.io/v2/user");
        setCommonParams();
        requestSpecification.body(createUserAsJsonObject(user).toString());
    }

    private JSONObject createUserAsJsonObject(User user) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", user.getId());
        requestBody.put("username", user.getUsername());
        requestBody.put("firstName", user.getFirstName());
        requestBody.put("lastName", user.getLastName());
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getUsername());
        requestBody.put("phone", user.getPhone());
        requestBody.put("userStatus", user.getUserStatus());
        return requestBody;
    }

    public void createRequestForAddingNewPet(Pet pet) throws JSONException {
        setBaseUri("https://petstore.swagger.io/v2/pet");
        setCommonParams();
        requestSpecification.body(createPetAsJsonObject(pet).toString());
    }

    private JSONObject createPetAsJsonObject(Pet pet) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", pet.getId());
        requestBody.put("category", createCategoryAsJsonObject(pet.getCategory().getId(), pet.getCategory().getName()));
        requestBody.put("name", pet.getName());
        requestBody.put("photoUrls", createArrayForPhotoUrls(pet.getPhotoUrls()));
        requestBody.put("tags", createTagAsJsonObject(pet.getTags()));
        requestBody.put("status", pet.getStatus().name());
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
