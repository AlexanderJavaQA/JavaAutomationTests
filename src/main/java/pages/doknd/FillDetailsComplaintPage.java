package pages.doknd;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.Data;
import lombok.SneakyThrows;
import apimodels.erknm.SurveillanceItemsList;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static api.ErknmInspectionSortService.getErknmInspectionsSort;
import static api.WarningInspectionsService.getWarningInspectionsService;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Data
public class FillDetailsComplaintPage {

    // Баннер с дополнительной информацией перед отправкой жалобы
    private SelenideElement bannerInformerDetails = $x("//knd-banner-informer//div[3]");

    // Поле выбора типа надзора в выпадающем списке
    private SelenideElement dropdownSelectSupervisionType = $x("//lib-dropdown[@id='kind-of-control-dropdown']//input[@role='combobox']");

    // Выпадающий список выбора типа контроля
    private SelenideElement dropdownChooseControlType = $x("//lib-dropdown[@id='type-of-control-dropdown']//a[@class='dropdown-arrow']");

    // Список доступных типов контроля
    private ElementsCollection controlTypeList = $$x("//span[@class='dropdown-item-text ng-star-inserted']");

    // Выпадающий список выбора объекта контроля
    private SelenideElement dropdownSelectControlObject = $x("//div[@class='dropdown dropdown-height-max-content']//a[@class='dropdown-arrow']");

    // Список номеров объектов контроля
    private ElementsCollection controlObjectNumberList = $$x("//div[@class='object-control-text-number grey']");

    // Опция выбора типа надзора "Федерал"
    private SelenideElement supervisionTypeOption = $x("//span[contains(text(),'Федерал')]");

    // Выпадающий список для выбора нарушений
    private SelenideElement dropdownViolationsList = $x("//div[@class='dropdown-field multi']//input[@role='combobox']");

    // Выпадающий список выбора причины несогласия по категории риска
    private SelenideElement dropdownDisagreementReasonRiskCategory = $x("//lib-dropdown[@id='type-of-control-reason-dropdown']//a[@class='dropdown-arrow']");

    // Список чекбоксов для выбора нарушений
    private ElementsCollection violationsCheckboxList = $$x("//div[@class='dropdown-item-checkbox-wrapper ng-star-inserted']");

    // Текстовое поле для ввода описания жалобы
    private SelenideElement textareaComplaintDescription = $x("//textarea[@placeholder='Описание жалобы']");

    // Текстовое поле для указания причины изменения объекта контроля
    private SelenideElement textareaChangeControlObjectReasons = $x("//textarea[@id='type-of-control-description-input']");

    // Текстовое поле для указания описания исполнения заявки
    private SelenideElement textareaApplicationExecutionDescription = $x("//textarea[@id='appeal-comment-input']");

    // Выпадающий список для выбора типа электронной подписи
    private SelenideElement dropdownElectronicSignatureType = $x("//lib-dropdown[@id='signature-type-dropdown']//a[@class='dropdown-arrow']");

    // Опция для выбора усиленной квалифицированной электронной подписи (УКЭП)
    private SelenideElement signatureUKEP = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись')]");

    // Опция для выбора усиленной квалифицированной электронной подписи для госорганов (УКЭП ГК)
    private SelenideElement signatureUKEPGK = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись Го')]");

    // Опция для выбора усиленной неквалифицированной электронной подписи (УНЭП)
    private SelenideElement signatureUNEP = $x("//span[contains(text(),'Усиленная неквалифицированная электронная подпись ')]");

    // XPath для пунктов выпадающего списка причины обжалования Объектов контроля
    // Опция: "Не согласен с присвоенной категорией риска или классом опасности"
    private SelenideElement disagreementOption = $x("//div[@role='option' and @itemid='0']//span[text()='Не согласен с присвоенной категорией риска или классом опасности']");

    // Опция: "Неверные сведения об объекте контроля"
    private SelenideElement incorrectInfoOption = $x("//div[@role='option' and @itemid='1']//span[text()='Неверные сведения об объекте контроля']");

    // Опция: "Иное"
    private SelenideElement otherOption = $x("//div[@role='option' and @itemid='2']//span[text()='Иное']");


    // Радиокнопка "Нет" для паузы исполнения
    private SelenideElement radioPauseExecutionNo = $x("//label[@for='app-radio-2']//div[@class='radio-button']");

