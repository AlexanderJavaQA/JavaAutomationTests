package api;

import io.restassured.response.Response;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;

public class ChatInitiatorsService {
    public static Response getChatInitiators(String accTValue, String inspectionId) {
        installSpec(requestSpec(), responseSpecOK());

        return given()
                .when()
                .header("Cookie", "acc_t=" + accTValue)
                .queryParam("inspectionId", inspectionId)
                .get("/api/knd/v1/chats/initiators");
    }
}