package ui.doknd.ul;

import baseTest.BaseTestSelenide;
import listener.RetryListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@ExtendWith(RetryListener.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка подачи дополнительных документов для ЮЛ")
public class SubmitAdditionalDocumentsTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ЮЛ")
    public void loginAccount() {
        loginPage.openPage(config.appealsPage())
                .clickButtonEnter()
                .authAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.UL);
    }


    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи дополнительных документов с типом подписи ПЭП")
    public void shouldSubmitAdditionalDocumentsWithPEP(String typeSignature) {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        String orderId = handleFilingComplaint.getNewOrderId();

        elasticPage.openElasticInNewTabDev2()
                .setOrderIdInQueryInput(orderId)
                .clickUpdateButton()
                .getValidKuberCorrelationId();

        String messageId = elasticPage.getSmevMessageIdByCorrelationDev2();

        smevPage.openSmevStatusAppealRequestDev2()
                .clearMessageID()
                .setMessageID(messageId)
                .clearXmlRequest()
                .setXmlRequest(typeSignature, "101")
                .clickButtonSubmit()
                .clickButtonOk();

        myComplaintsPage.openMyСomplaintsPageDev2()
                .clickRegisteredComplaint();
        complaintProgressPage.clickAdditionalDocumentsButton();
        submitAdditionalDocumentsPage.setValueSubmitDocuments();
        repeatFilingPage
                .uploadDocumentIfHidden()
                .verifyFileUploaded()
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}
