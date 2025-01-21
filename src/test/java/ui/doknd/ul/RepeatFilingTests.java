package ui.doknd.ul;

import baseTest.BaseTestSelenide;

import listener.RetryListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка повторной подачи жалобы под ЮЛ")
@ExtendWith(RetryListener.class)
public class RepeatFilingTests extends BaseTestSelenide {

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
                .handleTypeOfSignature(typeSignature)
                .uploadDocumentIfHidden()
                .verifyFileUploaded()
                .scrollIntoViewAndClick(repeatFilingPage.getButtonContinue())
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}
