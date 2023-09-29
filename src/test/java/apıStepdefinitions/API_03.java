package apıStepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import utilities.Authentication;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static hooks.api.HooksApı.spec;
import static io.restassured.RestAssured.given;

public class API_03 {

    JSONObject requestBody;
    Response response;

    @When("Api kullanicisi api visitorsPurposeAdd endpointine gondermek icin bir request body hazirlar")
    public void api_kullanicisi_api_visitors_purpose_add_endpointine_gondermek_icin_bir_request_body_hazirlar() {

        /*
        {
    "visitors_purpose":"Veli Ziyareti...",
    "description":"Veli Ziyareti İçin Gelindi"
       }
         */

        requestBody = new JSONObject();
        requestBody.put("visitors_purpose","Veli Ziyareti...");
        requestBody.put("description","Veli Ziyareti İçin Gelindi");
    }
    @When("Api kullanicisi api visitorsPurposeAdd endpointinden donen response kaydeder")
    public void api_kullanicisi_api_visitors_purpose_add_endpointinden_donen_response_kaydeder() {

        ReusableMethods.postResponse(Authentication.adminToken(),requestBody.toString());

    }
    @Then("Api kullanicisi donen status codein {int} oldugunu ve response bodydeki message bilgisinin {string} oldugunu dogrular")
    public void api_kullanicisi_donen_status_codein_oldugunu_ve_response_bodydeki_message_bilgisinin_oldugunu_dogrular(Integer code, String message) {

        ReusableMethods.response
              .then()
                .assertThat()
                    .statusCode(code)
                    .body("message", Matchers.equalTo(message));
    }

    @When("Api kullanicisi api visitorsPurposeAdd endpointine gondermek icin eksik datalar iceren request body hazirlar")
    public void apiKullanicisiApiVisitorsPurposeAddEndpointineGondermekIcinEksikDatalarIcerenRequestBodyHazirlar() {

        requestBody = new JSONObject();
        requestBody.put("visitors_purpose","Veli Ziyareti...");

    }

    @And("Api kullanicisi api visitorsPurposeAdd endpointinden donen response gecersiz authorization bilgileriyle kaydeder")
    public void apiKullanicisiApiVisitorsPurposeAddEndpointindenDonenResponseGecersizAuthorizationBilgileriyleKaydeder() {

        ReusableMethods.postResponse(ConfigReader.getProperty("invalidToken"),requestBody.toString());
    }

    @When("Api kullanicisi api visitorsPurposeId endpointine gondermek icin bir request body hazirlar")
    public void apiKullanicisiApiVisitorsPurposeIdEndpointineGondermekIcinBirRequestBodyHazirlar() {

        /*
        {
    "id":1246
      }
         */
        requestBody = new JSONObject();
        requestBody.put("id",1254);
    }

    @And("Api kullanicisi api visitorsPurposeId endpointinden donen response kaydeder")
    public void apiKullanicisiApiVisitorsPurposeIdEndpointindenDonenResponseKaydeder() {

        ReusableMethods.postResponse(Authentication.adminToken(),requestBody.toString());
    }
}
