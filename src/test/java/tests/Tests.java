package tests;

import entities.Category;
import entities.Pet;
import entities.Tag;
import entities.User;
import entities.enums.PetStatus;
import model.heirs.requests.heirs.DeleteRequestObject;
import model.heirs.requests.heirs.GetRequestObject;
import model.heirs.requests.heirs.PostRequestObject;
import model.heirs.responses.heirs.DeleteResponseObject;
import model.heirs.responses.heirs.GetResponseObject;
import model.heirs.responses.heirs.PostResponseObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {
    @Test
    public void getPetById() {
        GetRequestObject getRequestObject = new GetRequestObject("asd");
        getRequestObject.createRequestForGettingPetById("2137");
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertEquals(getResponseObject.getPetName(), "Rex");
    }

    @Test
    public void getAvailablePets() {
        GetRequestObject getRequestObject = new GetRequestObject("asd");
        getRequestObject.createRequestForGettingPetsByStatus(PetStatus.available);
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertFalse(getResponseObject.getPetsStatusList().isEmpty());
    }
//    @Test
//    public void getUsers() {
//        GetRequestObject getRequestObject = new GetRequestObject("asd");
//        getRequestObject.createRequestForGettingUsers();
//        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
//        Assert.assertFalse(getResponseObject.getPets().isEmpty());
//    }

    @Test
    public void getUserByUserName() {
        GetRequestObject getRequestObject = new GetRequestObject("asd");
        getRequestObject.createRequestForGettingUserByUserName("string");
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertEquals(getResponseObject.getUserName(), "string");
    }

    @Test
    public void createUser() {
        PostRequestObject postRequestObject = new PostRequestObject("asd");
        User user = new User("2137", "username", "firstName", "lastName"
                , "email@gmail.com", "some_password123", "123456789", "0");
        postRequestObject.createRequestForCreatingUser(user);
        PostResponseObject postResponseObject = new PostResponseObject("asd", postRequestObject.sendPostRequest());
        Assert.assertEquals(postResponseObject.getMessage(), "1");
    }

    @Test
    public void deleteUser() {
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject("asd");
        deleteRequestObject.createRequestForDeletetingUser("username");
        DeleteResponseObject deleteResponseObject = new DeleteResponseObject("asd", deleteRequestObject.sendDeleteRequest());
        Assert.assertEquals(deleteResponseObject.getStatusCode(), 200);
    }

    @Test
    public void deletePet() {
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject("asd");
        deleteRequestObject.createRequestForDeletingPet(1);
        DeleteResponseObject deleteResponseObject = new DeleteResponseObject("asd", deleteRequestObject.sendDeleteRequest());
        Assert.assertEquals(deleteResponseObject.getStatusCode(), 200);
    }

    @Test
    public void createPet() {
        PostRequestObject postRequestObject = new PostRequestObject("asd");
        Category category = new Category("1", "dog");
        Tag tag = new Tag("123", "some tag");
        Tag[] tags = new Tag[]{tag};
        String[] urls = new String[]{"https://photo.com"};
        Pet pet = new Pet("2137", category, "Rex", urls, tags, PetStatus.available);
        postRequestObject.createRequestForAddingNewPet(pet);
        PostResponseObject postResponseObject = new PostResponseObject("asd", postRequestObject.sendPostRequest());
        Assert.assertEquals(postResponseObject.getPetName(), "Rex");
    }
}