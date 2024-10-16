package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

public class DocumentExchangeTabService {
    public static Response getDocumentExchangeTab(String accTValue, String erknmId) {

        installSpec (requestSpec(), responseSpecOK());
        return  given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/"+erknmId+"/docs");
    }
}
