package api;

import apimodels.erknm.PMInspectionDetailsCard;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PMDetailsCardService {

    public static PMInspectionDetailsCard getPMInspectionDetailsCard(String accTValue, String erknmId, String jsonSchema ) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/"+erknmId)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(""+jsonSchema+""))
                .extract().response().as(PMInspectionDetailsCard.class);

    }
}
