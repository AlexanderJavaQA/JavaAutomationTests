package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.ErknmListResponseService.getErpInspectionsListResponseData;

@Tag("API")
@DisplayName("Проверка списка ЕРП карточек")
public class ErpInspectionsListResponseDataTests extends BaseApiTests {

    @Test
    @DisplayName("Проверка списка ЕРП карточек для ЮЛ")
    public void checkValidateErpInspectionsListUL() {
        getErpInspectionsListResponseData(accTValueUl);
    }

    @Test
    @DisplayName("Проверка списка ЕРП карточек для ИП")
    public void checkValidateErpInspectionsListIP() {
        getErpInspectionsListResponseData(accTValueIp);
    }
}

