package ui.doknd.ul;

import baseTest.BaseTestSelenide;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка подачи дополнительных документов для ЮЛ")
public class SubmitAdditionalDocumentsTests extends BaseTestSelenide {

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
    @DisplayName("Проверка подачи дополнительных документов с типом подписи ПЭП")
    public void shouldSubmitAdditionalDocumentsWithPEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("PEP", false);
    }

    @Test
    @DisplayName("Проверка подачи дополнительных документов с типом подписи УКЭП")
    public void shouldSubmitAdditionalDocumentsWithUKEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UKEP", false);
    }

    @Test
    @DisplayName("Проверка подачи дополнительных документов с типом подписи УНЭП")
    public void shouldSubmitAdditionalDocumentsWithUNEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UNEP", false);
    }

    @Test
    @DisplayName("Проверка подачи дополнительных документов с типом подписи УКЭПГК")
    public void shouldSubmitAdditionalDocumentsWithUKEPGK() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevComplaintWithdrawalRequestUat();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UKEPGK", false);
    }
}
