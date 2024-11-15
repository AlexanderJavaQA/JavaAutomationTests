package ui.knd.erp;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.*;

@DisplayName("Проверка отправки ЕРП шаблонов")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ErpTemplateTests extends BaseTestSelenide {

    @Order(1)
    public void submittedERPTemplateForEntity( String inn, String ogrn, String erpId) {
        smevPage
                .enableERPSmevaFlag()
                .createERPStubTemplateDev2(inn, ogrn, erpId)
                .openSmevRequestBroadcastDev2()
                .createERPBroadcastRequest(erpId)
                .clickButtonSubmit()
                .checkResultModalIsVisible()
                .disableERPSmevaFlag();

        String messageID = smevPage.getMessageIDFromModal();
        }

    @Test
    @DisplayName("Проверка отправки ЕРП шаблонов")
    public void verifySingleErpTemplateSubmission() {
        submittedERPTemplateForEntity( "1201004920", "1020200715281","125555222220004");
    }
}
