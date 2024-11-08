package screenshots;

import appconfig.AppConfig;
import com.codeborne.selenide.*;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static screenshots.DisplayNoneElement.displayNoneElements;

@DisplayName("Проверка верстки личного кабинета и формы досудебного обжалования контрольно-надзорной деятельности")
public class ScreenshotsTests extends BaseScreenshotTest {
    private SelenideElement kndHeader = $x("//div[@class='header ng-tns-c608642765-1 ng-star-inserted']");
    private SelenideElement doKndMainHeader = $x("//div[@class='container flex-container justify-between']");
    private SelenideElement kndMainHeader = $x("//header[contains(@class, 'main-header top-position ng-star-inserted')]");
    private SelenideElement doKndSiteLogoHeader = $x("//div[@class='site-logo-container']");
    private SelenideElement erpInspectionsButton = $x("//span[contains(text(), 'Eдиный реестр проверок (294-ФЗ)')]");
    private SelenideElement stepsTimerow = $x("//div[contains(@class, 'steps-timerow-wrapper')]//div[contains(@class, 'steps-timerow')]");
    private ElementsCollection closeButtons = $$x("//div[contains(@class, 'container')]//div[contains(@class, 'close')]");
    private AppConfig config = ConfigFactory.create(AppConfig.class);
    private static final String DESKTOP_RESOLUTION = "1920x1024";
    private static final String MOBILE_RESOLUTION = "414x900";

    public void loginWithDoKnd() {
        loginPage.loginToKndPortal(
                config.doKndAppealFormUrlUat(),
                config.userLoginEmelyanov(),
                config.userPasswordBespalov(),
                loginPage.getEmelianovIPAuth());
    }

    public void loginWithDoKndFl() {
        loginPage.loginToKndPortal(
                config.pguOrgProfileInspectUrlUat(),
                config.userLoginEmelyanov(),
                config.userPasswordBespalov(),
                loginPage.getEmelianovFLAuth());
    }

    private void setupTest(String browserSize, String url) {
        Configuration.browserSize = browserSize;
        loginWithDoKnd();
        if (url != null) {
            open(url);
        }
    }

    private void setupTestFL(String browserSize, String url) {
        Configuration.browserSize = browserSize;
        loginWithDoKndFl();
        if (url != null) {
            open(url);
        }
    }

    private void performLayoutCheck(SelenideElement header, SelenideElement mainHeader, boolean clickCloseButtons) throws InterruptedException {
        displayNoneElements(header);
        if (clickCloseButtons) {
            closeButtons.get(1).shouldBe(enabled).click();
            closeButtons.get(2).shouldBe(enabled).click();
        }
        displayNoneElements(mainHeader);
        Thread.sleep(1500);
        assertFullScreen();
    }

