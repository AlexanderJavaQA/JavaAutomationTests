package pages.doknd;

import appconfig.AppConfig;
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

    // Поле для загрузки файлов
    private SelenideElement fileUploadInput = $x("//input[@class='input']");

    // Поле для поиска решения, которое будет обжаловано
    private SelenideElement fieldReasonDisagreement = $x("//input[@aria-labelledby='Введите минимум 6 символов']");

    // Кнопка для перехода к следующему шагу (Продолжить)
    private SelenideElement buttonContinue = $x("//span[contains(text(),'Продолжить')]");

    // Текстовое поле для ввода причины несогласия с принятым решением
    private SelenideElement disagreementReasonTextarea = $x("//textarea[@id='repeat-form-why-not-decision']");

    // Текстовое поле для отображения имени загруженного файла
    private SelenideElement textFileName = $x("//span[@class='filename text-plain black']");

    // Выпадающее меню для выбора типа электронной подписи
    private SelenideElement dropdownTypeOfElectronicSignature = $x("//a[@class='dropdown-arrow']");

    // Модальное окно с предупреждением о наличии сохраненных черновиков
    private SelenideElement modalWindowSavedDrafts = $x("//span[contains(text(),'Начать заново')]");

    // Опция выбора Усиленной Квалифицированной Электронной Подписи (УКЭП)
    private SelenideElement signatureUKEP = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись')]");

    // Опция выбора Усиленной Квалифицированной Электронной Подписи для Государственных Клиентов (УКЭП ГК)
    private SelenideElement signatureUKEPGK = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись Го')]");

    // Опция выбора Усиленной Неквалифицированной Электронной Подписи (УНЭП)
    private SelenideElement signatureUNEP = $x("//span[contains(text(),'Усиленная неквалифицированная электронная подпись')]");

    // Блок для отображения прикрепленных к заявлению документов
    private SelenideElement attachedDocuments = $x("//*[contains(text(), 'Прикрепленные документы')]");

    // Элемент, отображающий загруженные документы, содержащие текст "Тестовые данные.pdf"
    private SelenideElement uploadedDocument = $x("//*[contains(text(), 'Тестовые данные.pdf')]");

    // Заголовок с номером заявления
    private SelenideElement orderID = $x("//h3[contains(text(),'Заявление №')]");

    private String newOrderId;

    File file = new File(("C:\\Users\\aleksandr.kurbanov\\Desktop\\Тестовые данные.pdf"));

    AppConfig config = ConfigFactory.create(AppConfig.class);
    FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();


    public RepeatFilingPage checkAttachedDocuments() {
        attachedDocuments.shouldHave(text("Прикрепленные документы"));
        return this;
    }

    public RepeatFilingPage openNewTabForRepeatFilingPage() {
        switchTo().newWindow(WindowType.TAB);
        open(config.repeatedAppealPage());
        return this;
    }

    public RepeatFilingPage openNewTabForRepeatFilingPageDev2() {
        switchTo().newWindow(WindowType.TAB);
        open(config.repeatedAppealPageDev2());
        return this;
    }

    @SneakyThrows
    public RepeatFilingPage clickStartOverInSavedDraftsModal() {
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

    public RepeatFilingPage setReasonForDisagreement() {
        disagreementReasonTextarea.shouldBe(visible).setValue("Тестовые данные");
        return this;
    }

    public SelenideElement getHighlightedSpanElement(String orderId) {
        return $x("//span[@class='highlighted' and text()='" + orderId + "']");
    }


    @SneakyThrows
    public RepeatFilingPage scrollIntoViewAndClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(300);
        element.click();

        return this;
    }


    public RepeatFilingPage uploadDocumentIfHidden() {
        fileUploadInput.shouldBe(hidden).uploadFile(file);
        return this;
    }

    public RepeatFilingPage verifyFileUploaded() {
        uploadedDocument.shouldBe(visible);
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
        scrollIntoViewAndClick(buttonContinue);
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
                        .uploadSigFile()
                        .verifySigFileIsAttached()
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails();
                break;

            case "UNEP":
            case "UKEPGK":
                signingComplaintPage
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails();
                break;

            case "PEP":
                signingComplaintPage
                        .clickButtonSignAndSend()
                        .clickLinkComplaintDetails();
                break;

            default:
                throw new IllegalArgumentException("Неизвестный тип подписи: " + selectSignature);
        }
        return this;
    }

    public RepeatFilingPage extractOrderIdForRepeatFiling() {
        String orderId = orderID.text();
        newOrderId = orderId.substring(12);
        return this;
    }

    public RepeatFilingPage setInspectionNumber(String orderId) {
        fieldReasonDisagreement.shouldBe(visible).setValue(orderId);
        return this;
    }

    public RepeatFilingPage clickHighlightedInspection(String orderId) {
        $x("//span[@class='highlighted' and text()='" + orderId + "']")
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();
        return this;
    }

    public RepeatFilingPage clickInspectionNumberFromList() {
        getHighlightedSpanElement(newOrderId).shouldBe(visible, Duration.ofSeconds(5)).click();
        return this;
    }
}

