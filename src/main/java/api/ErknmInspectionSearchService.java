package api;

import apimodels.erknm.ErknmInspectionsList;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ErknmInspectionSearchService {

    public static ErknmInspectionsList getErknmInspectionSearch(String accTValue, Integer pageSize, String sortParameter, String kindType, String query) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", pageSize)
                .queryParam("kind", kindType)
                .queryParam("sort", sortParameter)
                .queryParam("query", query)
                .when()
                .get("/api/knd/v2/inspection/erknm/")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/erknm_inspection_list_schema.json"))
                .extract().response().as(ErknmInspectionsList.class);
    }
}
