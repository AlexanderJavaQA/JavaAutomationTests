package api;

import apimodels.erknm.ErknmInspectionsListPojo;

import static api.Specifications.*;
import static api.Specifications.DEFAULT_PAGE_NUMBER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ErknmInspectionSortService {
    public static ErknmInspectionsListPojo getErknmInspectionsSort(String accTValue, Integer countSize, String sortParameter, String kindType) {

        installSpec (requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageNumber", DEFAULT_PAGE_NUMBER)
                .queryParam("pageSize", countSize)
                .queryParam("kind", kindType)
                .queryParam("sort", sortParameter)
                .when()
                .get("/api/knd/v2/inspection/erknm/")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/erknm_inspection_list_schema.json"))
                .extract().response().as(ErknmInspectionsListPojo.class);
    }
}
