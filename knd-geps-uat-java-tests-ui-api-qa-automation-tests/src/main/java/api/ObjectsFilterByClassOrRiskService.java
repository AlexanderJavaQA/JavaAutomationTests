package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ObjectsFilterByClassOrRiskService {

    public static Response getObjectsFilterByClassOrRisk(String accTValue, int pageNumber, String riskCategoryId) {

        installSpec(requestSpec(), responseSpecOK());
        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("sort", SORT_ORDER_ASC)
                .queryParam("riskCategoryId", riskCategoryId)
                .when()
                .get("/api/knd/v1/control-objects");
    }
}
