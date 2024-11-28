package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.ChatStatusesService.getChatStatuses;

@DisplayName("Проверка запроса уникальных статусов чатов обращений в контрольный (надзорный) орган")
public class ChatStatusesTests extends BaseApiTests {

    @Test
    @DisplayName("Проверка запроса уникальных статусов чатов обращений в контрольный (надзорный) орган для ЮЛ")
    public void checkFindChatCardsForUL() {
        getChatStatuses(accTValueUl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных статусов чатов обращений в контрольный (надзорный) орган для ФЛ")
    public void checkFindChatCardsForFL() {
        getChatStatuses(accTValueFl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных статусов чатов обращений в контрольный (надзорный) орган для ИП")
    public void checkFindChatCardsForIP() {
        getChatStatuses(accTValueIp, "12040055125066009877");
    }
}
