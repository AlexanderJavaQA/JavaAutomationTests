package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MyСomplaintsPage {

    // Текст, подтверждающий, что жалоба зарегистрирована в ведомстве
    private SelenideElement registeredComplaintText = $x("//p[contains(text(), 'Жалоба зарегистрирована в ведомстве')]");

    // Текст, указывающий на наличие решения по жалобе
    private SelenideElement complaintDecisionText = $x("//p[contains(text(), 'Решение')]");

    // Текст, указывающий, что ведомство запрашивает дополнительную информацию
    private SelenideElement additionalInfoRequestText = $x("//p[contains(text(), 'Ведомство запрашивает дополнительную информацию')]");

    private AppConfig config = ConfigFactory.create(AppConfig.class);

    public MyСomplaintsPage openMyСomplaintsPage() {
        open(config.kndAppealsAllUrlUat());
        return this;
    }

    public MyСomplaintsPage clickRegisteredComplaint() {
        registeredComplaintText.shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickComplaintDecision() {
        complaintDecisionText.shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickRequestAdditionalInformation() {
        additionalInfoRequestText.shouldBe(visible).click();
        return this;
    }


}
