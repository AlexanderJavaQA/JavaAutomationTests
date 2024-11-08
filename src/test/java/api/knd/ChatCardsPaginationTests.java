package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.ChatCardsPaginationService.getChatCardsPagination;

@DisplayName("Проверка поиска чатов обращений в контрольный (надзорный) орган")
public class ChatCardsPaginationTests extends BaseApiTests{

    @Test
    @DisplayName("Проверка поиска чатов обращений в контрольный (надзорный) орган для ЮЛ")
    public void shouldFindChatCardsForUL() {
        getChatCardsPagination(accTValueUl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка поиска чатов обращений в контрольный (надзорный) орган для ФЛ")
    public void shouldFindChatCardsForFL() {
        getChatCardsPagination(accTValueFl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка поиска чатов обращений в контрольный (надзорный) орган для ИП")
    public void shouldFindChatCardsForIP() {
        getChatCardsPagination(accTValueIp, "12040055125066009877");
    }

}
