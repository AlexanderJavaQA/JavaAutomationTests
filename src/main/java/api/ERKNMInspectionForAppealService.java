package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class ERKNMInspectionForAppealService {
    public static Response getERKNMInspectionForAppeal(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("situationId", INITIAL_SITUATION)
                .queryParam("inspectionId", DEFAULT_INSPECTION)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .when()
                .get("/api/knd/v3/inspection/searchForAppeal");
    }
}
