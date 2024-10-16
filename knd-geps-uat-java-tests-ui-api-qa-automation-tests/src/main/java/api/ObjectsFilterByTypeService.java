package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ObjectsFilterByTypeService {

    public static Response getTypeOfControlFilte(String accTValue, int pageNumber, String controlTypeId) {

        installSpec(requestSpec(), responseSpecOK());
        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("type", TYPE_KND_APPEAL)
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("sort", SORT_ORDER_ASC)
                .queryParam("controlTypeId", controlTypeId)
                .when()
                .get("/api/knd/v1/control-objects");
    }
}
