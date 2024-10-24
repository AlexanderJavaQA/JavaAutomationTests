package api;

import static api.Specifications.*;
import static api.Specifications.responseSpecOK;
import static io.restassured.RestAssured.given;

public class SituationsFilterService {

    public static void getSituationsFilter(String accTValue, String type) {
        installSpec(requestSpec(), responseSpecOK());

        given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("type", type)
                .when()
                .get("api/knd/v2/appeals/situations");
    }
}
