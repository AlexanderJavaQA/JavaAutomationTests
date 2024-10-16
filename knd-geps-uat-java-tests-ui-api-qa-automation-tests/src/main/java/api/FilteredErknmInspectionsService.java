package api;

import apimodels.erknm.ErknmFiltersPojo;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;

public class FilteredErknmInspectionsService {

    public static ErknmFiltersPojo getFilteredErknmInspections(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/filters")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/erknm_filters_schema.json"))
                .extract().response().as(ErknmFiltersPojo.class);
    }
}

