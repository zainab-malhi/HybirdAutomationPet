package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.ResourceBundle;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;



public class userEndPoints2 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("Routes");
        return  routes;
    }

    public static Response createUser(user payload){

        String post_url = getURL().getString("post_url");
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post(post_url);

        return response;
    }

    public static Response getUser(String username){

        String get_url = getURL().getString("get_url");
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",username)

                .when()
                .get(get_url);

        return response;
    }

    public static Response UpdateUser(String userName, user payload)
    {
        String update_url = getURL().getString("update_url");
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)


                .when()
                .put(update_url);

        return response;
    }


    public static Response DeleteUser(String userName)
    {
        String delete_url = getURL().getString("delete_url");
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("username", userName)

                .when()
                .delete(delete_url);

        return response;
    }

}
