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
    @DisplayName("Проверка отображения сообщений 'Проверок не найдено' и 'Попробуйте изменить параметры поиска' при отсутствии результатов")
    public void checkMessagesDisplayedForNoSearchResults() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .setSearchInputField("Тестовые данные")
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible();
    }

    @Test
    @DisplayName("Проверка отображения заголовков КНО после очистки поиска для ЕРКНМ")
    public void checkHeadersDisplayedAfterClearingSearchErknm() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .setSearchInputField("Тестовые данные")
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible()
                .clearSearchInputAfterTextEntry()
                .checkHeadersVisibility();
    }

    @Test
    @DisplayName("Проверка отображения заголовков КНО после очистки поиска для ЕРП проверок")
    public void checkHeadersDisplayedAfterClearingSearchErp() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .clickHeaderERP()
                .setSearchInputField("Тестовые данные")
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible()
                .clearSearchInputAfterTextEntry()
                .checkHeadersVisibility();
    }

    @Test
    @DisplayName("Проверка отображения заголовков КНО после очистки поиска и ввода текста длиной 1000 символов для ЕРКНМ")
    public void checkHeadersAfterClearingAndLongInput() {
        String longText = "A".repeat(1000); // Генерация строки длиной 1000 символов

        controlActivitiesPage
                .openControlActivitiesPage()
                .setSearchInputField(longText)
                .isNoChecksFoundVisible()
                .isChangeSearchParamsVisible()
                .clearSearchInputAfterTextEntry()
                .checkHeadersVisibility();
    }


    @Test
    @DisplayName("Проверка отображения заголовков КНО после многократной очистки поиска для ЕРКНМ")
    public void checkHeadersAfterSearchClear() {
        controlActivitiesPage
                .openControlActivitiesPage();

        for (int i = 0; i < 30; i++) {
            controlActivitiesPage
                    .setSearchInputField("Тестовые данные")
                    .isNoChecksFoundVisible()
                    .isChangeSearchParamsVisible()
                    .clearSearchInputAfterTextEntry()
                    .checkHeadersVisibility();
        }
    }


    //Проверка что если есть кнопка показать кликаем по ней -> выбираем все элементы кликаем по последнему-> сравнием количсевто символов -> если нет то сравниваем текущее кол во элементов


    @Test
    @Order(2)
    @DisplayName("Проверка что если есть кнопка показать кликаем по ней")
    public void checkssHeadersAfterSearchClear() {
        controlActivitiesPage
                .openControlActivitiesPage()
                .verifyCurrentPagePointerAndCheckHeaders()
                .scrollAndClickCurrentPagePointer()
                .getPageSizeOptionsText()
                .clickOnPageSizeOption(String.valueOf(25))
                .verifyFirstHeaderVisible()
                .checkHeadersCount(10, 25);
        ;
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



