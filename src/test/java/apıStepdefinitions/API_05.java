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

public class API_05 {

    JSONObject requestBody;
    Response response;
    String exceptionMesaj;
    JsonPath jsonPath;
    @When("Api kullanicisi api visitorsPurposeDelete endpointine gondermek icin bir request body hazirlar")
    public void api_kullanicisi_api_visitors_purpose_delete_endpointine_gondermek_icin_bir_request_body_hazirlar() {
        /*
        {
    "id":1250
    }
         */
        requestBody= new JSONObject();
        requestBody.put("id",1254);
    }
    @When("Api kullanicisi api visitorsPurposeDelete endpointinden donen response kaydeder")
    public void api_kullanicisi_api_visitors_purpose_delete_endpointinden_donen_response_kaydeder() {

        ReusableMethods.deleteResponse(Authentication.adminToken(),requestBody.toString());
    }

    @When("Api kullanicisi api visitorsPurposeDelete endpointine gondermek icin yanlis id iceren request body hazirlar")
    public void apiKullanicisiApiVisitorsPurposeDeleteEndpointineGondermekIcinYanlisIdIcerenRequestBodyHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("id",1265);
    }

    @And("Api kullanicisi api visitorsPurposeDelete endpointinden donen response gecersiz authorization bilgileriyle kaydeder ve status codunun '403' oldugunu dogrular")
    public void apiKullanicisiApiVisitorsPurposeDeleteEndpointindenDonenResponseGecersizAuthorizationBilgileriyleKaydederVeStatusCodununOldugunuDogrular() {

        try {
            response = given()
                    .spec(spec)
                    .contentType(ContentType.JSON)
                    .header("Accept", "application/json")
                    .headers("Authorization", "Bearer " + ConfigReader.getProperty("invalidToken"))
                    .when()
                    .body(requestBody.toString())
                    .delete(API_01.fullPath);
        } catch (Exception e) {
            exceptionMesaj=e.getMessage();
        }

        System.out.println("exceptionMesaj : " + exceptionMesaj);

        assertTrue(exceptionMesaj.contains("status code: 403"));
    }

    @Then("Api kullanicisi response body icindeki DeletedId bilgisinin api visitorsPurposeDelete endpointine gonderilen request body icindeki id bilgisi ile ayni oldugunu dogrular")
    public void apiKullanicisiResponseBodyIcindekiDeletedIdBilgisininApiVisitorsPurposeDeleteEndpointineGonderilenRequestBodyIcindekiIdBilgisiIleAyniOldugunuDogrular() {

        jsonPath = ReusableMethods.response.jsonPath();
        assertEquals(requestBody.get("id"),jsonPath.get("DeletedId"));
    }
}
