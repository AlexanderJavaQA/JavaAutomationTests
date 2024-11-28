package api;

import apimodels.businessActivity.BusinessNotificationsItems;

import java.util.List;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class BusinessNotificationsService {

    public static List<BusinessNotificationsItems> getObjectsOfBusinessNotifications(String accTValue, String id) {

        installSpec (requestSpec(), responseSpecOK());

        return List.of(given()
                .header("Cookie", "acc_t=" + accTValue)
                .when()
                .get("/api/knd/v1/orgactivity/objects/" + id + "/grouped-notifications/activity")
                .then()
                .extract().as(BusinessNotificationsItems[].class));
    }
}
