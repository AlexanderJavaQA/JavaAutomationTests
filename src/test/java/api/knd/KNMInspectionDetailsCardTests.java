package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.KNMDetailsCardService.getKNMInspectionDetailsCard;


@Tag("SmokeAPITest")
@DisplayName("Проверка детальной карточки КНМ")
public class KNMInspectionDetailsCardTests extends BaseApiTests {
    @Test
    @DisplayName("Проверка детальной карточки КНМ для ЮЛ")
    public void checkVerifyKNMInspectionDetailsCardUL() {
        getKNMInspectionDetailsCard(accTValueUl, "02210861000100043073500", "jsonschema/knm_inspection_schema_ul.json");
    }

    @Test
    @DisplayName("Проверка детальной карточки КНМ для ИП")
    public void checkVerifyKNMInspectionDetailsCardIP() {
        getKNMInspectionDetailsCard(accTValueIp, "0221086100010004307350", "jsonschema/knm_inspection_schema_ip.json");
    }

    @Test
    @DisplayName("Проверка детальной карточки КНМ для ФЛ")
    public void checkVerifyKNMInspectionDetailsCardFL() {
        getKNMInspectionDetailsCard(accTValueFl, "0221086100010004307352", "jsonschema/knm_inspection_schema_fl.json");
    }


}
