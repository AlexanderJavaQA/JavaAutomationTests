package ui.doknd.ul;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import listener.RetryListener;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("additionalActions")
@ExtendWith(RetryListener.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Процесс отзыва жалобы для ЮЛ")
public class ComplaintWithdrawalTests extends BaseTestSelenide {

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
    @DisplayName("Процесс отзыва жалобы с использованием ПЭП")
    public void shouldWithdrawComplaintUsingPEP(String typeSignature) {
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
                .setXmlRequest(orderId, "101")
                .clickButtonSubmit()
                .clickButtonOk();

        myComplaintsPage.openMyСomplaintsPage()
                .clickRegisteredComplaint();

        complaintProgressPage.clickWithdrawalButton();
        complaintWithdrawalPage.setWithdrawalReason();

        repeatFilingPage
                .handleTypeOfSignature(typeSignature)
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}
