package apıStepdefinitions;

import hooks.api.HooksApı;
import io.cucumber.java.en.Given;

import java.util.Arrays;

public class API_01 {

    public static String fullPath;

    @Given("Api kullanicisi {string} path parametreleri set eder.")
    public void api_kullanicisi_path_parametreleri_set_eder(String rawPaths) {

        String[] paths = rawPaths.split("/"); // [api, visitorsPurposeDelete]

        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            HooksApı.spec.pathParam(key, value);

            tempPath.append(key + "}/{"); // /{pp0}/{pp1}
        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }


}
