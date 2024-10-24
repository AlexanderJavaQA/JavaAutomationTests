package api.doknd;

import api.knd.BaseApiTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static api.SituationsFilterService.getSituationsFilter;
import static api.Specifications.TYPE_APPEAL;
import static api.Specifications.TYPE_DRAFT;

@DisplayName("Проверка фильтрации по жизненной ситуации на странице Мои жалобы или Мои черновики")
public class SituationsFilterTests extends BaseApiTests {

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои жалобы для Юридических Лиц")
    public void shouldFilterSituationsAppealForUL() {
        getSituationsFilter(accTValueUl, TYPE_APPEAL);
    }

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои черновики для Юридических Лиц")
    public void shouldFilterSituationsDraftForUL() {
        getSituationsFilter(accTValueUl, TYPE_DRAFT);
    }

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои жалобы для Физических Лиц")
    public void shouldFilterSituationsAppealForFL() {
        getSituationsFilter(accTValueFl, TYPE_APPEAL);
    }

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои черновики для Физических Лиц")
    public void shouldFilterSituationsDraftForFL() {
        getSituationsFilter(accTValueFl, TYPE_DRAFT);
    }

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои жалобы для Индивидуальных Предпринимателей")
    public void shouldFilterSituationsAppealForIP() {
        getSituationsFilter(accTValueIp, TYPE_APPEAL);
    }

    @Test
    @DisplayName("Фильтрация по жизненной ситуации на странице Мои черновики для Индивидуальных Предпринимателей")
    public void shouldFilterSituationsDraftForIP() {
        getSituationsFilter(accTValueIp, TYPE_DRAFT);
    }
}


