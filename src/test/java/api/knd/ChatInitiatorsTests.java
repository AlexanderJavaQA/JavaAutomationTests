package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.ChatInitiatorsService.getChatInitiators;

@DisplayName("Проверка запроса уникальных ФИО инициаторов чатов обращений в контрольный (надзорный) орган")
public class ChatInitiatorsTests extends BaseApiTests{

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов обращений в контрольный (надзорный) орган для ЮЛ")
    public void shouldRequestUniqueChatInitiatorsForUL() {
        getChatInitiators(accTValueUl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов обращений в контрольный (надзорный) орган для ФЛ")
    public void shouldRequestUniqueChatInitiatorsForFL() {
        getChatInitiators(accTValueFl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов обращений в контрольный (надзорный) орган для ИП")
    public void shouldRequestUniqueChatInitiatorsForIP() {
        getChatInitiators(accTValueIp, "12040055125066009877");
    }

}


