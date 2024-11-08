package ui.doknd.ip;

import baseTest.BaseTestSelenide;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка подачи дополнительных документов для ИП")
public class SubmitAdditionalDocumentsTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ИП")
    public void loginWithIPAccount() {
        loginPage.login(
                config.doKndAppealFormUrlUat(),
                config.userLoginBespalov(),
                config.userPasswordBespalov(),
                LoginPage.AccountType.IP
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