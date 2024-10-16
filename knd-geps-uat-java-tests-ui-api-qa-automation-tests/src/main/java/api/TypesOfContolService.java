package api;

import apimodels.controlObjects.TypesOfContolPojo;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TypesOfContolService {

    public static List<TypesOfContolPojo> getTypesOfContolList(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        return  given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/control-types")
                .then()
                .body(matchesJsonSchemaInClasspath("jsonschema/control_types_schema.json"))
                .extract().as(new TypeRef<List<TypesOfContolPojo>>() {});
    }
}


