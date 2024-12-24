package ui.knd;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.*;
import pages.doknd.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка элементов и действий на странице Контрольные мероприятия")
public class ControlActivitiesTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ЮЛ")
    public void loginWithFLAccount() {
        loginPage.openPage(config.controlActivitiesPage())
                .authAccountType(config.userLoginBespalov(), config.userPasswordBespalov(), LoginPage.AccountType.UL);
    }

    @Test
    @DisplayName("Проверка перехода по вкладке ЕРП и ЕРКНМ")
    public void checkTabSwitchingERPAndERKNM() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .isHeaderERPVisible()
                .clickHeaderERP()
                .checkERPTextBannerContent()
                .isHeaderERKNMVisible()
                .clickHeaderERKNM()
                .checkERKNMTextBannerContent();
    }

    @Test
    @DisplayName("Проверка перехода по ссылке Написать в поддержку")
    public void checkUrlSupportLink() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .verifySupportLinkUrl()
                .clickSupportLink()
                .switchToNewWindow()
                .checkUrlSupportLink();
    }

    @Test
    @DisplayName("Проверка количества открытых новых вкладок после клика по ссылке Обратитесь в поддержку")
    public void checkFiveTabsOpenedAfterClickingSupportLink() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .clickSupportLinkWithSwitchBack(5)
                .checkNumberOfOpenNewTabs(5);
    }

    @Test
    @Order(2)
    @DisplayName("Проверка отображения сообщений 'Проверок не найдено' и 'Попробуйте изменить параметры поиска' при отсутствии результатов")
    public void checkMessagesDisplayedForNoSearchResults() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .setSearchInputField("Тестовые данные")
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible();
    }

    @Test
    @DisplayName("Проверка отображения заголовков КНО после очистки поиска")
    public void checkHeadersDisplayedAfterClearingSearch() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .setSearchInputField("Тестовые данные")
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible()
                .clearSearchInputField()
                .checkHeadersVisibility();
    }

    @Test
    @DisplayName("Проверка корректного переключения между вкладками 'Единый реестр проверок' и 'Единый реестр КНМ' при множественном переходе")
    public void checkTabSwitchingBetweenERPAndERKNM() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .isHeaderERPVisible();

        for (int i = 0; i < 30; i++) {
            controlActivitiesPage.clickHeaderERP()
                    .checkERPTextBannerContent()
                    .isHeaderERKNMVisible()
                    .clickHeaderERKNM()
                    .checkERKNMTextBannerContent();
        }
    }
}



