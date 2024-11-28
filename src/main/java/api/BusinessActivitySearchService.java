package api;

import apimodels.businessActivity.BusinessActivityList;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class BusinessActivitySearchService {

    public static BusinessActivityList getObjectsOfBusinessActivitySearch(String accTValue, String name) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("name", name)
                .when()
                .get("/api/knd/v1/orgactivity/objects")
                .then()
                .extract().as(BusinessActivityList.class);
    }
}
