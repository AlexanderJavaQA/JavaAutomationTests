package doknd.pages;

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

    private SelenideElement bannerInformerDetails = $x("//knd-banner-informer//div[3]");
    private SelenideElement dropdownSelectSupervisionType = $x("//lib-dropdown[@id='kind-of-control-dropdown']//input[@role='combobox']");
    private SelenideElement dropdownChooseControlType = $x("//lib-dropdown[@id='type-of-control-dropdown']//a[@class='dropdown-arrow']");
    private ElementsCollection controlTypeList = $$x("//span[@class='dropdown-item-text ng-star-inserted']");
    private SelenideElement dropdownSelectControlObject = $x("//div[@class='dropdown dropdown-height-max-content']//a[@class='dropdown-arrow']");
    private ElementsCollection controlObjectNumberList = $$x("//div[@class='object-control-text-number grey']");
    private SelenideElement supervisionTypeOption = $x("//span[contains(text(),'Федерал')]");
    private SelenideElement dropdownViolationsList = $x("//div[@class='dropdown-field multi']//input[@role='combobox']");
    private SelenideElement dropdownDisagreementReasonRiskCategory = $x("//lib-dropdown[@id='type-of-control-reason-dropdown']//a[@class='dropdown-arrow']");
    private ElementsCollection violationsCheckboxList = $$x("//div[@class='dropdown-item-checkbox-wrapper ng-star-inserted']");
    private SelenideElement textareaComplaintDescription = $x("//textarea[@placeholder='Описание жалобы']");
    private SelenideElement textareaChangeControlObjectReasons = $x("//textarea[@id='type-of-control-description-input']");
    private SelenideElement textareaApplicationExecutionDescription = $x("//textarea[@id='appeal-comment-input']");
    private SelenideElement dropdownElectronicSignatureType = $x("//lib-dropdown[@id='signature-type-dropdown']//a[@class='dropdown-arrow']");
    private SelenideElement signatureUKEP = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись')]");
    private SelenideElement signatureUKEPGK = $x("//span[contains(text(),'Усиленная квалифицированная электронная подпись Го')]");
    private SelenideElement signatureUNEP = $x("//span[contains(text(),'Усиленная неквалифицированная электронная подпись ')]");
    private SelenideElement radioPauseExecutionNo = $x("//label[@for='app-radio-2']//div[@class='radio-button']");
    private SelenideElement radioRestoreDeadlineExtensionNo = $x("//label[@for='app-radio-4']");
    private SelenideElement buttonContinueForm = $x("//button[@class='md button font- ng-star-inserted']");
    private SelenideElement inputFileUpload = $x("//input[@class='input']");
    private SelenideElement inputControlAgencyDepartment = $x("//div[@class='step-content']//lib-plain-input[@class='ng-untouched ng-pristine']//input[@type='text']");
    private SelenideElement inputCheckNumber = $x("//div[@class='search-field magnifying-glass-or-throbber-shown lookup-input undefined']");
    private SelenideElement textSuspendExecutionContestedDecision = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-untouched ng-pristine ng-invalid']/knd-form-step[6]/div[1]");
    private SelenideElement checkboxSuspendExecutionYes = $x("//label[@for='app-radio-9']//div[@class='radio-button']");
    private SelenideElement checkboxSuspendExecutionNo = $x("//label[@for='app-radio-10']//div[@class='radio-button']");
    private SelenideElement textRestoreComplaintSubmissionDeadline = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-untouched ng-pristine ng-invalid']/knd-form-step[7]/div[1]");
    private SelenideElement checkboxRestoreSubmissionYes = $x("//label[@for='app-radio-11']//div[@class='radio-button']");
    private SelenideElement checkboxRestoreSubmissionNo = $x("//label[@for='app-radio-12']//div[@class='radio-button']");
    private SelenideElement textMoratoriumDetails = $x("//div[@class='main']");
    private SelenideElement fieldEnterCheckNumber = $x("//span[@class='selected-value-text ng-star-inserted'][contains(text(),'Жалоба на нарушение моратория на проверки')]");
    private SelenideElement textControlAgencyRegionDivision = $x("//body/div[@class='app-root']/div[@class='main']/div[@id='print-page']/knd-appeal-form[@class='ng-star-inserted']/knd-base-page-form[@title='Жалоба на решение контрольных органов']/div[@class='container']/div[@class='grid-row ng-star-inserted']/div[@class='col-lg-8 col-md-6 col-3 appeal-form-wrap']/div[@class='mt-40 ng-star-inserted']/form[@class='ng-invalid ng-touched ng-dirty']/knd-form-step[3]/div[1]");
    private SelenideElement textRegionControlAgency = $x("//p[contains(text(),'Регион')]");
    private SelenideElement textBranchControlAgency = $x("//p[contains(text(),'Отделение')]");
    private SelenideElement modalSavedDraftsRestart = $x("//span[contains(text(),'Начать заново')]");
    private SelenideElement textIncorrectControlObjectInfo = $x("//span[contains(text(),'Неверные сведения об объекте контроля')]");
    private ElementsCollection inputControlAuthorityDataProfvizit = $$x("//input[@class='text-input ng-untouched ng-pristine ng-star-inserted']");
    private SelenideElement spanFilePdfInfo = $x(".//span[contains(@class, 'file-info') and text()='pdf']");
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
    public FillDetailsComplaintPage lookupAndSubmitPmInspectionNumber(String erknmId) {
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
    public FillDetailsComplaintPage lookupAndSubmitKNMInspectionNumber() {
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

    public FillDetailsComplaintPage lookupAndSubmitWarningKNMInspectionNumber() {
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
    public FillDetailsComplaintPage clickSavedDraftsModal() {
        try {
            modalSavedDraftsRestart.shouldBe(enabled, Duration.ofSeconds(5)).click();
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

    public FillDetailsComplaintPage scrollAndClickControlObjectInfo() {
        scrollToElement(textIncorrectControlObjectInfo);
        textIncorrectControlObjectInfo.shouldBe(enabled).shouldBe(visible).click();
        return this;
    }


    public FillDetailsComplaintPage clickSupervisionType() {
        supervisionTypeOption.shouldBe(enabled).click();
        return this;
    }

    public FillDetailsComplaintPage handleRiskCategoriesDetails(String selectSignature) {
        scrollToInformerBanner();
        clickControlType();
        clickFirstControlObject();
        handleSignatureRiskCategories(selectSignature);
        continueAndSubmit(selectSignature);

        return this;
    }


    public void clickControlType() {
        clickControlTypeDropdown();
        clickControlType(1);
    }

    public void clickFirstControlObject() {
        clickControlObjectDropdown();
        clickControlObject(0);
    }

    private void clickControlType(int index) {
        controlTypeList.get(index).shouldBe(enabled).click();
    }

    private void clickControlObject(int index) {
        controlObjectNumberList.get(index).shouldBe(enabled).shouldBe(visible).click();
    }

    public void handleSignatureRiskCategories(String selectSignature) {
        scrollToRiskCategoryDropdown().clickRiskCategoryDropdown().scrollAndClickControlObjectInfo().scrollAndSetChangeControlObjectReasons();

        switch (selectSignature) {
            case "UKEP":
                scrollToElectronicSignature().scrollAndClickElectronicSignatureDropdown().clickSignatureUKEP();
                break;

            case "UKEPGK":
                scrollToElectronicSignature().scrollAndClickElectronicSignatureDropdown().clickSignatureUKEPGK();
                break;

            case "UNEP":
                scrollToElectronicSignature().scrollAndClickElectronicSignatureDropdown().clickSignatureUNEP();
                break;

            default:
                throw new IllegalArgumentException("Unknown signature type: " + selectSignature);
        }

    }

    private void continueAndSubmit(String selectSignature) {
        switch (selectSignature) {
            case "UKEP":
                scrollAndClickContinueButton().scrollInputAttachSignatureFile().attachSignatureFileAndVerify().scrollButtonSend().clickButtonSend().clickLinkComplaintDetails().getOldWindow();
                break;

            case "UNEP":
            case "UKEPGK":
                scrollAndClickContinueButton().scrollButtonSend().clickButtonSend().clickLinkComplaintDetails().getOldWindow();
                break;

            case "PEP":
                scrollAndClickContinueButton().clickButtonSignAndSend().clickLinkComplaintDetails().getOldWindow();
                break;

            default:
                throw new IllegalArgumentException("Unknown signature type: " + selectSignature);
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
    public void scrollToElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        Thread.sleep(300);
    }

    private void clickViolationCheckbox(int index) {
        violationsCheckboxList.get(index).click();
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndSetComplaintDescription() {
        scrollToElement(textareaComplaintDescription);
        textareaComplaintDescription.shouldBe(enabled).setValue("Тестовые данные");
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndSetApplicationDescription() {
        scrollToElement(textareaApplicationExecutionDescription);
        textareaApplicationExecutionDescription.shouldBe(enabled).setValue("Тестовые данные");
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndSetChangeControlObjectReasons() {
        scrollToElement(textareaChangeControlObjectReasons);
        textareaChangeControlObjectReasons.shouldBe(enabled).setValue("Тестовые данные");
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndClickElectronicSignatureDropdown() {
        scrollToElement(dropdownElectronicSignatureType);
        dropdownElectronicSignatureType.shouldBe(enabled).click();
        return this;
    }


    @SneakyThrows
    public FillDetailsComplaintPage scrollToElectronicSignature() {
        executeJavaScript("arguments[0].scrollIntoView(true);", dropdownElectronicSignatureType); // Скролл до элемента с помощью javaScript
        Thread.sleep(200);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToControlAgencyDepartmentField() {
        executeJavaScript("arguments[0].scrollIntoView(true);", inputControlAgencyDepartment); // Скролл до элемента с помощью javaScript
        Thread.sleep(300);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollRadioButtonPauseExecutionNo() {
        executeJavaScript("arguments[0].scrollIntoView(true);", radioPauseExecutionNo); // Скролл до элемента с помощью javaScript
        Thread.sleep(300);
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollToDisagreementReasonDropdown() {
        dropdownDisagreementReasonRiskCategory.shouldBe(visible);
        scrollToElement(dropdownDisagreementReasonRiskCategory);
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
    public FillDetailsComplaintPage scrollAndClickPauseExecutionNo() {
        scrollToElement(radioPauseExecutionNo);
        radioPauseExecutionNo.shouldBe(enabled).click();
        return this;
    }

    @SneakyThrows
    public FillDetailsComplaintPage scrollAndClickRestoreExtensionNo() {
        scrollToElement(radioRestoreDeadlineExtensionNo);
        radioRestoreDeadlineExtensionNo.shouldBe(enabled).click();
        return this;
    }

    @SneakyThrows
    public SigningComplaintPage scrollAndClickContinueButton() {
        scrollToElement(buttonContinueForm);
        buttonContinueForm.shouldBe(enabled).click();
        return new SigningComplaintPage();
    }


}



