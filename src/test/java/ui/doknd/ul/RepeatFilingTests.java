package ui.doknd.ul;

import baseTest.BaseTestSelenide;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;
@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка повторной подачи жалобы под ЮЛ")
public class RepeatFilingTests extends BaseTestSelenide {

   @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ЮЛ")
    public void loginWithULAccount() {
        loginPage.login(
                config.doKndAppealFormUrlUat(),
                config.userLoginBespalov(),
                config.userPasswordBespalov(),
                LoginPage.AccountType.UL
        );
    }
    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка повторной подачи жалобы с типом подписи")
    public void shouldRepeatFilingComplaintWithPEP(String typeSignature) {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        String orderId = handleFilingComplaint.getNewOrderId();

        elasticPage.openElasticInNewTabUat()
                .setOrderIdInQueryInput(orderId)
                .clickUpdateButton()
                .getKuberCorrelationId();
        elasticPage.getSmevMessageIdByCorrelation();

        String messageId = elasticPage.getSmevMessageIdByCorrelation();

        smevPage.openSmevRequestBroadcastUat()
                .clearMessageID()
                .setMessageID(messageId)
                .clearXmlRequest()
                .setXmlRequest(orderId, "101")
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(orderId, "117")
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(orderId, "103")
                .clickButtonSubmit()
                .clickButtonOk();

        myComplaintsPage.openMyСomplaintsPage()
                .clickRegisteredComplaint();

        repeatFilingPage.setInspectionNumber()
                .setReasonForDisagreement()
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }

}
