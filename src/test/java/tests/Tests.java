package tests;

import entities.Category;
import entities.Tag;
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
        getRequestObject.createRequestForGettingPetById("2");
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertEquals(getResponseObject.getUserName(),"Apolllo");
    }

    @Test
    public void getAvailablePets() {
        GetRequestObject getRequestObject = new GetRequestObject("asd");
        getRequestObject.createRequestForGettingPetsByStatus(PetStatus.available);
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertFalse(getResponseObject.getPets().isEmpty());
    }
    @Test
    public void getUsers() {
        GetRequestObject getRequestObject = new GetRequestObject("asd");
        getRequestObject.createRequestForGettingUsers();
        GetResponseObject getResponseObject = new GetResponseObject("asd", getRequestObject.sendGetRequest());
        Assert.assertFalse(getResponseObject.getPets().isEmpty());
    }

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
        postRequestObject.createRequestForCreatingUser(1, "username", "firstName", "lastName"
        , "email@gmail.com", "some_password123", "123456789", 0);
        PostResponseObject postResponseObject = new PostResponseObject("asd", postRequestObject.sendPostRequest());
        Assert.assertEquals(postResponseObject.getUserId(), "1");
    }

    @Test
    public void deletePet(){
        DeleteRequestObject deleteRequestObject = new DeleteRequestObject("asd");
        deleteRequestObject.createRequestForDeletingUser("1");
        DeleteResponseObject deleteResponseObject = new DeleteResponseObject("asd", deleteRequestObject.sendDeleteRequest());
        Assert.assertEquals(deleteResponseObject.getStatusCode(), 200);
    }

    @Test
    public void createPet() {
        PostRequestObject postRequestObject = new PostRequestObject("asd");
        Category category = new Category(1, "dog");
        Tag tag = new Tag(123,"some tag");
        postRequestObject.createRequestForCreatingPet(1, category, "Rex", "https://photo.com",tag,PetStatus.available);
        PostResponseObject postResponseObject = new PostResponseObject("asd", postRequestObject.sendPostRequest());
        Assert.assertEquals(postResponseObject.getPetName(), "Rex");
    }


}