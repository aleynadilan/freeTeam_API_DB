package utilities;

import apıStepdefinitions.API_01;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static hooks.api.HooksApı.*;
import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static Response response;

    public static Response getResponse(String token){

        response = given()
                      .spec(spec)
                      .contentType(ContentType.JSON)
                      .header("Accept", "application/json")
                      .headers("Authorization", "Bearer " + token)
                  .when()
                      .get(API_01.fullPath);

        response.prettyPrint();

        return response;
    }

    public static Response postResponse(String token,Object requestBody) {

        response = given()
                     .spec(spec)
                     .contentType(ContentType.JSON)
                     .header("Accept", "application/json")
                     .headers("Authorization", "Bearer " + token)
                   .when()
                     .body(requestBody)
                     .post(API_01.fullPath);

        response.prettyPrint();

        return  response;
    }

    public static Response deleteResponse(String token, Object requestBody){

        response = given()
                     .spec(spec)
                     .contentType(ContentType.JSON)
                     .header("Accept", "application/json")
                     .headers("Authorization", "Bearer " + token)
                  .when()
                     .body(requestBody)
                     .delete(API_01.fullPath);

        response.prettyPrint();

        return response;

    }

    public static Response patchResponse(String token,Object requestBody) {

        response = given()
                      .spec(spec)
                      .contentType(ContentType.JSON)
                      .header("Accept", "application/json")
                      .headers("Authorization", "Bearer " + token)
                  .when()
                      .body(requestBody)
                      .patch(API_01.fullPath);

        response.prettyPrint();

        return  response;
    }
}
