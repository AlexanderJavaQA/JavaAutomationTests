package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.ChatsTopicListService.getChatsTopicList;

@DisplayName("Проверка запроса уникальных тем чатов обращений в контрольный (надзорный) орган")
public class ChatsTopicListTests extends BaseApiTests{

    @Test
    @DisplayName("Проверка запроса уникальных тем чатов обращений в контрольный (надзорный) орган для ЮЛ")
    public void shouldFindChatCardsForUL() {
        getChatsTopicList(accTValueUl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных тем чатов обращений в контрольный (надзорный) орган для ФЛ")
    public void shouldFindChatCardsForFL() {
        getChatsTopicList(accTValueFl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных тем чатов обращений в контрольный (надзорный) орган для ИП")
    public void shouldFindChatCardsForIP() {
        getChatsTopicList(accTValueIp, "12040055125066009877");
    }
}
