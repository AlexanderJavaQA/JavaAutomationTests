package ui.knd;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import pages.doknd.LoginPage;

@DisplayName("Проверка элементов и действий на странице Контрольные мероприятия")
public class ControlActivitiesTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ЮЛ")
    public void loginWithFLAccount() {
        loginPage.openPage(config.controlActivitiesPage()).authenticateWithAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.UL);
    }

    @Test
    @DisplayName("Проверка перехода по вкладке ЕРП и ЕРКНМ")
    public void shouldDisplayHeaderControlActivities() {
        controlActivitiesPage.openControlActivitiesPage().isHeaderERPVisible().clickHeaderERP().isTextBannerVisible().isHeaderERKNMVisible().clickHeaderERKNM().isTextBannerVisible();
    }

    @Test
    @DisplayName("Проверка работы поля поиска по объекту")
    public void shouldSearchByInputField() {
        controlActivitiesPage.openControlActivitiesPage().setSearchInputField("Москва").getSearchInputFieldValue().clearSearchInputField();
    }

    @Test
    @DisplayName("Проверка открытия выпадающего меню сортировки")
    public void shouldOpenSortDropdown() {
        controlActivitiesPage.openControlActivitiesPage().openSortDropdown().isSortDropdownVisible();
    }

    @Test
    @DisplayName("Проверка выбора сортировки по возрастанию даты")
    public void shouldSelectSortAscendingDate() {
        controlActivitiesPage.openControlActivitiesPage().openSortDropdown().selectSortAscendingDate();
    }

    @Test
    @DisplayName("Проверка выбора сортировки по убыванию даты")
    public void shouldSelectSortDescendingDate() {
        controlActivitiesPage.openControlActivitiesPage().openSortDropdown().selectSortDescendingDate();
    }

    @Test
    @DisplayName("Проверка открытия фильтров")
    public void shouldOpenFilterPanel() {
        controlActivitiesPage.openControlActivitiesPage().openFilterPanel().isFilterToggleButtonVisible();
    }

    @Test
    @DisplayName("Проверка открытия дропдауна 'Характер/вид мероприятия'")
    public void shouldOpenEventTypeDropdown() {
        controlActivitiesPage.openControlActivitiesPage().openEventTypeDropdown().isEventTypeDropdownVisible();
    }

    @Test
    @DisplayName("Проверка клика по кнопке 'Обжаловать'")
    public void shouldClickAppealButton() {
        controlActivitiesPage.openControlActivitiesPage().clickAppealButton(0); // Клик по первой кнопке
    }


    @Test
    @DisplayName("Проверка работы выпадающего списка сортировки по возрастанию даты")
    public void testSortDropdownSelectAscendingDate() {
        controlActivitiesPage.openControlActivitiesPage().openSortDropdown().isSortDropdownVisible().isSortAscendingDateOptionVisible().selectSortAscendingDate();
    }

    @Test
    @DisplayName("Проверка работы выпадающего списка сортировки по возрастанию даты")
    public void testSortDrdownSelectAscendingDate() {
        controlActivitiesPage.openControlActivitiesPage().isFilterToggleButtonVisible().openFilterPanel().openEventTypeDropdown();
    }
}



