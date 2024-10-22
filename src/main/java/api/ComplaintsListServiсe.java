package api;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ComplaintsListServiсe {
    public static void getComplaintsList(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageSize", 100)
                .queryParam("unread", false)
                .queryParam("isArchive", false)
                .queryParam("types", TYPE_KND_APPEAL)
                .when()
                .get("/api/lk/v1/feeds");
    }
}
