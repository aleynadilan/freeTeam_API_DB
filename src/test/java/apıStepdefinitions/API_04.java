package apıStepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import utilities.Authentication;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static hooks.api.HooksApı.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class API_04 {

    JSONObject requestBody;
    Response response;
    String exceptionMesaj;

    JsonPath jsonPath;

    @When("Api kullanicisi api visitorsPurposeUpdate endpointine gondermek icin bir request body hazirlar")
    public void api_kullanicisi_api_visitors_purpose_update_endpointine_gondermek_icin_bir_request_body_hazirlar() {

        /*
        {
    "id":1248,
    "visitors_purpose":"purpose",
    "description":"came for student visit"
    }
         */
        requestBody = new JSONObject();
        requestBody.put("id",1248);
        requestBody.put("visitors_purpose","purpose");
        requestBody.put("description","came for student visit");
    }
    @When("Api kullanicisi api visitorsPurposeUpdate endpointinden donen response kaydeder")
    public void api_kullanicisi_api_visitors_purpose_update_endpointinden_donen_response_kaydeder() {

        ReusableMethods.patchResponse(Authentication.adminToken(),requestBody.toString());
    }


    @When("Api kullanicisi api visitorsPurposeUpdate endpointine gondermek icin eksik id iceren request body hazirlar")
    public void apiKullanicisiApiVisitorsPurposeUpdateEndpointineGondermekIcinEksikIdIcerenRequestBodyHazirlar() {

          /*
        {

    "visitors_purpose":"purpose",
    "description":"came for student visit"
     }
         */

        requestBody = new JSONObject();
        requestBody.put("visitors_purpose","purpose");
        requestBody.put("description","came for student visit");
    }

    @And("Api kullanicisi api visitorsPurposeUpdate endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular")
    public void apiKullanicisiApiVisitorsPurposeUpdateEndpointindenDonenResponseGecersizAuthorizationBilgileriyleKaydederVeStatusCodununOldugunuDogrular() {

        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                    .when()
                    .body(requestBody.toString())
                    .patch(API_01.fullPath);
        } catch (Exception e) {
            exceptionMesaj=e.getMessage();
        }

        System.out.println("exceptionMesaj : " + exceptionMesaj);

        assertTrue(exceptionMesaj.contains("status code: 403"));

    }

    @Then("Api kullanicisi response body icindeki updateId bilgisinin api visitorsPurposeUpdate endpointine gonderilen request body icindeki id bilgisi ile ayni oldugunu dogrular")
    public void apiKullanicisiResponseBodyIcindekiUpdateIdBilgisininApiVisitorsPurposeUpdateEndpointineGonderilenRequestBodyIcindekiIdBilgisiIleAyniOldugunuDogrular() {

        jsonPath = ReusableMethods.response.jsonPath();

        assertEquals(requestBody.get("id"),jsonPath.get("updateId"));
    }

    @Then("Api kullanicisi response bodyde donen updateId ile apivisitorsPurposeId endpointine POST body gondererek kaydin guncellendigini dogrular")
    public void apiKullanicisiResponseBodydeDonenUpdateIdIleApivisitorsPurposeIdEndpointinePOSTBodyGondererekKaydinGuncellendiginiDogrular() {

        jsonPath = ReusableMethods.response.jsonPath();
        assertEquals("purpose",jsonPath.get("lists.visitors_purpose"));

    }
}
