package api;

import apimodels.erknm.InspectionDocsTemplates;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;

public class InspectionDocsTemplatesService {

    public static InspectionDocsTemplates getInspectionDocsTemplates(String accTValue, String erknmId) {

        installSpec (requestSpec(), responseSpecOK());

        return  given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/"+erknmId+"/docs")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/inspection_docs_templates.json"))
                .extract().response().as(InspectionDocsTemplates.class);
    }}
