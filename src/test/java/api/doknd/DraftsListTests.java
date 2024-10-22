package api.doknd;

import api.knd.BaseApiTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static api.DraftsListService.getDraftsList;

@Tag("SmokeAPI")
@DisplayName("Проверка отображения списка черновиков Ленты уведомлений")
public class DraftsListTests  extends BaseApiTests {

    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Юридических Лиц")
    public void shouldDisplayDraftsListForUL() {
        getDraftsList(accTValueUl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Физических Лиц")
    public void shouldDisplayDraftsListForFL() {
        getDraftsList(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Индивидуальных Предпринимателей")
    public void shouldDisplayDraftsListForIP() {
        getDraftsList(accTValueIp);
    }
}
