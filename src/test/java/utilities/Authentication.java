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

    public static String adminToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        Map<String,Object> dataCredentials = new HashMap<>();

        dataCredentials.put("email",ConfigReader.getProperty("adminUserName"));
        dataCredentials.put("password",ConfigReader.getProperty("password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(dataCredentials)
                .post("/{pp1}/{pp2}");

        response.prettyPrint();

        JsonPath jsonResponse = response.jsonPath();

        String token = jsonResponse.getString("token");

        return token;
    }

    public static String teacherToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        Map<String,Object> dataCredentials = new HashMap<>();

        dataCredentials.put("email",ConfigReader.getProperty("teacherUserName"));
        dataCredentials.put("password",ConfigReader.getProperty("password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(dataCredentials)
                .post("/{pp1}/{pp2}");

        response.prettyPrint();
        JsonPath jsonResponse = response.jsonPath();

        String token = jsonResponse.getString("token");

        return token;
    }

    public static String studentToken(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","apistudent","pp2","getToken");

        Map<String,Object> dataCredentials = new HashMap<>();

        dataCredentials.put("username",ConfigReader.getProperty("studentUserName"));
        dataCredentials.put("password",ConfigReader.getProperty("password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec)
                .when()
                .body(dataCredentials)
                .post("/{pp1}/{pp2}");

        response.prettyPrint();
        JsonPath jsonResponse = response.jsonPath();

        String token = jsonResponse.getString("token");

        return token;
    }

}
