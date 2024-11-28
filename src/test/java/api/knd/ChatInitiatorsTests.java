package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.ChatInitiatorsService.getChatInitiators;

@DisplayName("Проверка запроса уникальных ФИО инициаторов чатов обращений в контрольный (надзорный) орган")
public class ChatInitiatorsTests extends BaseApiTests{

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов для Юридических Лиц")
    public void checkUniqueChatInitiatorsUL() {
        getChatInitiators(accTValueUl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов для Физических Лиц")
    public void checkUniqueChatInitiatorsFL() {
        getChatInitiators(accTValueFl, "12040055125066009877");
    }

    @Test
    @DisplayName("Проверка запроса уникальных ФИО инициаторов чатов для Индивидуальных Предпринимателей")
    public void checkUniqueChatInitiatorsIP() {
        getChatInitiators(accTValueIp, "12040055125066009877");
    }

}


