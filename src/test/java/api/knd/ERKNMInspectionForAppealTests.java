package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static api.ERKNMInspectionForAppealService.getERKNMInspectionForAppeal;

@Tag("API")
@DisplayName("Получение ЕРКНМ проверок доступных к обжалованию")
public class ERKNMInspectionForAppealTests extends BaseApiTests {

    @Test
    @DisplayName("Получение ЕРКНМ проверок для обжалования для Юридических Лиц")
    public void checkERKNMForAppealUL() {
        getERKNMInspectionForAppeal(accTValueUl);
    }

    @Test
    @DisplayName("Получение ЕРКНМ проверок для обжалования для Физических Лиц")
    public void checkERKNMForAppealFL() {
        getERKNMInspectionForAppeal(accTValueFl);
    }

    @Test
    @DisplayName("Получение ЕРКНМ проверок для обжалования для Индивидуальных Предпринимателей")
    public void checkERKNMForAppealIP() {
        getERKNMInspectionForAppeal(accTValueIp);
    }


}

