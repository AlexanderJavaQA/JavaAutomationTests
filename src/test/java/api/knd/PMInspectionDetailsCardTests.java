package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.PMDetailsCardService.getPMInspectionDetailsCard;


@Tag("SmokeAPITest")
@DisplayName("Проверка детальной карточки ПМ")
public class PMInspectionDetailsCardTests extends BaseApiTests {

    @Test
    @DisplayName("Проверка детальной карточки ПМ для ИП")
    public void shouldVerifyPMDetailsCardForIP() {
        getPMInspectionDetailsCard(accTValueIp, "772309577000038041701", "jsonschema/pm_inspection_schema_ip.json");
    }

    @Test
    @DisplayName("Проверка детальной карточки ПМ для ЮЛ")
    public void shouldVerifyPMDetailsCardForUL() {
        getPMInspectionDetailsCard(accTValueUl, "77230957700003804170", "jsonschema/pm_inspection_schema_ul.json");
    }

    @Test
    @DisplayName("Проверка детальной карточки ПМ для ФЛ")
    public void shouldVerifyPMDetailsCardForFL() {
        getPMInspectionDetailsCard(accTValueFl, "7723095770000380417012", "jsonschema/pm_inspection_schema_fl.json");
    }

}
