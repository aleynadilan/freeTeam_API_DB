package apıStepdefinitions;

import hooks.api.HooksApı;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import utilities.ReusableMethods;

import java.util.Arrays;

public class API_10 {

    public static String fullPath;
    JSONObject reqBody;

    @Given("Api kullanicisi {string} path parametreleri set eder.")
    public void apiKullanicisiPathParametreleriSetEder(String rawPaths) {

        // https://trendlifebuy.com/api/profile/allCountries

        // spec.pathParams("pp1","api","pp2","profile","pp3","allCountries");

        String [] paths = rawPaths.split("/"); // ["api","profile","allCountries"]

        System.out.println(Arrays.toString(paths));
       /*
        spec.pathParam("pp1","api");
        spec.pathParam("pp2","profile");
        spec.pathParam("pp3","allCountries");
        */

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            HooksApı.spec.pathParam(key,value);

            tempPath.append(key + "}/{");
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }

    @When("Api kullanicisi api visitorsId endpointe gonderilecek request bodyi hazırlar")
    public void api_kullanicisi_api_visitors_ıd_endpointe_gonderilecek_request_bodyi_hazırlar() {

        /*
        {
         "id":28
        }
         */

         reqBody = new JSONObject();
         reqBody.put("id", 28);

        System.out.println("reqBody : " + reqBody);


    }
    @When("Api kullanicisi api visitorsId endpointden donen response kaydeder")
    public void apiKullanicisiApiVisitorsIdEndpointdenDonenResponseKaydeder() {
        ReusableMethods.getResponse(reqBody.toString());
    }

    @Then("Api kullanicisi donen status codein {int} oldugunu ve response bodydeki message bilgisinin {string} oldugunu dogrular")
    public void apiKullanicisiDonenStatusCodeinOldugunuVeResponseBodydekiMessageBilgisininOldugunuDogrular(int statusCode, String message) {

        ReusableMethods.response
                          .then()
                             .assertThat()
                             .statusCode(statusCode)
                             .body("message", Matchers.equalTo(message));

    }

}
