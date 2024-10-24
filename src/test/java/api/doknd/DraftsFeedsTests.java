package api.doknd;

import api.knd.BaseApiTests;
import apimodels.appeals.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static api.AppealsListService.getAppealsList;
import static api.DraftsFeedsService.getFeedsService;
import static api.DraftsListService.getDraftsList;
import static api.doknd.SearchForAppealTests.SORT_ORDER_ASC;
import static api.doknd.SearchForAppealTests.SORT_ORDER_DESC;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("SmokeAPI")
@DisplayName("Проверка отображения списка черновиков Ленты уведомлений")
public class DraftsFeedsTests extends BaseApiTests {


    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Юридических Лиц")
    public void shouldDisplayDraftsListForUL() {
        getFeedsService(accTValueUl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Физических Лиц")
    public void shouldDisplayDraftsListForFL() {
        getFeedsService(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков Ленты уведомлений для Индивидуальных Предпринимателей")
    public void shouldDisplayDraftsListForIP() {
        getFeedsService(accTValueIp);
    }
}
