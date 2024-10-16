package api;

import apimodels.controlObjects.ControlObjectsAllResponse;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ControlObjectCardService {

    public static ControlObjectsAllResponse getControlObjectCard(String accTValue, String objectId) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("sort", SORT_ORDER_ASC)
                .when()
                .get("/api/knd/v1/control-objects/" + objectId)
                .then()
                .extract().as(ControlObjectsAllResponse.class);
    }
}
