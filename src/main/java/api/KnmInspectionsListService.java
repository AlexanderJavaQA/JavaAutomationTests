package api;

import io.restassured.response.ValidatableResponse;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;

public class KnmInspectionsListService {

    public static ValidatableResponse getKnmInspectionsList(String accTValue, String erknmId ) {

        installSpec (requestSpec(), responseSpecOK());


        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/"+erknmId)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonschema/erknm_inspection_list_schema.json"));
    }
}
