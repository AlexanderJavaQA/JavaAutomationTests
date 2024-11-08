package api;

import io.restassured.response.Response;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ChatCardsPaginationService {

    public static Response getChatCardsPagination(String accTValue, String inspectionId) {
        installSpec(requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("inspectionId", inspectionId)
                .queryParam("topic", "topic")
                .queryParam("initiator", "initiator")
                .queryParam("status", "status")
                .queryParam("pageNumber", "pageNumber")
                .queryParam("pageSize", "pageSize")
                .get("/api/knd/v1/chats");
    }
}
