package api;

import apimodels.erknm.ErknmInspectionsList;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class WarningInspectionsService {

    public static ErknmInspectionsList getWarningInspectionsService(String accTValue, Integer countSize, String sortParameter, String statusId ) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", 1)
                .queryParam("countSize", countSize)
                .queryParam("sort", sortParameter)
                .queryParam("statusId", statusId)
                .when()
                .get("/api/knd/v2/inspection/erknm/")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/erknm_inspection_list_schema.json"))
                .extract().response().as(ErknmInspectionsList.class);
    }
}
