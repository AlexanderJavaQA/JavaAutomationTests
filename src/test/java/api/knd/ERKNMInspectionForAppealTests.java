package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static api.ERKNMInspectionForAppealService.getERKNMInspectionForAppeal;

@Tag("API")
@DisplayName("Получение ЕРКНМ проверок доступных к обжалованию")
public class ERKNMInspectionForAppealTests extends BaseApiTests {

    @Test
    @DisplayName("Получение ЕРКНМ проверок доступных к обжалованию для ЮЛ")
    public void shouldRetrieveERKNMInspectionForAppealUL() {
        getERKNMInspectionForAppeal(accTValueUl);
    }

    @Test
    @DisplayName("Получение ЕРКНМ проверок доступных к обжалованию для ФЛ")
    public void shouldRetrieveERKNMInspectionForAppealFL() {
        getERKNMInspectionForAppeal(accTValueFl);
    }

    @Test
    @DisplayName("Получение ЕРКНМ проверок доступных к обжалованию для ИП")
    public void shouldRetrieveERKNMInspectionForAppealIP() {
        getERKNMInspectionForAppeal(accTValueIp);
    }

}

