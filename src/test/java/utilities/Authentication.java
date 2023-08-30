package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentication {
    private static RequestSpecification spec;

    public static String generateToken() {

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1", "api", "pp2", "getToken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("email"));
        reqBody.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                               .spec(spec)
                               .contentType(ContentType.JSON)
                           .when()
                               .body(reqBody.toString())
                               .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String token = resJP.getString("token");

        return token;
    }

    public static String generateToken1() {

        String url = "https://www.heallifehospital.com/api/getToken";

        Map<String,Object> reqBody = new HashMap<>();

        reqBody.put("email", "hatice.kubra.ceylan.admin@heallifehospital.com");
        reqBody.put("password","heallife123");

        Response response = given()
                             .contentType(ContentType.JSON)
                            .when()
                             .body(reqBody)
                             .post(url);

        response.prettyPrint();

        JsonPath token = response.jsonPath();

        return token.getString("token");
    }

    public static void main(String[] args) {
        System.out.println(generateToken1());
    }

    @Test
    public void test01(){

        String url = "https://www.heallifehospital.com/api/getToken";

        Map<String,Object> reqBody = new HashMap<>();

        reqBody.put("email", ConfigReader.getProperty("email"));
        reqBody.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .get(url);

        response
                .then()
                .assertThat()
                .body("status", equalTo(200),
                        "token",equalTo("M7yjLvpb9092mvAUMZJ9qXx6INERSv"),
                        "userId",equalTo("597"));



        /*
        {
    "status": 200,
    "token": ""M7yjLvpb9092mvAUMZJ9qXx6INERSv"",
    "userId": "597",
    "staff_designation_id": "1",
    "Token_remaining_time": 511
     }
         */
    }
}
