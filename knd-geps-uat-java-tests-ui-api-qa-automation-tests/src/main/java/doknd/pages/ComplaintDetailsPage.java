package doknd.pages;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import springjdbc.oracle.DataAccessObjectOracle;
import springjdbc.oracle.DatabaseConnectOracle;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.aeonbits.owner.ConfigFactory.create;

@Data
@Getter
public class ComplaintDetailsPage {
    private SelenideElement orderHeader = $x("//h3[@class='title-h3 gray mb-20 ng-star-inserted']");
    private SelenideElement queryTextArea = $x("//textarea[@data-test-subj='queryInput']");
    private SelenideElement updateButtonIcon = $x("//button[@class='euiButton euiButton--success euiButton--fill euiSuperUpdateButton']//span[@class='euiButtonContent euiButton__content']//*[name()='svg']");
    private SelenideElement dataFieldCell = $x("//td[@class='kbnDocTableCell__dataField eui-textBreakAll eui-textBreakWord']");
    private SelenideElement documentIdPreformatted = $x("//div[17]/pre");
    private ElementsCollection complaintWithdrawalButtons = $$x("//*[contains(text(), 'Отозвать жалобу')]");
    private ElementsCollection submitAdditionalDocumentsButtons = $$x("//*[contains(text(), 'Направить дополнительные документы')]");
    private ElementsCollection provideAdditionalInfoButtons = $$x("//div//span[contains(text(),'Перейти')]");

    private String newOrderId;
    private String correlationId;
    private String oldWindow;
    private String requestId;
    private String newCorrelationId;
    private String newSmevMessageId;

    AppConfig config = create(AppConfig.class);


    public String getOrderIdFromOrderHeader() {
        System.out.println("New Order ID: " + (newOrderId = orderHeader.text().substring(12)));
        return newOrderId;
    }

    public String getCorrelationIdForOrder() {
        correlationId = String.valueOf(
                new DataAccessObjectOracle(DatabaseConnectOracle.oracleDatabaseUat())
                        .findOrderCorrelationIdsByOgrn(newOrderId)
        );
        return correlationId;
    }

    public String storeAndGetCurrentWindowHandle() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        oldWindow = driver.getWindowHandle();
        return oldWindow;
    }

    public String getFormattedSmevMessageIdByCorrelation() {
        String smevMessageID = String.valueOf(
                        new DataAccessObjectPostgres(DatabaseConnectPostgres
                                .smevaDatabaseUat())
                                .findMessageIdsByCorrelation(requestId))
                                .replaceAll("[\\[\\]]", "");
        return smevMessageID;
    }

    public void switchToNewTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void openSmevAdapterById(String smevMessageId) {
        String smevMessageUrl = config.smevAdapterUrlUat() + smevMessageId;
        open(smevMessageUrl);
    }

    public String openSmevMessageInNewTab(String smevMessageId) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String originalWindowHandle = driver.getWindowHandle();

        // Открыть новую вкладку
        switchToNewTab();

        // Открыть сообщение СМЭВ по идентификатору
        openSmevAdapterById(smevMessageId);

        return originalWindowHandle;
    }

    public String getNewWindowHandleAfterSwitch() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String newWindowHandle = driver.getWindowHandles().stream()
                .filter(s -> !oldWindow.equals(s))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("New window not found"));
        return newWindowHandle;
    }


    public ComplaintDetailsPage openKuberDiscoverPageInNewTabUat() {
        switchToNewTab();
        open(config.kuberDiscoverUrlUat());
        return this;
    }

    public ComplaintDetailsPage openKuberDiscoverPageInNewTabDev2() {
        switchToNewTab();
        open(config.kuberDiscoverUrlDev2());
        return this;
    }

    public ComplaintDetailsPage enterKuberOrderId() {
        queryTextArea.sendKeys(newOrderId);
        return this;
    }

    public ComplaintWithdrawalPage clickComplaintWithdrawalButton() {
        complaintWithdrawalButtons.get(1).shouldBe(visible).click();
        return new ComplaintWithdrawalPage();
    }

    public SubmitAdditionalDocumentsPage clickSubmitAdditionalDocumentsButton() {
        submitAdditionalDocumentsButtons.get(1).shouldBe(visible).click();
        return new SubmitAdditionalDocumentsPage();
    }

    public ComplaintDetailsPage clickProvideAdditionalInfoButton() {
        provideAdditionalInfoButtons.get(1).shouldBe(visible).click();
        return this;
    }

    public ComplaintDetailsPage clickUpdateButton() {
        updateButtonIcon.shouldBe(enabled).click();
        return this;
    }

    public String getKuberCorrelationId() {
        System.out.println("Correlation ID: " + (newCorrelationId = dataFieldCell.text()));
        return newCorrelationId;
    }

    public String getKuberSmevMessageIdUAT() {
        newSmevMessageId = String.valueOf(
                new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat())
                        .findMessageIdsByCorrelation(newCorrelationId)
        ).replaceAll("[\\[\\]]", "");
        System.out.println("Smev Message ID (UAT): " + newSmevMessageId);
        return newSmevMessageId;
    }

    public ComplaintDetailsPage closeCurrentWindow() {
        WebDriverRunner.getWebDriver().close();
        return this;
    }

}