    private void performLayoutCheck(SelenideElement header, SelenideElement mainHeader, SelenideElement mainHeaders, boolean clickCloseButtons) throws InterruptedException {
        displayNoneElements(header);
        if (clickCloseButtons) {
            closeButtons.get(1).shouldBe(enabled).click();
            closeButtons.get(2).shouldBe(enabled).click();
        }
        displayNoneElements(mainHeader);
        displayNoneElements(mainHeaders);

        Thread.sleep(2500);
        assertFullScreen();
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контроль и надзор в Десктопной версии")
    public void verifyControlAndSupervisionPageLayoutDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.pguOrgProfileKndUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, true);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контроль и надзор в мобильной версии")
    public void verifyControlAndSupervisionPageMobile() {
        setupTest(MOBILE_RESOLUTION, config.pguOrgProfileKndUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, true);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контрольные мероприятия в мобильной версии для ЕРКНМ")
    public void verifyControlActivitiesPageMobileERKNM() {
        setupTest(MOBILE_RESOLUTION, config.pguInspectUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }


    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контрольные мероприятия в десктопной версии для ЕРКНМ")
    public void verifyControlActivitiesPageDesktopERKNM() {
        setupTest(DESKTOP_RESOLUTION, config.pguInspectUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контрольные мероприятия в десктопной версии для ЕРП")
    public void verifyControlActivitiesPageDesktopERP() {
        setupTest(DESKTOP_RESOLUTION, config.pguInspectUrlUat());
        erpInspectionsButton.shouldBe(visible).click();
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Контрольные мероприятия в мобильной версии для ЕРП")
    public void verifyControlActivitiesPageMobileERP() {
        setupTest(MOBILE_RESOLUTION, config.pguInspectUrlUat());
        erpInspectionsButton.shouldBe(visible).click();
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Категорированные объекты контроля в десктопной версии")
    public void verifyControlObjectsPageDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.pguControlObjectsUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Категорированные объекты контроля в мобильной версии")
    public void verifyControlObjectsPageMobile() {
        setupTest(MOBILE_RESOLUTION, config.pguControlObjectsUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Объекты предпринимательской деятельности в десктопной версии")
    public void verifyBusinessActivityObjectsPageDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.pguOrgActivityUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Объекты предпринимательской деятельности в мобильной версии")
    public void verifyBusinessActivityObjectsPageMobile() {
        setupTest(MOBILE_RESOLUTION, config.pguOrgActivityUrlUat());
        performLayoutCheck(kndHeader, kndMainHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Жалоба на решение контрольных органов в десктопной версии")
    public void verifyDoKNDPageDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.doKndBaseUrlUat());
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки страницы Жалоба на решение контрольных органов в мобильной версии")
    public void verifyDoKNDPageMobile() {
        setupTest(MOBILE_RESOLUTION, config.doKndBaseUrlUat());
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Нарушена процедура проведения проверки в десктопной версии")
    public void verifyCheckProcedureViolationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatCheckProcedureViolationId1());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с актом проверки в десктопной версии")
    public void verifyDisagreeWithActViolationsDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithActViolationsId2());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Нарушение процедуры назначения проверки в десктопной версии")
    public void verifyOrderOfCheckAssignmentViolationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatOrderOfCheckAssignmentViolationId3());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с действиями должностного лица в десктопной версии")
    public void verifyDisagreeWithActionsOfOfficialDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithActionsOfOfficialId4());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с принятыми мерами в десктопной версии")
    public void verifyDisagreeWithMeasuresDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithMeasuresId5());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Продление срока исполнения предписания в десктопной версии")
    public void verifyExtensionOfPrescriptionExecutionPeriodDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatExtensionOfPrescriptionExecutionPeriodId11());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с решением контрольного органа по мобилизации в десктопной версии")
    public void verifyDisagreeWithControlOrganDecisionMobilizationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithControlOrganDecisionMobilizationId13());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Жалоба на нарушение моратория в десктопной версии")
    public void verifyComplaintForBreachOfMoratoriumDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatComplaintForBreachOfMoratoriumId10());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с предписанием профпосещения в десктопной версии")
    public void verifyDisagreeWithProfvisitPrescriptionDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithProfvisitPrescriptionId14());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Возражение на предостережение в десктопной версии")
    public void verifyObjectionCautionDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatObjectionCautionId15());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки незаполненной страницы Несогласие с присвоением категории риска в десктопной версии")
    public void verifyDisagreeWithRiskCategoryAssignmentDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithRiskCategoryAssignmentId12());
        fillDetailsComplaintPage.clickSavedDraftsModal();
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Нарушена процедура проведения проверки в десктопной версии")
    public void verifyFilledCheckProcedureViolationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatCheckProcedureViolationId1());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Несогласие с актом проверки в десктопной версии")
    public void verifyFilledDisagreeWithActViolationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithActViolationsId2());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Нарушение процедуры назначения проверки в десктопной версии")
    public void verifyFilledOrderOfCheckAssignmentViolationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatOrderOfCheckAssignmentViolationId3());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Несогласие с действиями должностного лица в десктопной версии")
    public void verifyFilledDisagreeWithActionsOfOfficialDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithActionsOfOfficialId4());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Несогласие с принятыми мерами в десктопной версии")
    public void verifyFilledDisagreeWithMeasuresDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithMeasuresId5());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Продление срока исполнения предписания в десктопной версии")
    public void verifyFilledExtensionOfPrescriptionExecutionPeriodDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatExtensionOfPrescriptionExecutionPeriodId11());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(false); // Для этого ID тип нарушений false
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(false);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Несогласие с решением контрольного органа по мобилизации в десктопной версии")
    public void verifyFilledDisagreeWithControlOrganDecisionMobilizationDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithControlOrganDecisionMobilizationId13());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(true);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Жалоба на нарушение моратория в десктопной версии")
    public void verifyFilledComplaintForBreachOfMoratoriumDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatComplaintForBreachOfMoratoriumId10());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendKnmInspectionNumberLookUp();
        handleFilingComplaint.handleTypeOfViolations(false); // Для этого ID тип нарушений false
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(false); // Радио-кнопка
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);// false
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Несогласие с предписанием профпосещения в десктопной версии")
    public void verifyFilledDisagreeWithProfvisitPrescriptionDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatDisagreeWithProfvisitPrescriptionId14());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendPmInspectionNumberLookUp("772309577000038041701");
        handleFilingComplaint.handleTypeOfViolations(false);
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(true);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }

    @Test
    @SneakyThrows
    @DisplayName("Проверка верстки заполненной страницы Возражение на предостережение в десктопной версии")
    public void verifyFilledObjectionCautionDesktop() {
        setupTest(DESKTOP_RESOLUTION, config.uatObjectionCautionId15());
        fillDetailsComplaintPage.clickSavedDraftsModal()
                .scrollToInformerBanner();
        handleFilingComplaint.sendWarningKnmInspectionsList();
        handleFilingComplaint.handleTypeOfViolations(false); // Для этого ID тип нарушений false
        handleFilingComplaint.handlePlaceholderDescription(true);
        handleFilingComplaint.handleTypeOfSignature("PEP");
        handleFilingComplaint.handleSelectRadioButton(false);
        executeJavaScript("window.scrollTo(0, 0);");
        Thread.sleep(5000);
        performLayoutCheck(doKndMainHeader, doKndSiteLogoHeader, stepsTimerow, false);
    }




}