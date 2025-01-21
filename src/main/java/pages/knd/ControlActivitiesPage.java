package pages.knd;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.SneakyThrows;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.aeonbits.owner.ConfigFactory.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControlActivitiesPage {

    //заголовок страницы "Контрольные мероприятия"
    private SelenideElement headerControlActivities = $x("//h1[contains(text(),'Контрольные мероприятия')]");
    // Заголовок вкладки "Единый реестр КНМ (248-ФЗ)"
    private SelenideElement headerERKNM = $x("//span[contains(text(),'Единый реестр КНМ (248-ФЗ')]");
    //Подзаголовок поля поиск
    private SelenideElement searchFieldSubtitle = $x("//label[contains(text(),'Поиск')]");
    // Заголовок вкладки "Eдиный реестр проверок"
    private SelenideElement headerERP = $x("//span[contains(text(),'Eдиный реестр проверок (294-ФЗ)')]");
    //Текст баннера во вкладке ЕРКНМ или ЕРП проверок
    private SelenideElement textBanner = $x("//p[@class='mb-16']");
    // Поле ввода для поиска объекта по адресу, номеру или другим данным проверки
    private SelenideElement searchInputField = $x("//input[@placeholder='Адрес, номер или другие данные объекта проверки']");
    // Поле ввода для поиска объекта по адресу, номеру или другим данным проверки после ввода текста
    private SelenideElement searchInputFieldTextEntry = $x("//input[@aria-label='Адрес, номер или другие данные объекта проверки']");
    // Выпадающее меню для сортировки по дате
    private SelenideElement sortDropdown = $x("//lib-dropdown[@placeholder='Сортировка']");
    // Выпадающий элемент для сортировки по возрастанию даты
    private SelenideElement sortAscendingDateOption = $x("//span[@class='dropdown-item-text ng-star-inserted' and contains(text(), 'По возрастанию даты')]");
    // Выпадающий элемент для сортировки по убыванию даты
    private SelenideElement sortDescendingDateOption = $x("//span[@class='dropdown-item-text ng-star-inserted' and contains(text(), 'По убыванию даты')]");
    //Элемент отображающий текст после выбора сортировки по возрастанию
    private SelenideElement selectedSortingOption = $x("//div[@class='dropdown-value ng-star-inserted']//span[@class='selected-value-text ng-star-inserted' and contains(text(), 'По возрастанию даты')]");
    // Элемент, представляющий текущую выбранную страницу в пагинации.
    // Используется для проверки текущего состояния пагинации или для навигации по страницам.
    private SelenideElement currentPagePointer = $x("//span[@class='small-text color-gosblue pointer current-page']");
    // Кнопка для открытия панели фильтрации
    private SelenideElement filterToggleButton = $x("//button[@class='inline-button ng-star-inserted']");
    // Выпадающий список для выбора характера/вида мероприятия
    private SelenideElement eventTypeDropdown = $x("//lib-dropdown[@class='ng-pristine ng-valid ng-touched']//a[@class='dropdown-arrow ng-star-inserted']");
    // Коллекция всех карточек на странице
    private ElementsCollection cards = $$x("//div[contains(@class, 'substrate-card')]");
    // Элемент с текстом "Проверок не найдено"
    SelenideElement noChecksFound = $x("//*[text()='Проверок не найдено']");

    // Элемент с текстом "Попробуйте изменить параметры поиска"
    SelenideElement changeSearchParams = $x("//*[text()='Попробуйте изменить параметры поиска']");

    // Заголовки карточек (например: "Федеральный государственный пожарный надзор")
    private ElementsCollection headers = $$x("//div[contains(@class, 'general-title')]//div[contains(@class, 'title-h4')]");

    // Номера проверок (например: "№ 02210861000100043073501")
    private ElementsCollection checkNumbers = $$x("//div[contains(@class, 'info-label') and contains(text(), '№')]");

    // Названия КНО (например: "Главное управление МЧС России по Москве")
    private ElementsCollection organizationNames = $$x("//div[contains(@class, 'ctrlOrgName')]//span");

    // Лейбл для "Адреса объектов проверки"
    private ElementsCollection addressLabels = $$x("//div[contains(@class, 'addresses')]//div[contains(@class, 'info-label') and contains(text(), 'Адреса объектов проверки')]");

    // Сами адреса объектов проверки
    private ElementsCollection addresses = $$x("//div[contains(@class, 'addresses')]//div[contains(@class, 'content')]//div");

    // Лейбл для подзаголовка "Вид"
    private ElementsCollection typeLabels = $$x("//div[contains(@class, 'type')]//div[contains(@class, 'info-label') and contains(text(), 'Вид')]");

    // Виды проверок (например: "Выездная проверка")
    private ElementsCollection typeValues = $$x("//div[contains(@class, 'type')]//div[contains(@class, 'content')]");

    // Лейбл для подзаголовка "Характер"
    private ElementsCollection characteristicLabels = $$x("//div[contains(@class, 'characteristic')]//div[contains(@class, 'info-label') and contains(text(), 'Характер')]");

    // Характер проверок (например: "Плановая проверка")
    private ElementsCollection characteristicValues = $$x("//div[contains(@class, 'characteristic')]//div[contains(@class, 'content')]");

    // Лейбл для подзаголовка "Начало"
    private ElementsCollection startDateLabels = $$x("//div[contains(@class, 'start-date')]//div[contains(@class, 'info-label') and contains(text(), 'Начало')]");
    // Коллекция для нахождения списка всех элементов в выпадающем списке "Показать"
    private ElementsCollection pageSizeOptions = $$x("//div[@class='page-size-item small-text pointer ng-star-inserted']");

    // Даты начала проверок (например: "22.08.2022")
    private ElementsCollection startDateValues = $$x("//div[contains(@class, 'start-date')]//div[contains(@class, 'content')]");

    // Лейбл для подзаголовка "Окончание"
    private ElementsCollection endDateLabels = $$x("//div[contains(@class, 'end-date')]//div[contains(@class, 'info-label') and contains(text(), 'Окончание')]");

    // Даты окончания проверок (например: "02.09.2022")
    private ElementsCollection endDateValues = $$x("//div[contains(@class, 'end-date')]//div[contains(@class, 'content')]");

    // Кнопки "Обжаловать"
    private ElementsCollection appealButtons = $$x("//a[contains(text(), 'Обжаловать')]");
    // Локатор элемента ссылки "Написать в поддержку"
    private SelenideElement supportLink = $x("//a[contains(text(), 'Написать в')]");
    // Тексты баннеров
    private static final String ERP_BANNER_TEXT = "Если проверки нет в списке, проверьте её законность в Едином реестре проверок";
    private static final String ERKNM_BANNER_TEXT = "Если проверки нет в списке, обратитесь в поддержку портала";

    private final String EXPECTED_NO_CHECKS_FOUND_TEXT = "Проверок не найдено";
    private final String EXPECTED_CHANGE_SEARCH_PARAMS_TEXT = "Попробуйте изменить параметры поиска";

    private static final String SUPPORT_LINK_EXPECTED_URL = "https://www.gosuslugi.ru/newsearch?query=досудебное%20обжалование";

    String decodedExpectedHref;
    AppConfig config = create(AppConfig.class);

    public ControlActivitiesPage openControlActivitiesPage() {
        open(config.controlActivitiesPage());
        return this;
    }

    public ControlActivitiesPage verifyFirstHeaderVisible() {
        headers.get(0).shouldBe(visible);
        return this;
    }


    public ControlActivitiesPage checkHeadersCount(int minSize, int maxHeaders) {
        int headersCount = headers.size();
        System.out.printf(String.valueOf(headersCount));
        assertTrue(headersCount >= minSize && headersCount <= maxHeaders,
                "Количество заголовков (" + headersCount + ") превышает допустимое значение: " + maxHeaders);

        return this;
    }


    public ControlActivitiesPage getPageSizeOptionsText() {
        List<String> pageSizeOptionTexts = pageSizeOptions.texts();
        System.out.printf(pageSizeOptionTexts.toString());
        return this;
    }

    public ControlActivitiesPage clickSupportLinkWithSwitchBack(int times) {
        for (int i = 0; i < times; i++) {
            clickSupportLink().switchToOldWindow();
        }
        return this;
    }

    public ControlActivitiesPage clickOnPageSizeOption(String optionText) {
        pageSizeOptions.findBy(text(optionText)).click();
        return this;
    }

    public ControlActivitiesPage clickSupportLink() {
        supportLink.click();
        return this;
    }


    @SneakyThrows
    public ControlActivitiesPage scrollAndClickCurrentPagePointer() {
        executeJavaScript("arguments[0].scrollIntoView(true);", currentPagePointer);
        Thread.sleep(300);
        currentPagePointer.click();
        return this;
    }

    public ControlActivitiesPage verifyCurrentPagePointerAndCheckHeaders() {
        try {
            currentPagePointer.shouldBe(visible, Duration.ofSeconds(4));
        } catch (ElementNotFound e) {
            checkHeadersCount(0, 10);
            throw new RuntimeException("Элемент currentPagePointer не найден или не видим.");
        }
        return this;
    }

    @SneakyThrows
    public ControlActivitiesPage scrollAndClickSupportLinks() {
        executeJavaScript("arguments[0].scrollIntoView(true);", supportLink);
        Thread.sleep(300);
        supportLink.click();

        return this;
    }

    public ControlActivitiesPage switchToNewWindow() {
        switchTo().window(1);
        return this;
    }

    public ControlActivitiesPage isNoChecksFoundVisible() {
        noChecksFound.shouldBe(visible).shouldHave(text(EXPECTED_NO_CHECKS_FOUND_TEXT));
        return this;
    }

    public ControlActivitiesPage isChangeSearchParamsVisible() {
        changeSearchParams.shouldBe(visible).shouldHave(text(EXPECTED_CHANGE_SEARCH_PARAMS_TEXT));
        return this;
    }

    public ControlActivitiesPage switchToOldWindow() {
        switchTo().window(0);
        return this;
    }

    public ControlActivitiesPage checkNumberOfOpenNewTabs(Integer number) {
        int numberOfOpenTabs = WebDriverRunner.getWebDriver().getWindowHandles().size() - 1;
        assertEquals(number, numberOfOpenTabs, "Количество открытых вкладок не соответствует ожидаемому");
        return this;
    }

    public ControlActivitiesPage verifySupportLinkUrl() {
        String actualHref = supportLink.getAttribute("href");
        // Декодируем значение
        String decodedHref = URLDecoder.decode(actualHref, StandardCharsets.UTF_8);
        decodedExpectedHref = URLDecoder.decode(SUPPORT_LINK_EXPECTED_URL, StandardCharsets.UTF_8);
        assertTrue(
                decodedExpectedHref.equals(decodedHref),
                "URL ссылки не соответствует ожидаемому. Ожидаемый: " + decodedExpectedHref + ", Фактический: " + decodedHref
        );
        return this;
    }

    public ControlActivitiesPage checkUrlSupportLink() {
        String actualSupportLinkUrl = URLDecoder.decode(WebDriverRunner.url(), StandardCharsets.UTF_8);

        assertTrue(
                decodedExpectedHref.equals(actualSupportLinkUrl),
                "URL ссылки не соответствует ожидаемому. Ожидаемый: " + decodedExpectedHref + ", Фактический: " + actualSupportLinkUrl
        );
        return this;
    }

    // Методы для headerControlActivities
    public ControlActivitiesPage isHeaderControlActivitiesVisible() {
        headerControlActivities.shouldBe(visible).isDisplayed();
        return this;
    }

    public ControlActivitiesPage getHeaderControlActivitiesText() {
        headerControlActivities.shouldBe(visible).getText();
        return this;
    }


    public ControlActivitiesPage getHeaders() {
        headers.texts();
        return this;
    }

    public ControlActivitiesPage checkHeadersVisibility() {
        headers.stream().map(x -> x.shouldBe(visible));
        return this;
    }


    public ControlActivitiesPage getCheckNumbers() {
        checkNumbers.texts();
        return this;
    }

    public ControlActivitiesPage getOrganizationNames() {
        organizationNames.texts();
        return this;
    }

    public ControlActivitiesPage getAddresses() {
        addresses.texts();
        return this;
    }

    public ControlActivitiesPage getAddressLabels() {
        addressLabels.texts();
        return this;
    }

    public ControlActivitiesPage getTypeValues() {
        typeValues.texts();
        return this;
    }

    public ControlActivitiesPage getTypeLabels() {
        typeLabels.texts();
        return this;
    }

    public ControlActivitiesPage getCharacteristicValues() {
        characteristicValues.texts();
        return this;
    }

    public ControlActivitiesPage getCharacteristicLabels() {
        characteristicLabels.texts();
        return this;
    }

    public ControlActivitiesPage getStartDates() {
        startDateValues.texts();
        return this;
    }

    public ControlActivitiesPage getStartDateLabels() {
        startDateLabels.texts();
        return this;
    }

    public ControlActivitiesPage getEndDates() {
        endDateValues.texts();
        return this;
    }

    public ControlActivitiesPage getEndDateLabels() {
        endDateLabels.texts();
        return this;
    }

    public ControlActivitiesPage clickAppealButton(int index) {
        appealButtons.get(index).click();
        return this;
    }

    // Методы для headerERKNM
    public ControlActivitiesPage clickHeaderERKNM() {
        headerERKNM.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isHeaderERKNMVisible() {
        headerERKNM.shouldBe(visible).isDisplayed();
        return this;
    }


    public ControlActivitiesPage getHeaderERKNMText() {
        headerERKNM.shouldBe(visible).getText();
        return this;
    }

    // Методы для headerERP
    public ControlActivitiesPage clickHeaderERP() {
        headerERP.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isHeaderERPVisible() {
        headerERP.shouldBe(visible);
        return this;
    }


    public ControlActivitiesPage checkERPTextBannerContent() {
        textBanner.shouldHave(text(ERP_BANNER_TEXT));
        return this;
    }

    public ControlActivitiesPage checkERKNMTextBannerContent() {
        textBanner.shouldHave(text(ERKNM_BANNER_TEXT));
        return this;
    }

    public ControlActivitiesPage getHeaderERPText() {
        headerERP.shouldBe(visible).getText();
        return this;
    }

    // Методы для searchInputField
    public ControlActivitiesPage setSearchInputField(String text) {
        searchInputFieldTextEntry.shouldBe(enabled).setValue(text);
        return this;
    }

    public ControlActivitiesPage clearSearchInputAfterTextEntry() {
        searchInputFieldTextEntry.clear();
        return this;
    }

    public ControlActivitiesPage clearSearchInputField() {
        searchInputField.clear();
        return this;
    }

    public ControlActivitiesPage getSearchInputFieldValue() {
        searchInputField.shouldBe(visible).getValue();
        return this;
    }

    // Методы для sortDropdown
    public ControlActivitiesPage openSortDropdown() {
        sortDropdown.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isSortDropdownVisible() {
        sortDropdown.shouldBe(visible).isDisplayed();
        return this;
    }

    // Методы для sortAscendingDateOption
    public ControlActivitiesPage selectSortAscendingDate() {
        sortAscendingDateOption.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isSortAscendingDateOptionVisible() {
        sortAscendingDateOption.shouldBe(visible).isDisplayed();
        return this;
    }

    // Методы для sortDescendingDateOption
    public ControlActivitiesPage selectSortDescendingDate() {
        sortDescendingDateOption.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isSortDescendingDateOptionVisible() {
        sortDescendingDateOption.shouldBe(visible).isDisplayed();
        return this;
    }

    // Методы для filterToggleButton
    public ControlActivitiesPage openFilterPanel() {
        filterToggleButton.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isFilterToggleButtonVisible() {
        filterToggleButton.shouldBe(visible).isDisplayed();
        return this;
    }

    // Методы для eventTypeDropdown
    public ControlActivitiesPage openEventTypeDropdown() {
        eventTypeDropdown.shouldBe(enabled).click();
        return this;
    }

    public ControlActivitiesPage isEventTypeDropdownVisible() {
        eventTypeDropdown.shouldBe(visible).isDisplayed();
        return this;
    }

}
