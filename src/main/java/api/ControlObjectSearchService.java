package api;

import apimodels.controlObjects.ControlObjectsAllResponse;

import static api.ControlObjectCardService.*;
import static api.Specifications.*;
import static api.Specifications.DEFAULT_PAGE_NUMBER;
import static api.Specifications.DEFAULT_PAGE_SIZE;
import static api.Specifications.SORT_ORDER_ASC;
import static io.restassured.RestAssured.given;

public class ControlObjectSearchService {

    public static ControlObjectsAllResponse getControlObjectSearch(String accTValue, String query) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("sort", SORT_ORDER_ASC)
                .queryParam("query", query)
                .when()
                .get("/api/knd/v1/control-objects")
                .then()
                .extract().as(ControlObjectsAllResponse.class);
    }
}
