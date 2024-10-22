package api;

import lombok.SneakyThrows;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ErknmListResponseService {

    @SneakyThrows
    public static void getErpInspectionsListResponseData(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/lk/v2/procuracy/informers/knd/search");

    }
}
