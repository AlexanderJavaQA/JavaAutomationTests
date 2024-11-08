package pages.doknd;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class ComplaintWithdrawalPage {
    private SelenideElement fieldReasonForComplaintWithdrawal = $x("//textarea[@id='reason-cancel']");
    private SelenideElement attachedDocumentsText = $x("//*[contains(text(), 'Прикреплённые документы')]");
    private SelenideElement pdfTextElement = $x("//*[contains(text(), 'pdf')]");
    private SelenideElement downloadPdfLink = $x("//a[contains(@href, '/download?asArchive=false') and @class='small-text file-link']");
    private SelenideElement fileSizeTextElement = $x("//*[contains(text(), '25 Кб')]");
    private SelenideElement pdfFileInfoSpan = $x(".//span[contains(@class, 'file-info') and text()='pdf']");

    ComplaintDetailsPage complaintDetailsPage = new ComplaintDetailsPage();
    RepeatFilingPage repeatFilingPage = new RepeatFilingPage();
    MyСomplaintsPage myComplaintsPage = new MyСomplaintsPage();


    public ComplaintWithdrawalPage enterReasonForWithdrawal() {
        fieldReasonForComplaintWithdrawal.shouldBe(visible).setValue("Тестовые данные");
        return this;
    }

    public void processComplaintWithdrawal(String typeSignature) {
        myComplaintsPage.openMyСomplaintsPage()
                .clickRegisteredComplaintText();

        complaintDetailsPage
                .clickComplaintWithdrawalButton()
                .enterReasonForWithdrawal();

        repeatFilingPage.handleTypeOfSignature(typeSignature)
                .scrollAndClickButtonContinue();

        repeatFilingPage.handleSendInputAttachSignatureFile(typeSignature);
    }
}
