package api;

import apimodels.businessActivity.BusinessActivityCardListPojo;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class BusinessActivityCardService {

    public static BusinessActivityCardListPojo getObjectsOfBusinessActivityCardList(String accTValue, String id) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .when()
                .get("/api/knd/v1/orgactivity/objects/" + id)
                .then()
                .extract().as(BusinessActivityCardListPojo.class);
    }

}
