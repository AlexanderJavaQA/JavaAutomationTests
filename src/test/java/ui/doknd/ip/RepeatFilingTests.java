package ui.doknd.ip;

import baseTest.BaseTestSelenide;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;

@Tag("additionalActions")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка повторной подачи жалобы для ИП")
public class RepeatFilingTests extends BaseTestSelenide {

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
    @DisplayName("Проверка повторной подачи жалобы с типом подписи ПЭП")
    public void shouldRepeatFilingComplaintWithPEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevRequestWithStatusCodesUat("114", "103");
        repeatFilingPage.submitRepeatComplaint("PEP");
    }

    @Test
    @DisplayName("Проверка повторной подачи жалобы с типом подписи УКЭП")
    public void shouldRepeatFilingComplaintWithUKEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevRequestWithStatusCodesUat("114", "103");
        repeatFilingPage.submitRepeatComplaint("UKEP");
    }

    @Test
    @DisplayName("Проверка повторной подачи жалобы с типом подписи УНЭП")
    public void shouldRepeatFilingComplaintWithUNEP() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevRequestWithStatusCodesUat("114", "103");
        repeatFilingPage.submitRepeatComplaint("UNEP");
    }

    @Test
    @DisplayName("Проверка повторной подачи жалобы с типом подписи УКЭПГК")
    public void shouldRepeatFilingComplaintWithUKEPGK() {
        handleFilingComplaint.checkProcedureViolationID_1("PEP");
        smevRequest.processSmevRequestWithStatusCodesUat("114", "103");
        repeatFilingPage.submitRepeatComplaint("UKEPGK");
    }

}