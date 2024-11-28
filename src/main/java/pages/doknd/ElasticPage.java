package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;

import java.util.Optional;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.aeonbits.owner.ConfigFactory.create;

@Data
public class ElasticPage {

    //Поле ввода на странице Кубера
    private final SelenideElement queryInputField = $x("//textarea[@data-test-subj='queryInput']");

    // Кнопка "Обновить" на дашборде в Elastic
    private SelenideElement updateButton = $x("//button[@class='euiButton euiButton--success euiButton--fill euiSuperUpdateButton']//span[@class='euiButtonContent euiButton__content']//*[name()='svg']");

    // Ячейка, отображающая найденный Correlation ID на дашборде Elastic
    private ElementsCollection correlationIdCells = $$x("//td[@class='kbnDocTableCell__dataField eui-textBreakAll eui-textBreakWord']");

    AppConfig config = create(AppConfig.class);

    private String сorrelationId;
    private String newSmevMessageId;

    public ElasticPage setOrderIdInQueryInput(String orderId) {
        queryInputField.sendKeys(orderId);
        return this;
    }

    public ElasticPage clickUpdateButton() {
        updateButton.shouldBe(enabled).click();
        return this;
    }

    public String getValidKuberCorrelationId() {
        correlationIdCells.get(0).shouldBe(visible);
        сorrelationId = correlationIdCells.stream()
                .map(cell -> cell.shouldBe(visible).text())
                .peek(cell -> {
                    if ("-".equals(cell)) {
                        System.out.println("Found invalid Correlation ID: " + cell);
                    }
                })
                .filter(correlationId -> correlationId != null && !correlationId.equals("-")) // Фильтруем валидные значения
                .findFirst() // Находим первое подходящее значение
                .orElseThrow(() -> new IllegalStateException("Valid Correlation ID not found!")); // Если ничего не найдено, выбрасываем исключение

        return сorrelationId;
    }

    public void switchToNewTab() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public ElasticPage openElasticInNewTabUat() {
        switchToNewTab();
        open(config.kibanaDiscoverPage());
        return this;
    }

    public String getSmevMessageIdByCorrelation() {
        newSmevMessageId = String.valueOf(
                new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat())
                        .findMessageIdsByCorrelation(сorrelationId).get(0));

        System.out.println("SMEV Message ID: " + newSmevMessageId);
        return newSmevMessageId;
    }
}
