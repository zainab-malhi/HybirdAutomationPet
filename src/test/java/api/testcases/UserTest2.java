package api.testcases;

import api.endpoints.userEndPoints2;
import api.payload.user;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTest2 {

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

        Response response = userEndPoints2.createUser(userPayload);

        System.out.println("Read User data");

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("Create user executed");
    }


    @Test(priority = 2)
    public void GetUserData(){

        Response response = userEndPoints2.getUser(this.userPayload.getUsername());

        System.out.println("Update User data");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Get User Data executed.");
    }

    @Test(priority = 3)
    public void testUpdateUser(){

        userPayload.setFirstName(faker.name().username());

        Response response = userEndPoints2.UpdateUser(this.userPayload.getUsername(), userPayload);

        System.out.println("Delete  User data");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Update User executed.");


    }

    @Test(priority = 4)
    public void testDeleteUser(){


        Response response = userEndPoints2.DeleteUser(this.userPayload.getUsername());

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Delete User executed.");

    }






}
