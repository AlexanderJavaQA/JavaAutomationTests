package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.aeonbits.owner.ConfigFactory.create;

@Data
public class ElasticPage {

    //Поле ввода на странице Кубера
    private final SelenideElement queryInputField = $x("//textarea[@data-test-subj='queryInput']");

    // Кнопка "Обновить" на дашборде в Elastic
    private SelenideElement updateButton = $x("//button[@class='euiButton euiButton--success euiButton--fill euiSuperUpdateButton']//span[@class='euiButtonContent euiButton__content']//*[name()='svg']");

    // Ячейка, отображающая найденный Correlation ID на дашборде Elastic
    private SelenideElement correlationIdCell = $x("//td[@class='kbnDocTableCell__dataField eui-textBreakAll eui-textBreakWord']");

    AppConfig config = create(AppConfig.class);

    private String newCorrelationId;
    private String newSmevMessageId;

    public ElasticPage setOrderIdInQueryInput(String orderId) {
        queryInputField.sendKeys(orderId);
        return this;
    }

    public ElasticPage clickUpdateButton() {
        updateButton.shouldBe(enabled).click();
        return this;
    }

    public String getKuberCorrelationId() {
        System.out.println("Correlation ID: " + (newCorrelationId = correlationIdCell.shouldBe(visible).text()));
        return newCorrelationId;
    }

    public void switchToNewTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public ElasticPage openElasticInNewTabUat() {
        switchToNewTab();
        open(config.kuberDiscoverUrlUat());
        return this;
    }

    public ElasticPage openElasticInNewTabDev2() {
        switchToNewTab();
        open(config.kuberDiscoverUrlDev2());
        return this;
    }

    public String getSmevMessageIdByCorrelation() {
        newSmevMessageId = String.valueOf(
                new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat())
                        .findMessageIdsByCorrelation(newCorrelationId).get(0));

        System.out.println("SMEV Message ID: " + newSmevMessageId);
        return newSmevMessageId;
    }

}
