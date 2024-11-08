package ui.knd.erp;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.*;

import java.util.Random;

@DisplayName("Проверка отправки ЕРП шаблонов")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ErpTemplateTests extends BaseTestSelenide {

    @Order(1)
    public void submittedERPTemplateForEntity( String inn, String ogrn, String erpId) {
        smevPage
                .enableSmevaFlag()
                .createERPStubTemplateDev2(inn, ogrn, erpId)
                .openSmevRequestBroadcastDev2()
                .createERPBroadcastRequest(erpId)
                .clickButtonSubmit()
                .verifyModalBodyIsVisible();

        String messageID = smevPage.getMessageIDFromModal();
        /*smevPage
                .clickButtonOk()
                .openAndVerifyDeliveryConfirmationInNewTab(messageID, smevPage.getSmevUpdateErknmPassedResponse());*/
    }

    @Test
    @DisplayName("Проверка отправки ЕРКНМ шаблонов")
    public void verifySingleErknmTemplateSubmission() {
        submittedERPTemplateForEntity( "1201004920", "1020200715281","125555222220004");
    }
}
