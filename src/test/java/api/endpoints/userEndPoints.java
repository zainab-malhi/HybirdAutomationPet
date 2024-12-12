package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class userEndPoints {

    public static Response createUser(user payload){

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response getUser(String username){

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)

                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response UpdateUser(String userName, user payload)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)


                .when()
                .put(Routes.put_url);

        return response;
    }


    public static Response DeleteUser(String userName)
    {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)


                .when()
                .delete(Routes.del_url);

        return response;
    }

}
