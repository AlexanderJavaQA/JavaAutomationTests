package api;

import apimodels.erknm.ControlTypesResponseItem;
import io.restassured.common.mapper.TypeRef;

import java.util.List;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TypesOfContolService {

    public static List<ControlTypesResponseItem> getTypesOfContolList(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        return  given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .get("/api/knd/v2/inspection/erknm/control-types")
                .then()
              //  .body(matchesJsonSchemaInClasspath("jsonschema/control_types_schema.json"))
                .extract().as(new TypeRef<List<ControlTypesResponseItem>>() {});
    }
}


