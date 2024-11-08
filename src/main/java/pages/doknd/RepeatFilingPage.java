package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WindowType;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class RepeatFilingPage {

    private SelenideElement inputAttachDocuments = $x("//input[@class='input']");
    private SelenideElement fieldReasonDisagreement = $x("//input[@aria-labelledby='Введите минимум 6 символов']");
    private SelenideElement buttonContinue = $x("//span[contains(text(),'Продолжить')]");
    private SelenideElement fieldReasonForDisagreementTextarea = $x("//textarea[@id='repeat-form-why-not-decision']");
    private SelenideElement textFileName = $x("//span[@class='filename text-plain black']");
    private SelenideElement dropdownTypeOfElectronicSignature = $x("//a[@class='dropdown-arrow']");
    private SelenideElement modalWindowSavedDrafts = $x("//span[contains(text(),'Начать заново')]");
    private SelenideElement signatureUKEP = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись')]");
    private SelenideElement signatureUKEPGK = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись Го')]");
    private SelenideElement signatureUNEP = $x("//span[contains(text(),'Усиленная неквалифицированная электронная подпись')]");
    private SelenideElement attachedDocuments = $x("//*[contains(text(), 'Прикрепленные документы')]");
    private ElementsCollection spanFileInfoPdf = $$x(".//span[contains(@class, 'file-info') and text()='pdf']");
    private SelenideElement orderID = $x("//h3[contains(text(),'Заявление №')]");
    private String newOrderId;
    File file = new File(("C:\\Users\\aleksandr.kurbanov\\Desktop\\Тестовые данные.pdf"));

    AppConfig config = ConfigFactory.create(AppConfig.class);
    MyСomplaintsPage myСomplaintsPage = new MyСomplaintsPage();
    FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();

    public RepeatFilingPage checkSpanFileInfoPdf() {
        spanFileInfoPdf.forEach(element -> element.shouldHave(text("pdf")));
        spanFileInfoPdf.forEach(System.out::println);
        return this;
    }

    public RepeatFilingPage checkAttachedDocuments() {
        attachedDocuments.shouldHave(text("Прикрепленные документы"));
        System.out.printf(attachedDocuments.getText());
        return this;
    }

    public RepeatFilingPage openRepeatFilingPage() {
        switchTo().newWindow(WindowType.TAB);
        open(config.doKndRepeatedAppealFormUrlUat());
        return this;
    }

    @SneakyThrows
    public RepeatFilingPage clickModalWindowSavedDrafts() {
        try {
            modalWindowSavedDrafts.shouldBe(enabled, Duration.ofSeconds(5)).click();
        } catch (ElementNotFound e) {
            System.err.println("Ошибка при попытке кликнуть на 'Начать заново': " + e.getMessage());
        }
        return this;
    }

    @SneakyThrows
    public RepeatFilingPage scrollBannerInformerFillDetails() {
        executeJavaScript("arguments[0].scrollIntoView(true);", fillDetailsComplaintPage.getBannerInformerDetails());
        Thread.sleep(400);
        return this;
    }

    public RepeatFilingPage enterReasonForDisagreement() {
        fieldReasonForDisagreementTextarea
                .shouldBe(visible)
                .setValue("Тестовые данные");
        return this;
    }

    public SelenideElement getHighlightedSpanElement(String orderId) {
        return $x("//span[@class='highlighted' and text()='" + orderId + "']");
    }

    @SneakyThrows
    public RepeatFilingPage scrollAndClickButtonContinue() {
        executeJavaScript("arguments[0].scrollIntoView(true);", buttonContinue); // Скролл до элемента с помощью javaScript
        Thread.sleep(300);
        buttonContinue.click();
        return this;
    }


    public RepeatFilingPage uploadDocumentIfHidden() {
        inputAttachDocuments
                .shouldBe(hidden)
                .uploadFile(file);
        return this;
    }

    public RepeatFilingPage verifyFileNameIsDisplayed() {
        textFileName.shouldHave(text("Тестовые данные.pdf"));
        return this;
    }

    @SneakyThrows
    public RepeatFilingPage scrollSignature() {
        fillDetailsComplaintPage.scrollToElement(dropdownTypeOfElectronicSignature);
        return this;
    }

    public RepeatFilingPage clickSignatureUKEP() {
        signatureUKEP.click();
        return this;
    }

    public RepeatFilingPage clickSignatureUKEPGK() {
        signatureUKEPGK.click();
        return this;
    }

    public RepeatFilingPage clickSignatureUNEP() {
        signatureUNEP.click();
        return this;
    }

    public RepeatFilingPage clickDropdownTypeOfElectronicSignature() {
        dropdownTypeOfElectronicSignature.shouldBe(enabled).click();
        return this;
    }

    public RepeatFilingPage handleTypeOfSignature(String selectSignature) {
        scrollSignature();
        if (selectSignature == null) {
            throw new IllegalArgumentException("selectSignature не должно быть null");
        }

        switch (selectSignature) {
            case "UKEP":
                clickDropdownTypeOfElectronicSignature()
                        .clickSignatureUKEP();
                break;

            case "UKEPGK":
                clickDropdownTypeOfElectronicSignature()
                        .clickSignatureUKEPGK();
                break;

            case "UNEP":
                clickDropdownTypeOfElectronicSignature()
                        .clickSignatureUNEP();
                break;
        }
        return this;
    }

    public RepeatFilingPage handleSendInputAttachSignatureFile(String selectSignature) {
        SigningComplaintPage signingComplaintPage = new SigningComplaintPage();
        if (selectSignature == null) {
            throw new IllegalArgumentException("selectSignature не должно быть null");
        }

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
                signingComplaintPage
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails()
                        .getOldWindow();
                break;

            case "PEP":
                signingComplaintPage
                        .clickButtonSignAndSend()
                        .clickLinkComplaintDetails()
                        .getOldWindow();
                break;

            default:
                throw new IllegalArgumentException("Неизвестный тип подписи: " + selectSignature);
        }
        return this;
    }

    public RepeatFilingPage getOrderIdRepeatFilling() {
        String orderId = orderID.text();
        newOrderId = orderId.substring(12);
        return this;
    }

    public RepeatFilingPage enterInspectionNumberAndSelect() {
        fieldReasonDisagreement.shouldBe(visible).setValue(newOrderId);
        getHighlightedSpanElement(newOrderId).shouldBe(visible, Duration.ofSeconds(5)).click();
        return this;
    }

    public void submitRepeatComplaint(String typeSignature) {
        myСomplaintsPage.openMyСomplaintsPage()
                .clickAppealDecisionText();

        getOrderIdRepeatFilling()
                .openRepeatFilingPage()
                .clickModalWindowSavedDrafts()
                .scrollBannerInformerFillDetails()
                .enterInspectionNumberAndSelect()
                .enterReasonForDisagreement()
                .uploadDocumentIfHidden()
                .verifyFileNameIsDisplayed()
                .handleTypeOfSignature(typeSignature)
                .scrollAndClickButtonContinue()
                .checkAttachedDocuments()
                .checkSpanFileInfoPdf()
                .handleSendInputAttachSignatureFile(typeSignature);
    }
}

