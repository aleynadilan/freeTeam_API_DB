package apıStepdefinitions;

import hooks.api.HooksApı;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utilities.Authentication;

import java.util.Arrays;

import static hooks.api.HooksApı.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class API_01 {

    public static String fullPath;
    Response response;
    @Given("Api kullanicisi {string} path parametreleri set eder.")
    public void api_kullanicisi_path_parametreleri_set_eder(String rawPaths) {

        String[] paths = rawPaths.split("/"); // [api, visitorsPurposeId]

        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            HooksApı.spec.pathParam(key, value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }


    @When("Api kullanicisi api visitorsPurposeList endpointden donen response kaydeder")
    public void apiKullanicisiApiVisitorsPurposeListEndpointdenDonenResponseKaydeder() {

        response = given()
                      .spec(spec)
                      .contentType(ContentType.JSON)
                      .header("Accept", "application/json")
                      .headers("Authorization", "Bearer " + Authentication.adminToken())
                 .when()
                      .get(API_01.fullPath);

        response.prettyPrint();

    }

    @Then("Api kullanicisi donen status codein {int} oldugunu ve response bodydeki message bilgisinin {string} oldugunu dogrular")
    public void apiKullanicisiDonenStatusCodeinOldugunuVeResponseBodydekiMessageBilgisininOldugunuDogrular(int statusCode, String message) {

        response
          .then()
             .assertThat()
                .statusCode(statusCode)
                .body("message", Matchers.equalTo(message));

    }
}
