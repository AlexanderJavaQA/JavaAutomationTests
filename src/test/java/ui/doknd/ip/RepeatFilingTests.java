package ui.doknd.ip;

import baseTest.BaseTestSelenide;
import listener.RetryListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(RetryListener.class)
@DisplayName("Проверка повторной подачи жалобы для ИП")
public class RepeatFilingTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ИП")
    public void loginAccount() {
        loginPage.openPage(config.appealsPage())
                .clickButtonEnter()
                .authenticateWithAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.IP);
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка повторной подачи жалобы")
    public void shouldRepeatFilingComplaintWithPEP(String typeSignature) {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        String orderId = handleFilingComplaint.getNewOrderId();

        elasticPage.openElasticInNewTabUat()
                .setOrderIdInQueryInput(orderId)
                .clickUpdateButton()
                .getValidKuberCorrelationId();

        String messageId = elasticPage.getSmevMessageIdByCorrelation();

        smevPage.openSmevStatusAppealRequest()
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

        repeatFilingPage.openNewTabForRepeatFilingPage()
                .clickStartOverInSavedDraftsModal()
                .setInspectionNumber(orderId)
                .clickHighlightedInspection(orderId)
                .setReasonForDisagreement()
                .uploadDocumentIfHidden()
                .verifyFileUploaded()
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}