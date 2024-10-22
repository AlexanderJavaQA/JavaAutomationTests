package doknd.pages;

import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Data
public class LoginPage {

    private SelenideElement loginField = $x("//input[@id='login']");
    private SelenideElement passwordField = $x("//input[@id='password']");
    private SelenideElement wideButton = $x("//button[@class='plain-button plain-button_wide']");
    private SelenideElement buttonInOut = $x("//a[@class='button-in-out']");
    private SelenideElement bespalovIPAuth = $x("//h4[contains(text(),'ИП')]");
    private SelenideElement bespalovFLAuth = $x("//h4[contains(text(),'Беспалов  Ф.А.')]");
    private SelenideElement bespalovULAuth = $x("//*[text()='ООО Ромашка']");
    private SelenideElement emelianovIPAuth = $x("//*[text()='ИП Емельянов А. А.']");
    private SelenideElement emelianovFLAuth = $x("//*[text()='Емельянов А. А.']");

    private String accTValue;

    public enum AccountType {
        IP,
        UL,
        FL
    }

    public void openLoginPage(String url) {
        open(url);
    }

    public void enterCredentials(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        wideButton.click();
    }

    public void selectAccount(AccountType accountType) {
        switch (accountType) {
            case IP:
                bespalovIPAuth.shouldBe(visible).click();
                break;
            case UL:
                bespalovULAuth.shouldBe(visible).click();
                break;
            case FL:
                bespalovFLAuth.shouldBe(visible).click();
                break;
            default:
                throw new IllegalArgumentException("Неизвестный тип аккаунта: " + accountType);
        }
    }

    public void login(String url, String login, String password, AccountType accountType) {
        openLoginPage(url);
        enterCredentials(login, password);
        clickLoginButton();
        selectAccount(accountType);
    }


    public void loginToKndPortal(String URL, String login, String password, SelenideElement element) {
        WebDriverManager.chromedriver().setup();
        open(URL);
        loginField.setValue(login);
        passwordField.setValue(password);
        wideButton.click();
        element.shouldBe(enabled).click();
    }

    public String getCookieNamed(String URL, String login, String password, SelenideElement element) {
        WebDriverManager.chromedriver().setup();
        loginToKndPortal(URL, login, password, element);
        String accTValue = getWebDriver().manage().getCookieNamed("acc_t").getValue();
        return accTValue;
    }

    public String getAccTValue() {
        accTValue = getWebDriver().manage().getCookieNamed("acc_t").getValue();
        return accTValue;
    }
}
