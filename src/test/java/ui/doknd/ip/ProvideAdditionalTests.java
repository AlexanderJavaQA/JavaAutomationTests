package ui.doknd.ip;

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
@DisplayName("Проверка направления доп. информации в ведомство для ИП")
public class ProvideAdditionalTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ИП")
    public void loginAccount() {
        loginPage.openPage(config.appealsPage())
                .clickButtonEnter()
                .authAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.IP);
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка направления доп. информации в ведомство")
    public void shouldProvideAdditionalInfoToAgencyWithPEP(String typeSignature) {
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
                .setXmlRequest(orderId, "101")
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(orderId, "114")
                .clickButtonSubmit()
                .clickButtonOk();


        myComplaintsPage.openMyСomplaintsPageDev2()
                .clickRequestAdditionalInformation();
        complaintProgressPage.clickAdditionalInfoButton();
        submitAdditionalDocumentsPage.setValueSubmitDocuments();

        repeatFilingPage
                .uploadDocumentIfHidden()
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}