    // Радиокнопка "Нет" для продления срока исполнения
    private SelenideElement radioRestoreDeadlineExtensionNo = $x("//label[@for='app-radio-4']");

    // Кнопка для перехода к следующему шагу в форме
    private SelenideElement buttonContinueForm = $x("//button[@class='md button font- ng-star-inserted']");

    // Поле для загрузки файла
    private SelenideElement inputFileUpload = $x("//input[@class='input']");

    // Поле ввода для данных управления контрольным органом
    private SelenideElement inputControlAgencyDepartment = $x("//div[@class='step-content']//lib-plain-input[@class='ng-untouched ng-pristine']//input[@type='text']");

    // Поле для ввода номера проверки
    private SelenideElement inputCheckNumber = $x("//div[@class='search-field magnifying-glass-or-throbber-shown lookup-input undefined']");

    // Текст для приостановки исполнения обжалуемого решения
    private SelenideElement textSuspendExecutionContestedDecision = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-untouched ng-pristine ng-invalid']/knd-form-step[6]/div[1]");

    // Чекбокс "Да" для приостановки исполнения
    private SelenideElement checkboxSuspendExecutionYes = $x("//label[@for='app-radio-9']//div[@class='radio-button']");

    // Чекбокс "Нет" для приостановки исполнения
    private SelenideElement checkboxSuspendExecutionNo = $x("//label[@for='app-radio-10']//div[@class='radio-button']");

    // Текст для восстановления срока подачи жалобы
    private SelenideElement textRestoreComplaintSubmissionDeadline = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-untouched ng-pristine ng-invalid']/knd-form-step[7]/div[1]");

    // Чекбокс "Да" для восстановления подачи жалобы
    private SelenideElement checkboxRestoreSubmissionYes = $x("//label[@for='app-radio-11']//div[@class='radio-button']");

    // Чекбокс "Нет" для восстановления подачи жалобы
    private SelenideElement checkboxRestoreSubmissionNo = $x("//label[@for='app-radio-12']//div[@class='radio-button']");

    // Текстовый блок с деталями моратория
    private SelenideElement textMoratoriumDetails = $x("//div[@class='main']");

    // Поле для ввода номера проверки при нарушении моратория
    private SelenideElement fieldEnterCheckNumber = $x("//span[@class='selected-value-text ng-star-inserted'][contains(text(),'Жалоба на нарушение моратория на проверки')]");

    // Текст для отображения регионального отделения контрольного органа
    private SelenideElement textControlAgencyRegionDivision = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-invalid ng-touched ng-dirty']/knd-form-step[3]/div[1]");

    // Текстовый элемент для отображения региона контрольного органа
    private SelenideElement textRegionControlAgency = $x("//p[contains(text(),'Регион')]");

    // Текстовый элемент для отображения отделения контрольного органа
    private SelenideElement textBranchControlAgency = $x("//p[contains(text(),'Отделение')]");

    // Модальное окно с опцией "Начать заново" для сохраненных черновиков
    private SelenideElement modalSavedDraftsRestart = $x("//span[contains(text(),'Начать заново')]");

    // Текст для отображения неверной информации об объекте контроля
    private SelenideElement textIncorrectControlObjectInfo = $x("//span[contains(text(),'Неверные сведения об объекте контроля')]");

    // Коллекция полей ввода данных контрольного органа для профвизита
    private ElementsCollection inputControlAuthorityDataProfvizit = $$x("//input[@class='text-input ng-untouched ng-pristine ng-star-inserted']");

    // Элемент для отображения информации о PDF-файле
    private SelenideElement spanFilePdfInfo = $x(".//span[contains(@class, 'file-info') and text()='pdf']");

    SigningComplaintPage signingComplaintPage = new SigningComplaintPage();
    ComplaintProgressPage complaintProgressPage = new ComplaintProgressPage();

    private String accTValueUl;
    private String accTValueFl;
    private String accTValueIp;
    private boolean isSetupExecuted = false;
    private List<SurveillanceItemsList> erknmInspectionsList;
    private List<SurveillanceItemsList> pmInspectionsList;
    private List<SurveillanceItemsList> warningKnmInspectionsList;


    public FillDetailsComplaintPage fetchKnmInspectionsSorted(String accountValueType) {
        erknmInspectionsList = getErknmInspectionsSort(accountValueType, 10, "desc", "KNM").getList();
        return this;
    }

