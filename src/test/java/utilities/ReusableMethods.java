package utilities;

import hooks.api.HooksApı;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static apıStepdefinitions.API_10.fullPath;
import static hooks.api.HooksApı.*;
import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static Response response;

    public static Response getResponse(Object reqBody){

        response = given()
                      .spec(spec)
                      .contentType(ContentType.JSON)
                      .header("Accept", "application/json")
                      .headers("Authorization", "Bearer " + "iIJ1ukvKYYLJWELH7q9oHPYo48xti3")
                  .when()
                     .body(reqBody)
                     .get(fullPath);

        response.prettyPrint();

        return response;
    }
}
