package pages.doknd;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SubmitAdditionalDocumentsPage {

    ComplaintDetailsPage complaintDetailsPage = new ComplaintDetailsPage();
    RepeatFilingPage repeatFilingPage = new RepeatFilingPage();
    private SelenideElement fieldSubmitDocumentsWithComments = $x("//textarea[@id='comments-witch-docs']");
    MyСomplaintsPage myСomplaintsPage = new MyСomplaintsPage();


    public SubmitAdditionalDocumentsPage setValueSubmitDocumentsWithComments() {
        fieldSubmitDocumentsWithComments.shouldBe(visible).setValue("Тестовые данные");
        return this;
    }

    public void processAdditionalDocumentsSubmission(String typeSignature, boolean isProvideAdditionalInfoToAgency) {

        if (isProvideAdditionalInfoToAgency) {
            myСomplaintsPage.openMyСomplaintsPage()
                    .clickRequestAdditionalInformationText();
            complaintDetailsPage.clickSubmitAdditionalDocumentsButton();
        } else {
            myСomplaintsPage.openMyСomplaintsPage()
                    .clickRegisteredComplaintText();
            complaintDetailsPage.clickSubmitAdditionalDocumentsButton();
        }
        setValueSubmitDocumentsWithComments();

        repeatFilingPage
                .uploadDocumentIfHidden()
                .handleTypeOfSignature(typeSignature)
                .scrollAndClickButtonContinue();



        repeatFilingPage.handleSendInputAttachSignatureFile(typeSignature);
    }
}