    public FillDetailsComplaintPage fetchWarningKnmInspections(String accountValueType) {
        warningKnmInspectionsList = getWarningInspectionsService(accountValueType, 10, "desc", "23").getList();
        return this;
    }

    public FillDetailsComplaintPage fetchPmInspectionsSorted(String accountValueType) {
        pmInspectionsList = getErknmInspectionsSort(accountValueType, 10, "desc", "PM").getList();
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage searchAndSubmitPmInspectionNumber(String erknmId) {
        List<String> pmInspectionId = pmInspectionsList.stream().filter(x -> x.getErknmId().equals(erknmId)).map(SurveillanceItemsList::getErknmId).collect(Collectors.toList());
        for (String number : pmInspectionId) {
            try {
                actions().click().sendKeys(inputCheckNumber, number).perform();

                $x("//span[@class='highlighted' and text()='" + number + "']").shouldBe(visible, Duration.ofSeconds(5)).click();
                break;
            } catch (ElementNotFound e) {
                System.out.println("Элемент " + number + " не найден");
            }
            System.out.println(number);
        }

        return this;
    }


    @SneakyThrows
    public FillDetailsComplaintPage searchAndSubmitKNMInspectionNumber() {
        List<String> erknmIdList = erknmInspectionsList.stream().map(SurveillanceItemsList::getErknmId).collect(Collectors.toList());
        for (String number : erknmIdList) {
            try {
                actions().click().sendKeys(inputCheckNumber, number).perform();

                $x("//span[@class='highlighted' and text()='" + number + "']").shouldBe(visible, Duration.ofSeconds(5)).click();
                break;
            } catch (ElementNotFound e) {
                System.out.println("Элемент " + number + " не найден");
            }
            System.out.println(number);
        }

        return this;
    }

    public FillDetailsComplaintPage searchAndSubmitWarningKNMInspectionNumber() {
        List<String> erknmIdList = warningKnmInspectionsList.stream().map(SurveillanceItemsList::getErknmId).collect(Collectors.toList());
        for (String number : erknmIdList) {
            try {
                actions().click().sendKeys(inputCheckNumber, number).perform();

                $x("//span[@class='highlighted' and text()='" + number + "']").shouldBe(visible, Duration.ofSeconds(5)).click();
                break;
            } catch (ElementNotFound e) {
                System.out.println("Элемент " + number + " не найден");
            }
            System.out.println(number);
        }
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage clickRestartInSavedDraftsModal() {
        try {
            modalSavedDraftsRestart.shouldBe(enabled, Duration.ofSeconds(8)).click();
        } catch (ElementNotFound e) {
            System.err.println("Ошибка при попытке кликнуть на 'Начать заново': " + e.getMessage());
        }
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToInformerBanner() {
        bannerInformerDetails.scrollIntoView(true);
        Thread.sleep(300);
        return this;
    }

    public FillDetailsComplaintPage clickControlTypeDropdown() {
        dropdownChooseControlType.shouldBe(enabled).click();
        return this;
    }

    public FillDetailsComplaintPage clickControlTypeList() {
        controlTypeList.get(1).shouldBe(enabled).click();
        return this;
    }

    public FillDetailsComplaintPage clickControlObjectDropdown() {
        dropdownSelectControlObject.shouldBe(enabled).shouldBe(visible).click();
        return this;
    }

    public FillDetailsComplaintPage clickOnControlObjectNumber() {
        controlObjectNumberList.get(0).shouldBe(enabled).shouldBe(visible).click();
        return this;
    }


    @SneakyThrows
    public FillDetailsComplaintPage scrollToSupervisionTypeDropdown() {
        dropdownSelectSupervisionType.scrollIntoView(true);
        Thread.sleep(300);
        return this;
    }

    public FillDetailsComplaintPage clickSupervisionTypeDropdown() {
        dropdownSelectSupervisionType.shouldBe(enabled).click();
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToRiskCategoryDropdown() {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", dropdownSelectSupervisionType);
        Thread.sleep(300);
        return this;
    }

    public FillDetailsComplaintPage clickRiskCategoryDropdown() {
        dropdownSelectSupervisionType.shouldBe(enabled).click();
        return this;
    }


    public FillDetailsComplaintPage clickSupervisionType() {
        supervisionTypeOption.shouldBe(enabled).click();
        return this;
    }

    public FillDetailsComplaintPage clickDisagreementOption() {
        disagreementOption.shouldBe(visible, enabled).click();
        return this;
    }

    public FillDetailsComplaintPage clickIncorrectInfoOption() {
        incorrectInfoOption.shouldBe(visible, enabled).click();
        return this;
    }

    public FillDetailsComplaintPage clickOtherOption() {
        otherOption.shouldBe(visible, enabled).click();
        return this;
    }


    public FillDetailsComplaintPage handleRiskCategoriesDetails(String selectSignature) {
        scrollToInformerBanner();
        clickControlTypeDropdown();
        clickControlType(1);
        clickControlObjectDropdown();
        clickControlObject(0);
        scrollToCenterAndClick(dropdownDisagreementReasonRiskCategory);
        clickDisagreementOption();
        scrollToCenterAndSetValue(textareaChangeControlObjectReasons);
        handleSignatureRiskCategories(selectSignature);
        continueAndSubmit(selectSignature);

        return this;
    }


    private void clickControlType(int index) {
        controlTypeList.get(index).shouldBe(enabled).click();
    }

    private void clickControlObject(int index) {
        controlObjectNumberList.get(index).shouldBe(enabled).shouldBe(visible).click();
    }

    public void handleSignatureRiskCategories(String selectSignature) {

        switch (selectSignature) {
            case "UKEP":
                dropdownElectronicSignatureType.click();
                        clickSignatureUKEP();
                break;

            case "UKEPGK":
                dropdownElectronicSignatureType.click();
                clickSignatureUKEPGK();
                break;

            case "UNEP":
                dropdownElectronicSignatureType.click();
                clickSignatureUNEP();
                break;
        }
    }

    private void continueAndSubmit(String selectSignature) {
        buttonContinueForm.click();
        switch (selectSignature) {
            case "UKEP":

                signingComplaintPage.scrollInputAttachSignatureFile()
                        .uploadSigFile()
                        .verifySigFileIsAttached()
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails();
                complaintProgressPage.fetchOrderId();

                break;

            case "UNEP":
            case "UKEPGK":

                signingComplaintPage
                        .scrollButtonSend()
                        .clickButtonSend()
                        .clickLinkComplaintDetails();
                complaintProgressPage.fetchOrderId();
                break;

            case "PEP":

                signingComplaintPage
                        .clickButtonSignAndSend()
                        .clickLinkComplaintDetails();
                complaintProgressPage.fetchOrderId();
                break;

        }

    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndClickViolationsDropdown() {
        scrollToElement(dropdownViolationsList);
        dropdownViolationsList.click();
        return this;
    }

    public FillDetailsComplaintPage clickFirstViolationCheckbox() {
        clickViolationCheckbox(0);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndClick(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(300); // Небольшая задержка для корректного отображения элемента после скролла
        element.click();

        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndSetValue(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(300);
        element.setValue("Тестовые данные");

        return this;
    }

    @SneakyThrows
    public void scrollToElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        Thread.sleep(300);
    }

    private void clickViolationCheckbox(int index) {
        violationsCheckboxList.get(index).click();
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndSetValueComplaintDescription() {
        scrollToCenterAndSetValue(textareaComplaintDescription);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndSetValueApplicationDescription() {
        scrollToCenterAndSetValue(textareaApplicationExecutionDescription);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndSetValueChangeControlObjectReasons() {
        scrollToCenterAndSetValue(textareaChangeControlObjectReasons);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndClickElectronicSignatureDropdown() {
        dropdownElectronicSignatureType.shouldBe(visible).click();
        return this;
    }

    public FillDetailsComplaintPage clickSignatureUKEP() {
        signatureUKEP.click();
        return this;
    }

    public FillDetailsComplaintPage clickSignatureUKEPGK() {
        signatureUKEPGK.click();
        return this;
    }

    public FillDetailsComplaintPage clickSignatureUNEP() {
        signatureUNEP.click();
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndClickPauseExecutionNo() {
        scrollToCenterAndClick(radioPauseExecutionNo);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToCenterAndClickRestoreExtensionNo() {
        radioRestoreDeadlineExtensionNo.click();
        return this;
    }

    @SneakyThrows
    public SigningComplaintPage scrollToCenterAndClickContinueButton() {
        scrollToCenterAndClick(buttonContinueForm);
        return new SigningComplaintPage();
    }

}



