package api;

import apimodels.appeals.Items;

import java.util.HashMap;
import java.util.Map;

import static api.Specifications.*;
import static api.Specifications.responseSpecOK;
import static io.restassured.RestAssured.given;

public class AppealsSearchService {
    public static Items getAppealsSearch(String accTValue, String sortOrder, String query) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("sort", sortOrder );
        requestBody.put("pageSize", PAGE_SIZE_LARGE );
        requestBody.put("q", query );

        installSpec(requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("api/knd/v2/appeals")
                .then()
                .extract().as(Items.class);
    }
}
