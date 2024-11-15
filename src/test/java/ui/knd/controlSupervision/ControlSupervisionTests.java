package ui.knd.controlSupervision;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.*;
import pages.doknd.LoginPage;
import pages.knd.ControlSupervisionPage;
import static pages.knd.ControlSupervisionPage.performNavigationTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка кнопок и элементов страницы Контроль и Надзор")
public class ControlSupervisionTests extends BaseTestSelenide {

    @Test
    @Order(1)
    @DisplayName("Авторизация на портале КНД под учетной записью ЮЛ")
    public void loginWithULAccount() {
        loginPage.login(
                config.pguOrgProfileKndUrlUat(),
                config.userLoginBespalov(),
                config.userPasswordBespalov(),
                LoginPage.AccountType.UL
        );
    }

    @Test
    @Order(2)
    @DisplayName("Проверка перехода на старницу Контрольные и профилактические мероприятия")
    public void performGoToSectionControlAndPreventionTest() {
        controlSupervisionPage.openControlSupervisionPage()
                .waitForAndClickGoToSectionControlAndPrevention()
                .verifyCurrentUrl("https://pgu-uat-betalk.test.gosuslugi.ru/org-profile/knd/inspect");
    }


    @Test
    @Order(3)
    @DisplayName("Проверка перехода на старницу Объекты контроля")
    public void performGoToSectiosnControlAndPreventionTest() {
        controlSupervisionPage.openControlSupervisionPage()
                .clickAndWaitForControlObjectsSection()
                .verifyCurrentUrl("https://pgu-uat-betalk.test.gosuslugi.ru/org-profile/knd/control-objects");
    }

    @Test
    @DisplayName("Переход в раздел Уведомления по объектам предпринимательской деятельности")
    public void performGoToBusinessNotificationsSectionTest() {
        controlSupervisionPage.openControlSupervisionPage()
                .clickAndWaitForBusinessNotificationsSection()
                .verifyCurrentUrl("https://pgu-uat-betalk.test.gosuslugi.ru/org-profile/knd/orgactivity");
    }


    @Test
    @DisplayName("Переход в реестр обязательных требований")
    public void performGoToRegistryLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForRegistryLink,
                "https://ot.gov.ru/"
        );
    }

    @Test
    @Order(3)
    @DisplayName("Переход к подаче возражения на предостережение")
    public void performGoToSubmitObjectionLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForSubmitObjectionLink,
                "https://knd-uat.test.gosuslugi.ru/form/appeal?subjectId=15"
        );
    }

    @Test
    @DisplayName("Переход к отправке уведомления о начале предпринимательской деятельности")
    public void performGoToSendNotificationLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForSendNotificationLink,
                "https://pgu-uat-fed.test.gosuslugi.ru/630885/1/form?situationId=1"
        );
    }

    @Test
    @DisplayName("Переход к подаче жалобы на решение")
    public void performGoToSubmitComplaintLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForSubmitComplaintLink,
                "https://knd-uat.test.gosuslugi.ru/"
        );
    }

    @Test
    @DisplayName("Переход к подаче записи на профвизит")
    public void performGoToApplicationForVisitLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForApplicationForVisitLink,
                "https://pgu-uat-fed.test.gosuslugi.ru/626705/1/form"
        );
    }

    @Test
    @DisplayName("Переход к подаче заявки на консультацию инспектора")
    public void performGoToApplicationForConsultationLinkTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForApplicationForConsultationLink,
                "https://pgu-uat-fed.test.gosuslugi.ru/625710/1/form"
        );
    }

    @Test
    @DisplayName("Переход к скачиванию мобильного приложения")
    public void performGoToDownloadAppButtonTest() {
        performNavigationTest(
                ControlSupervisionPage::clickAndWaitForDownloadAppButton,
                "https://knd.gov.ru/document/mp"
        );
    }

    @Test
    @DisplayName("Проверка открытия и валидации всех ссылок типов контроля")
    public void clickAndValidateAllControlTypeLinksTest() {
        controlSupervisionPage
                .getAccountTokenValue()
                .validateTypesOfControlExistence()
                .clickAndValidateAllControlTypeLinks();
    }
}
