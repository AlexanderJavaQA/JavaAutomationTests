package api;

import apimodels.appeals.Items;

import java.util.HashMap;
import java.util.Map;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class DraftsListService {

    public static Items getDraftsList(String accTValue, String sortOrder) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("sort", sortOrder);
        requestBody.put("pageSize", PAGE_SIZE_LARGE );

        installSpec(requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("api/knd/v2/appeals/drafts")
                .then()
                .extract().as(Items.class);
}}
