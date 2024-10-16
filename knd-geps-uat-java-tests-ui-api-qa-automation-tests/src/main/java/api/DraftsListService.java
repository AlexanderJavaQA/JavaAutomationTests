package api;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class DraftsListService {

    public static void getDraftsList(String accTValue) {

        installSpec (requestSpec(), responseSpecOK());

        given()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("pageSize", DEFAULT_PAGE_SIZE)
                .queryParam("unread", false)
                .queryParam("isArchive", false)
                .queryParam("types", KND_APPEAL_DRAFT)
                .when()
                .get("/api/lk/v1/feeds");
    }
}
