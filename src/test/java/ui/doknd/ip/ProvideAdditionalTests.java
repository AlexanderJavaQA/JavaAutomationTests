package ui.doknd.ip;

import baseTest.BaseTestSelenide;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка направления доп. информации в ведомство для ИП")
public class ProvideAdditionalTests extends BaseTestSelenide {

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
    @DisplayName("Проверка направления доп. информации в ведомство с ПЭП")
    public void shouldProvideAdditionalInfoToAgencyWithPEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processAdditionalInfoSmevRequest();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("PEP", true);
    }

    @Test
    @DisplayName("Проверка направления доп. информации в ведомство с УКЭП")
    public void shouldProvideAdditionalInfoToAgencyWithUKEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processAdditionalInfoSmevRequest();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UKEP", true);
    }

    @Test
    @DisplayName("Проверка направления доп. информации в ведомство с УНЭП")
    public void shouldProvideAdditionalInfoToAgencyWithUNEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processAdditionalInfoSmevRequest();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UNEP", true);
    }

    @Test
    @DisplayName("Проверка направления доп. информации в ведомство с УКЭПГК")
    public void shouldProvideAdditionalInfoToAgencyWithUKEPGK() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processAdditionalInfoSmevRequest();
        submitAdditionalDocumentsPage.processAdditionalDocumentsSubmission("UKEPGK", true);
    }
}
