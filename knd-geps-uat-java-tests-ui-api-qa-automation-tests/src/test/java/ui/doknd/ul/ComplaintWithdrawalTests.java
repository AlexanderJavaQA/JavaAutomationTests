package ui.doknd.ul;

import baseTest.BaseTestSelenide;
import doknd.pages.LoginPage;
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
    public void loginWithULAccount() {
        loginPage.login(
                config.doKndAppealFormUrlUat(),
                config.userLoginBespalov(),
                config.userPasswordBespalov(),
                LoginPage.AccountType.UL
        );
    }

    @Test
    @DisplayName("Процесс отзыва жалобы с использованием ПЭП")
    public void shouldWithdrawComplaintUsingPEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        complaintWithdrawalPage.processComplaintWithdrawal("PEP");
    }

    @Test
    @DisplayName("Процесс отзыва жалобы с использованием УКЭП")
    public void shouldWithdrawComplaintUsingUKEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        complaintWithdrawalPage.processComplaintWithdrawal("UKEP");
    }

    @Test
    @DisplayName("Процесс отзыва жалобы с использованием УНЭП")
    public void shouldWithdrawComplaintUsingUNEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        complaintWithdrawalPage.processComplaintWithdrawal("UNEP");
    }

    @Test
    @DisplayName("Процесс отзыва жалобы с использованием УКЭПГК")
    public void shouldWithdrawComplaintUsingUKEPGK() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        complaintWithdrawalPage.processComplaintWithdrawal("UKEPGK");
    }
}
