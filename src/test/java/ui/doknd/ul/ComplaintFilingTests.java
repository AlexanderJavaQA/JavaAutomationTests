
package ui.doknd.ul;

import baseTest.BaseTestSelenide;
import listener.RetryListener;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.doknd.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.TestMethodOrder;

@Tag("FilingComplaint")
@ExtendWith(RetryListener.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка подачи жалобы для ЮЛ")
class ComplaintFilingTests extends BaseTestSelenide {

 /*   @Test
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
    @DisplayName("Проверка подачи жалобы на нарушение процедуры проведения проверки ID_1")
    public void shouldSubmitComplaintForProcedureViolationID_1(String typeSignature) {
        handleFilingComplaint.checkProcedureViolationID_1(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с актом проверки ID_2")
    public void shouldSubmitComplaintForDisagreementWithActID_2(String typeSignature) {
        handleFilingComplaint.disagreeWithActViolationsID_2(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на нарушение порядка назначения проверки ID_3")
    public void shouldSubmitComplaintForInspectionAssignmentProcedureViolationID_3(String typeSignature) {
        handleFilingComplaint.orderOfCheckAssignmentViolationID_3(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с действиями должностного лица ID_4")
    public void shouldSubmitComplaintForDisagreementWithOfficialActionsID_4(String typeSignature) {
        handleFilingComplaint.disagreeWithActionsOfOfficialID_4(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с мерами ID_5")
    public void shouldSubmitComplaintForDisagreementWithMeasuresID_5(String typeSignature) {
        handleFilingComplaint.disagreeWithMeasuresID_5(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на нарушение моратория на проверки ID_10")
    public void shouldSubmitComplaintForBreachOfMoratoriumID_10(String typeSignature) {
        handleFilingComplaint.complaintForBreachOfMoratoriumID_10(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на продление срока исполнения предписания ID_11")
    public void shouldSubmitComplaintForExecutionPeriodID_11(String typeSignature) {
        handleFilingComplaint.extensionOfPrescriptionExecutionPeriodID_11(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с категорией риска ID_12")
    public void shouldSubmitComplaintForDisagreementWithRiskID_12(String typeSignature) {
        handleFilingComplaint.disagreeWithRiskCategoryAssignmentID_12(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с решением в условиях мобилизации ID_13")
    public void shouldSubmitComplaintForDisagreementMobilizationID_13(String typeSignature) {
        handleFilingComplaint.disagreeWithControlOrganDecisionMobilizationID_13(typeSignature);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на несогласие с предписанием по профвизиту ID_14")
    public void shouldSubmitComplaintForDisagreementPrescriptionID_14(String typeSignature) {
        handleFilingComplaint.disagreeWithProfVisitPrescriptionID_14(typeSignature, "77230957700003804170");
    }

    @ParameterizedTest
    @ValueSource(strings = {"PEP", "UKEP", "UNEP", "UKEPGK"})
    @DisplayName("Проверка подачи жалобы на возражение на предостережение ID_15")
    public void shouldSubmitComplaintForObjectionToCautionID_15(String typeSignature) {
        handleFilingComplaint.disagreeObjectionCautionTestID_15(typeSignature);
    }*/
}


