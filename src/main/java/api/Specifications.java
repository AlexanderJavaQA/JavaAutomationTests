package api;

import appconfig.AppConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.fabric8.kubernetes.client.dsl.internal.OperationSupport.JSON;
import static org.aeonbits.owner.ConfigFactory.*;


public class Specifications {
    public static final String SORT_ORDER_DATE_ASC = "date_asc";
    public static final String SORT_ORDER_ASC = "asc";
    public static final String SORT_ORDER_DESC = "desc";
    public static final String TYPE_APPEAL = "appeal";
    public static final String TYPE_DRAFT = "draft";
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int PAGE_SIZE_LARGE = 100;
    public static final String TYPE_KND_APPEAL = "KND_APPEAL";
    public static final String KND_APPEAL_DRAFT = "KND_APPEAL_DRAFT";
    public static final int INITIAL_SITUATION = 1;
    public static final int DEFAULT_INSPECTION = 1;

    public static RequestSpecification  requestSpec() {
        AppConfig config = create(AppConfig.class);

        return new RequestSpecBuilder()
                .setBaseUri(config.doKndApiBaseUrlUat())
                .setContentType(JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOK() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
    public static void installSpec (RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
