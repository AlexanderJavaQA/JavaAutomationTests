package ui.doknd.fl;

import baseTest.BaseTestSelenide;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@DisplayName("Процесс отзыва жалобы для ФЛ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintWithdrawalTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ФЛ")
    public void loginWithFLAccount() {
        loginPage.login(
                config.doKndAppealFormUrlUat(),
                config.userLoginBespalov(),
                config.userPasswordBespalov(),
                LoginPage.AccountType.FL
        );
    }

    @Test
    @Order(2)
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