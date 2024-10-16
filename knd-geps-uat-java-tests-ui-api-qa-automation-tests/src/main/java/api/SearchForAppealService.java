package api;

import apimodels.erknm.SearchForAppealPojo;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class SearchForAppealService {

    public static SearchForAppealPojo getSearchForAppeal(String accTValue, String inspectionId, String situationId) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("situationId", situationId)
                .queryParam("inspectionId", inspectionId)
                .when()
                .get("/api/knd/v3/inspection/searchForAppeal")
                .then()
                .extract().response().as(SearchForAppealPojo.class);
    }
}
