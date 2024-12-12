package api.testcases;

import api.payload.user;
import api.endpoints.userEndPoints;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class UserTest {

    Faker faker;
    user userPayload;
    public static Logger logger;

    @BeforeClass
    public void generateTestData(){
        faker = new Faker();
        userPayload = new user();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger("PetRestAPIFramework");

    }

    @Test(priority = 1)
    public void testCreateUser(){

        Response response = userEndPoints.createUser(userPayload);

        System.out.println("Read User data");

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("Create user executed");
    }


    @Test(priority = 2)
    public void GetUserData(){

        Response response = userEndPoints.getUser(this.userPayload.getUsername());

        System.out.println("Update User data");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Get User Data executed.");
    }

    @Test(priority = 3)
    public void testUpdateUser(){

        userPayload.setFirstName(faker.name().username());

        Response response = userEndPoints.UpdateUser(this.userPayload.getUsername(), userPayload);

        System.out.println("Delete  User data");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Update User executed.");


    }

    @Test(priority = 4)
    public void testDeleteUser(){


        Response response = userEndPoints.DeleteUser(this.userPayload.getUsername());

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Delete User executed.");

    }






}
