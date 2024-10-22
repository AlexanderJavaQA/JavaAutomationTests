package api;

import apimodels.controlObjects.ControlObjectsAllResponse;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ControlObjectsService {
    public static ControlObjectsAllResponse getAllControlObjects(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("sort", SORT_ORDER_ASC)
                .when()
                .get("/api/knd/v1/control-objects")
                .then()
                .extract().as(ControlObjectsAllResponse.class);
    }
}
