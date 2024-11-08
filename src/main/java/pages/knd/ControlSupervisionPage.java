package pages.knd;

import appconfig.AppConfig;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import pages.doknd.FillDetailsComplaintPage;

import java.util.function.Consumer;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlSupervisionPage {

    // Кнопка перехода в раздел "Контрольные и профилактические мероприятия"
    private final SelenideElement goToSectionButtonControlAndPrevention = $x("//a[@href='/org-profile/knd/inspect']");
    // Кнопка перехода в раздел "Объекты контроля"
    private final SelenideElement goToSectionButtonControlObjects = $x("//a[@href='/org-profile/knd/control-objects']");
    // Кнопка перехода в раздел "Уведомления по объектам предпринимательской деятельности"
    private final SelenideElement goToSectionButtonBusinessNotifications = $x("//a[@href='/org-profile/knd/orgactivity']");
    // Ссылка "Перейти в реестр" в блоке "Обязательные требования"
    private final SelenideElement goToRegistryLink = $x("//a[contains(text(),'Перейти в реестр')]");

    // Ссылка "Подать возражение" в блоке "Возражение на предостережение"
    private final SelenideElement submitObjectionLink = $x("//a[contains(text(),'Подать возражение')]");

    // Ссылка "Отправить" в блоке "Отправьте уведомление о начале предпринимательской деятельности"
    private final SelenideElement sendNotificationLink = $x("//a[contains(text(),'Отправить')]");
    // Ссылка "Подать жалобу" в блоке "Досудебное обжалование"
    private final SelenideElement submitComplaintLink = $x("//a[contains(text(),'Подать жалобу')]");

    // Ссылка "Подать заявку" в блоке "Запись на профвизит"
    private final SelenideElement submitApplicationForVisitLink = $x("//lk-banner[2]//lk-screen-pad[1]//div[3]//a[1]");

    // Ссылка "Подать заявку" в блоке "Консультация инспектора"
    private final SelenideElement submitApplicationForConsultationLink = $x("//lk-banner[3]//lk-screen-pad[1]//div[3]//a[1]");
    // Кнопка "Скачать приложение" в блоке с мобильным баннером
    private final SelenideElement downloadAppButton = $x("//lk-mobile-banner[@class='desktop-banner']//span[contains(text(),'Скачать приложение')]");

    FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();
    static ControlSupervisionPage controlSupervisionPage = new ControlSupervisionPage();
    private String originalWindow;
    AppConfig config = create(AppConfig.class);

    public ControlSupervisionPage openControlSupervisionPage() {
        open(config.pguOrgProfileKndUrlUat());
        return this;
    }

    public ControlSupervisionPage closeAllTabs() {
        WebDriver driver = WebDriverRunner.getWebDriver();

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
            driver.close(); // Закрываем каждую вкладку
        }

        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);

        return this;
    }

    private void scrollAndClick(SelenideElement element) {
        fillDetailsComplaintPage.scrollToElement(element);
        element.shouldBe(Condition.visible).click();
    }

    public ControlSupervisionPage waitForAndClickGoToSectionControlAndPrevention() {
        scrollAndClick(goToSectionButtonControlAndPrevention);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForControlObjectsSection() {
        scrollAndClick(goToSectionButtonControlObjects);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForBusinessNotificationsSection() {
        scrollAndClick(goToSectionButtonBusinessNotifications);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForRegistryLink() {
        scrollAndClick(goToRegistryLink);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForSubmitObjectionLink() {
        scrollAndClick(submitObjectionLink);
        return this;
    }

    public ControlSupervisionPage closeAboutBlankTabs() {
        WebDriver driver = webdriver().object();
        String originalHandle = driver.getWindowHandle();

        // Перебираем все открытые вкладки
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);

            if ("about:blank".equals(driver.getCurrentUrl())) {
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForSendNotificationLink() {
        scrollAndClick(sendNotificationLink);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForSubmitComplaintLink() {
        scrollAndClick(submitComplaintLink);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForApplicationForVisitLink() {
        scrollAndClick(submitApplicationForVisitLink);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForApplicationForConsultationLink() {
        scrollAndClick(submitApplicationForConsultationLink);
        return this;
    }

    public ControlSupervisionPage clickAndWaitForDownloadAppButton() {
        scrollAndClick(downloadAppButton);
        return this;
    }

    public ControlSupervisionPage switchToNewWindow() {
        switchTo().window(1);
        return this;
    }

    public ControlSupervisionPage switchToOldWindow() {
        switchTo().window(0);
        return this;
    }
    public ControlSupervisionPage closeNewWindow() {
        switchTo().window(1).close();
        return this;
    }

    @SneakyThrows
    public ControlSupervisionPage verifyCurrentUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    public ControlSupervisionPage switchBackToOriginalWindow(String originalWindow) {
        switchTo().window(originalWindow);
        return this;
    }

    public static void performNavigationTest(Consumer<ControlSupervisionPage> action, String expectedUrl) {
        controlSupervisionPage.openControlSupervisionPage();
        action.accept(controlSupervisionPage); // Выполнение переданного действия на объекте controlSupervisionPage
        controlSupervisionPage.closeAboutBlankTabs()
                .switchToNewWindow()
                .verifyCurrentUrl(expectedUrl)
                .closeNewWindow()
                .switchToOldWindow();
    }

}
