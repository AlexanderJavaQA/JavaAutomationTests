package api;

import io.restassured.response.Response;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ControlObjectsFilterService {

    public static Response getControlObjectsWithFilters(String accTValue, int pageNumber) {

        installSpec(requestSpec(), responseSpecOK());
        return given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("type", "supervision")
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .when()
                .get("/api/knd/v2/control-objects/filters");
    }
}
