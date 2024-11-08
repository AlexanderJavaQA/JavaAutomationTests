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

    private SelenideElement buttonSend = $x("//span[contains(text(),' Отправить ')]");
    private SelenideElement inputAttachSignatureFile = $x("//input[@class='input']");
    private SelenideElement buttonSignAndSend = $x("//span[contains(text(),' Подписать и отправить ')]");
    private SelenideElement linkPdfFile = $("a.file-link[href*='download']");
    private SelenideElement textAttachSignatureFile = $x("//span[contains(text(),'Документ2')]");

    File file = new File("C:\\Users\\aleksandr.kurbanov\\Desktop\\Документ2-УКЭП.sig");

    @SneakyThrows
    public SigningComplaintPage scrollInputAttachSignatureFile() {
        executeJavaScript("arguments[0].scrollIntoView(true);", inputAttachSignatureFile); // Скролл до элемента с помощью javaScript
        Thread.sleep(300);
        return this;
    }

    @SneakyThrows
    public SigningComplaintPage scrollButtonSend() {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", buttonSend); // Скролл до элемента с помощью javaScript
        Thread.sleep(500);
        return this;
    }

    public SigningComplaintPage attachSignatureFileAndVerify() {
        inputAttachSignatureFile.uploadFile(file);
        textAttachSignatureFile.shouldBe(visible);
        return this;
    }

    public WaitingDecisionPage clickButtonSignAndSend() {
        buttonSignAndSend.shouldBe(enabled).click();
        return new WaitingDecisionPage();
    }

    public WaitingDecisionPage clickButtonSend() {
        buttonSend.shouldBe(visible).click();
        return new WaitingDecisionPage();
    }


}
