package pages.knd;

import apimodels.erknm.ControlTypesResponseItem;
import appconfig.AppConfig;
import com.codeborne.selenide.*;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import pages.doknd.FillDetailsComplaintPage;

import java.util.List;
import java.util.function.Consumer;

import static api.TypesOfContolService.getTypesOfContolList;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlSupervisionPage {

    // Кнопка перехода в раздел "Контрольные и профилактические мероприятия"
    private  SelenideElement goToSectionButtonControlAndPrevention = $x("//a[@href='/org-profile/knd/inspect']");
    // Кнопка перехода в раздел "Объекты контроля"
    private  SelenideElement goToSectionButtonControlObjects = $x("//a[@href='/org-profile/knd/control-objects']");
    // Кнопка перехода в раздел "Уведомления по объектам предпринимательской деятельности"
    private  SelenideElement goToSectionButtonBusinessNotifications = $x("//a[@href='/org-profile/knd/orgactivity']");
    // Ссылка "Перейти в реестр" в блоке "Обязательные требования"
    private  SelenideElement goToRegistryLink = $x("//a[contains(text(),'Перейти в реестр')]");

    // Ссылка "Подать возражение" в блоке "Возражение на предостережение"
    private  SelenideElement submitObjectionLink = $x("//a[contains(text(),'Подать возражение')]");

    // Ссылка "Отправить" в блоке "Отправьте уведомление о начале предпринимательской деятельности"
    private  SelenideElement sendNotificationLink = $x("//a[contains(text(),'Отправить')]");
    // Ссылка "Подать жалобу" в блоке "Досудебное обжалование"
    private  SelenideElement submitComplaintLink = $x("//a[contains(text(),'Подать жалобу')]");

    // Ссылка "Подать заявку" в блоке "Запись на профвизит"
    private  SelenideElement submitApplicationForVisitLink = $x("//lk-banner[2]//lk-screen-pad[1]//div[3]//a[1]");

    // Ссылка "Подать заявку" в блоке "Консультация инспектора"
    private  SelenideElement submitApplicationForConsultationLink = $x("//lk-banner[3]//lk-screen-pad[1]//div[3]//a[1]");
    // Кнопка "Скачать приложение" в блоке с мобильным баннером
    private  SelenideElement downloadAppButton = $x("//lk-mobile-banner[@class='desktop-banner']//span[contains(text(),'Скачать приложение')]");

    // Коллекция ссылок на доступные виды контроля на странице
    private  ElementsCollection controlTypeLinks = $$x("//div[contains(@class, 'pt-horizontal')]//li//a");

    FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();
    static ControlSupervisionPage controlSupervisionPage = new ControlSupervisionPage();
    private String originalWindow;
    private String controlTypeTitle;
    private String accTValue;
    private String supervisionId;
    private List<ControlTypesResponseItem>  typesOfControl;
    AppConfig config = create(AppConfig.class);

    public ControlSupervisionPage openControlSupervisionPage() {
        open(config.orgProfilePage());
        return this;
    }

    public ControlSupervisionPage closeAllTabs() {
        WebDriver driver = getWebDriver();

        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
            driver.close(); // Закрываем каждую вкладку
        }

        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);

        return this;
    }

    public void scrollAndClick(SelenideElement element) {
        fillDetailsComplaintPage.scrollToElement(element);
        element.shouldBe(Condition.visible).click();
    }

    public ControlSupervisionPage validateTypesOfControlExistence(String accTValue) {
        List<ControlTypesResponseItem> typesOfControl = (List<ControlTypesResponseItem>) getTypesOfContolList(accTValue);

        if (typesOfControl.isEmpty()) {
            throw new AssertionError("Список typesOfControl пуст, тест не может быть завершен.");
        }

        return this;
    }


    public ControlSupervisionPage уцу(String accTValue) {
        for (SelenideElement controlTypeLink : controlTypeLinks) {
            controlTypeLink.shouldBe(enabled).click();
          //  webdriver().shouldHave(url.);

        }


        return this;
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

    public ControlSupervisionPage getAccountTokenValue() {
        accTValue = getWebDriver().manage().getCookieNamed("acc_t").getValue();
        return this;
    }


    public ControlSupervisionPage validateTypesOfControlExistence() {
        typesOfControl = (List<ControlTypesResponseItem>) getTypesOfContolList(accTValue);
        if (typesOfControl.isEmpty()) {
            throw new AssertionError("Список typesOfControl пуст, тест не может быть завершен.");
        }

        return this;
    }

    public ControlSupervisionPage clickAndValidateAllControlTypeLinks() {
        for (SelenideElement controlTypeLink : controlTypeLinks) {
            getControlTypeTitle(controlTypeLink);
            scrollAndClick(controlTypeLink);
            switchToNewWindow();
            getSupervisionIdForControlType(controlTypeTitle);
            verifyUrlContainsSupervisionId(supervisionId);
            closeNewWindow();
            switchToOldWindow();
        }

        return this;
    }


    private void getControlTypeTitle(SelenideElement controlTypeLink) {
        controlTypeTitle = controlTypeLink.shouldBe(enabled).getText();
    }


    private void getSupervisionIdForControlType(String controlTypeTitle) {
        supervisionId = typesOfControl.stream()
                .filter(x -> x.getTitle().contains(controlTypeTitle))
                .map(ControlTypesResponseItem::getSupervisionId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден supervisionId для: " + controlTypeTitle));
    }

    private void verifyUrlContainsSupervisionId(String supervisionId) {
        webdriver().shouldHave(urlContaining(supervisionId));
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
