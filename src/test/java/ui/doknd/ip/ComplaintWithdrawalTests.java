package ui.doknd.ip;

import baseTest.BaseTestSelenide;
import listener.RetryListener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@ExtendWith(RetryListener.class)
@Tag("additionalActions")
@DisplayName("Процесс отзыва жалобы для ИП")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintWithdrawalTests extends BaseTestSelenide {


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

