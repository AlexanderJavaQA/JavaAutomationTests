package ui.doknd.fl;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка направления доп. информации в ведомство для ФЛ")
public class ProvideAdditionalTests extends BaseTestSelenide {

   @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ФЛ")
    public void loginAccount() {
        loginPage.openPage(config.appealsPage())
                .clickButtonEnter()
                .authenticateWithAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.FL);
    }

    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"PEP", "UKEP"})
    @DisplayName("Проверка направления доп. информации в ведомство")
    public void shouldProvideAdditionalInfoToAgencyWithPEP(String typeSignature) {
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
                .setXmlRequest(orderId, "114")
                .clickButtonSubmit()
                .clickButtonOk();


        myComplaintsPage.openMyСomplaintsPage()
                .clickRequestAdditionalInformation();
        complaintProgressPage.clickAdditionalDocumentsButton();
        submitAdditionalDocumentsPage.setValueSubmitDocuments();

        repeatFilingPage
                .uploadDocumentIfHidden()
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}
