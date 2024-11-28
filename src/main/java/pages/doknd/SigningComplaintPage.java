package pages.doknd;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Data
public class SigningComplaintPage {

    // Кнопка для отправки жалобы
    private SelenideElement sendButton = $x("//span[contains(text(),' Отправить ')]");

    // Поле для загрузки файла подписи
    private SelenideElement attachSignatureFileInput = $x("//input[@class='input']");

    // Кнопка для подписания и отправки документа
    private SelenideElement signAndSendButton = $x("//span[contains(text(),' Подписать и отправить ')]");

    // Ссылка на скачивание PDF-файла
    private SelenideElement pdfFileLink = $("a.file-link[href*='download']");

    // Текст для проверки успешной загрузки файла подписи
    private SelenideElement attachSignatureFileText = $x("//span[contains(text(),'Документ2')]");


    File file = new File("C:\\Users\\aleksandr.kurbanov\\Desktop\\Документ2-УКЭП.sig");

    @SneakyThrows
    public SigningComplaintPage scrollInputAttachSignatureFile() {
        executeJavaScript("arguments[0].scrollIntoView(true);", attachSignatureFileInput);
        Thread.sleep(300);
        return this;
    }

    @SneakyThrows
    public static void scrollToCenterAndClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(300); // Небольшая задержка для корректного отображения элемента после скролла
        element.click();
    }

    @SneakyThrows
    public SigningComplaintPage scrollButtonSend() {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", sendButton);
        Thread.sleep(300);
        return this;
    }

    public SigningComplaintPage uploadSigFile() {
        attachSignatureFileInput.uploadFile(file);
        return this;
    }

    public SigningComplaintPage verifySigFileIsAttached() {
        attachSignatureFileText.shouldBe(visible);
        return this;
    }

    public WaitingDecisionPage clickButtonSignAndSend() {
        signAndSendButton.shouldBe(enabled).click();
        return new WaitingDecisionPage();
    }

    public WaitingDecisionPage clickButtonSend() {
        sendButton.shouldBe(visible).click();
        return new WaitingDecisionPage();
    }
}
