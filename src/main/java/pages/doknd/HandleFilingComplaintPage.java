package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandleFilingComplaintPage {
    private SelenideElement spanFileInfoPdf = $x(".//span[contains(@class, 'file-info') and text()='pdf']");
    AppConfig config = ConfigFactory.create(AppConfig.class);

    FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();
    SigningComplaintPage signingComplaintPage = new SigningComplaintPage();
    LoginPage loginPage = new LoginPage();

    public void openAndInitialize(String URL) {
        open(URL);
        loginPage.getAccTValue();
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
    }

    public void sendKnmInspectionNumberLookUp() {
        fillDetailsComplaintPage.fetchKnmInspectionsSorted(loginPage.getAccTValue())
                .lookupAndSubmitKNMInspectionNumber();
    }

    @SneakyThrows
    public void sendWarningKnmInspectionsList() {
        fillDetailsComplaintPage.fetchWarningKnmInspections(loginPage.getAccTValue())
                .lookupAndSubmitWarningKNMInspectionNumber();
    }

    public void sendPmInspectionNumberLookUp(String erknmId) {
        fillDetailsComplaintPage.fetchPmInspectionsSorted(loginPage.getAccTValue())
                .lookupAndSubmitPmInspectionNumber(erknmId);
    }

    public void handleTypeOfSupervision() {
        fillDetailsComplaintPage.scrollToSupervisionTypeDropdown()
                .clickSupervisionTypeDropdown()
                .clickSupervisionType();
    }

    public void handleTypeOfViolations(boolean isSelectViolations) {
        if (isSelectViolations) {
            try {
                fillDetailsComplaintPage.scrollAndClickViolationsDropdown()
                        .clickFirstViolationCheckbox ();
            } catch (ElementNotFound e) {
            }
        }
    }

    public void handlePlaceholderDescription(boolean isSelectPlaceholderDescription) {
        if (isSelectPlaceholderDescription) {
            fillDetailsComplaintPage.scrollAndSetApplicationDescription();

        } else {
            fillDetailsComplaintPage.scrollAndSetComplaintDescription();
        }
    }

    public void checkPdfText() throws IOException {
        spanFileInfoPdf.shouldBe(visible);
        File pdf = signingComplaintPage.getLinkPdfFile().download();
        PDF pdfReader = new PDF(pdf);
        assertTrue(pdfReader.text.contains("Беспалов"), "PDF does not contain the expected text 'Беспалов'");    }

    @SneakyThrows
    public void handleTypeOfSignature(String selectSignature) {
        fillDetailsComplaintPage.scrollToElectronicSignature()
                .scrollAndClickElectronicSignatureDropdown();

        switch (selectSignature) {
            case "UKEP":
                fillDetailsComplaintPage.clickSignatureUKEP();
                break;
            case "UKEPGK":
                fillDetailsComplaintPage.clickSignatureUKEPGK();
                break;
            case "UNEP":
                fillDetailsComplaintPage.clickSignatureUNEP();
                break;
        }
    }

    @SneakyThrows
    public void handleSelectRadioButton(boolean isSelectRadioButton) {
        if (isSelectRadioButton) {
            fillDetailsComplaintPage
                    .scrollAndClickPauseExecutionNo()
                    .scrollAndClickRestoreExtensionNo();
        }
    }

   /* @SneakyThrows
    public void handleSendInputAttachSignatureFile(String selectSignature) {

        if ("UKEP".equals(selectSignature)) {
            fillDetailsComplaintPage.clickButtonContinueFillDataPage();
            signingComplaintPage.scrollInputAttachSignatureFile()
                    .sendInputAttachSignatureFile()
                    .scrollButtonSend()
                    .clickButtonSend()
                    .clickLinkComplaintDetails()
                    .getOldWindow();
        }
        if ("UNEP".equals(selectSignature) || "UKEPGK".equals(selectSignature)) {
            fillDetailsComplaintPage.clickButtonContinueFillDataPage();

            signingComplaintPage.scrollButtonSend()
                    .clickButtonSend()
                    .clickLinkComplaintDetails()
                    .getOldWindow();
        }
        if ("PEP".equals(selectSignature)) {
            fillDetailsComplaintPage.clickButtonContinueFillDataPage();
            signingComplaintPage.clickButtonSignAndSend()
                    .clickLinkComplaintDetails()
                    .getOldWindow();
        }
    }*/

    @SneakyThrows
    public void handleSendInputAttachSignatureFile(String selectSignature) {
        fillDetailsComplaintPage.scrollAndClickContinueButton();

        switch (selectSignature) {
            case "UKEP":
                signingComplaintPage.scrollInputAttachSignatureFile()
                        .attachSignatureFileAndVerify()
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails()
                        .getOldWindow();
                break;
            case "UNEP":
            case "UKEPGK":
                signingComplaintPage.scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails()
                        .getOldWindow();
                break;
            case "PEP":
                signingComplaintPage.clickButtonSignAndSend()
                        .clickLinkComplaintDetails()
                        .getOldWindow();
                break;
            default:
                throw new IllegalArgumentException("Unknown signature type: " + selectSignature);
        }
    }


    public void handleFilingComplaint(String URL, String typeSignature, boolean isSelectViolations,
                                      boolean isSelectPlaceholderDescription, boolean isSelectRadioButton) {
        openAndInitialize(URL);
        sendKnmInspectionNumberLookUp();
        handleTypeOfViolations(isSelectViolations);
        handlePlaceholderDescription(isSelectPlaceholderDescription);
        handleTypeOfSignature(typeSignature);
        handleSelectRadioButton(isSelectRadioButton);
        handleSendInputAttachSignatureFile(typeSignature);
    }

    public void handleWarningFilingComplaint(String URL, String typeSignature, boolean isSelectViolations,
                                             boolean isSelectPlaceholderDescription, boolean isSelectRadioButton) {
        openAndInitialize(URL);
        sendWarningKnmInspectionsList();
        handleTypeOfViolations(isSelectViolations);
        handlePlaceholderDescription(isSelectPlaceholderDescription);
        handleTypeOfSignature(typeSignature);
        handleSelectRadioButton(isSelectRadioButton);
        handleSendInputAttachSignatureFile(typeSignature);
    }

    public void handleFilingComplaintPmInspections(String URL, String erknmId, String typeSignature,
                                                   boolean isSelectViolations, boolean isSelectPlaceholderDescription, boolean isSelectRadioButton) {
        openAndInitialize(URL);
        sendPmInspectionNumberLookUp(erknmId);
        handlePlaceholderDescription(isSelectPlaceholderDescription);
        handleTypeOfSignature(typeSignature);
        handleSelectRadioButton(isSelectRadioButton);
        handleSendInputAttachSignatureFile(typeSignature);
    }

    public void handleRiskCategories(String URL, String typeSignature) {
        openAndInitialize(URL);
        fillDetailsComplaintPage.handleRiskCategoriesDetails(typeSignature);
    }

    public void checkProcedureViolationID_1(String typeSignature) {
        handleFilingComplaint(config.uatCheckProcedureViolationId1(), typeSignature, true, false, true);
    }

    public void disagreeWithActViolationsID_2(String typeSignature) {
        handleFilingComplaint(config.uatDisagreeWithActViolationsId2(), typeSignature, true, false, true);
    }

    public void orderOfCheckAssignmentViolationID_3(String typeSignature) {
        handleFilingComplaint(config.uatOrderOfCheckAssignmentViolationId3(), typeSignature, true, false, true);
    }

    public void disagreeWithActionsOfOfficialID_4(String typeSignature) {
        handleFilingComplaint(config.uatDisagreeWithActionsOfOfficialId4(), typeSignature, true, false, true);
    }

    public void disagreeWithMeasuresID_5(String typeSignature) {
        handleFilingComplaint(config.uatDisagreeWithMeasuresId5(), typeSignature, true, false, true);
    }

    public void complaintForBreachOfMoratoriumID_10(String typeSignature) {
        handleFilingComplaint(config.uatComplaintForBreachOfMoratoriumId10(), typeSignature, false, true, false);
    }

    public void extensionOfPrescriptionExecutionPeriodID_11(String typeSignature) {
        handleFilingComplaint(config.uatExtensionOfPrescriptionExecutionPeriodId11(), typeSignature, false, true, false);
    }

    public void disagreeWithRiskCategoryAssignmentID_12(String typeSignature) {
        handleRiskCategories(config.uatDisagreeWithRiskCategoryAssignmentId12(), typeSignature);
    }

    public void disagreeWithControlOrganDecisionMobilizationID_13(String typeSignature) {
        handleFilingComplaint(config.uatDisagreeWithControlOrganDecisionMobilizationId13(), typeSignature, false, true, true);
    }

    public void disagreeObjectionCautionTestID_15(String typeSignature) {
        handleWarningFilingComplaint(config.uatObjectionCautionId15(), typeSignature, false, true, false);
    }

    public void disagreeWithProfVisitPrescriptionID_14(String typeSignature, String erknmId) {
        handleFilingComplaintPmInspections(config.uatDisagreeWithProfvisitPrescriptionId14(), erknmId, typeSignature, true, true, true);
    }
}
