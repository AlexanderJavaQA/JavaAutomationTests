package pages.knd;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.aeonbits.owner.ConfigFactory.create;

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
    // Выпадающее меню для сортировки по дате
    private SelenideElement sortDropdown = $x("//lib-dropdown[@placeholder='Сортировка']");
    // Выпадающий элемент для сортировки по возрастанию даты
    private SelenideElement sortAscendingDateOption = $x("//span[@class='dropdown-item-text ng-star-inserted' and contains(text(), 'По возрастанию даты')]");
    // Выпадающий элемент для сортировки по убыванию даты
    private SelenideElement sortDescendingDateOption = $x("//span[@class='dropdown-item-text ng-star-inserted' and contains(text(), 'По убыванию даты')]");
    //Элемент отображающий текст после выбора сортировки по возрастанию
    private SelenideElement selectedSortingOption = $x("//div[@class='dropdown-value ng-star-inserted']//span[@class='selected-value-text ng-star-inserted' and contains(text(), 'По возрастанию даты')]");

    // Кнопка для открытия панели фильтрации
    private SelenideElement filterToggleButton = $x("//button[@class='inline-button ng-star-inserted']");
    // Выпадающий список для выбора характера/вида мероприятия
    private SelenideElement eventTypeDropdown = $x("//lib-dropdown[@class='ng-pristine ng-valid ng-touched']//a[@class='dropdown-arrow ng-star-inserted']");


    // Коллекция всех карточек на странице
    private ElementsCollection cards = $$x("//div[contains(@class, 'substrate-card')]");

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

    // Даты начала проверок (например: "22.08.2022")
    private ElementsCollection startDateValues = $$x("//div[contains(@class, 'start-date')]//div[contains(@class, 'content')]");

    // Лейбл для подзаголовка "Окончание"
    private ElementsCollection endDateLabels = $$x("//div[contains(@class, 'end-date')]//div[contains(@class, 'info-label') and contains(text(), 'Окончание')]");

    // Даты окончания проверок (например: "02.09.2022")
    private ElementsCollection endDateValues = $$x("//div[contains(@class, 'end-date')]//div[contains(@class, 'content')]");

    // Кнопки "Обжаловать"
    private ElementsCollection appealButtons = $$x("//a[contains(text(), 'Обжаловать')]");
    AppConfig config = create(AppConfig.class);

    public ControlActivitiesPage openControlActivitiesPage() {
        open(config.controlActivitiesPage());
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

    public ControlActivitiesPage isTextBannerVisible() {
        textBanner.shouldBe(visible);
        return this;
    }

    public ControlActivitiesPage getHeaderERPText() {
        headerERP.shouldBe(visible).getText();
        return this;
    }

    // Методы для searchInputField
    public ControlActivitiesPage setSearchInputField(String text) {
        searchInputField.shouldBe(enabled).setValue(text);
        return this;
    }

    public ControlActivitiesPage clearSearchInputField() {
        searchInputField.shouldBe(visible).clear();
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
