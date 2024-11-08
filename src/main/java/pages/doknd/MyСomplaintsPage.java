package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class MyСomplaintsPage {
    private SelenideElement complaintRegisteredText = $x("//p[contains(text(), 'Жалоба зарегистрирована в ведомстве')]");
    private SelenideElement decisionAppealText = $x("//p[contains(text(), 'Решение')]");
    private SelenideElement additionalInformationRequestText = $x("//p[contains(text(), 'Ведомство запрашивает дополнительную информацию')]");

    private AppConfig config = ConfigFactory.create(AppConfig.class);

    public MyСomplaintsPage openMyСomplaintsPage() {
        open(config.kndAppealsAllUrlUat());
        return this;
    }

    public MyСomplaintsPage clickRegisteredComplaintText() {
        complaintRegisteredText.shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickAppealDecisionText() {
        decisionAppealText.shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickRequestAdditionalInformationText() {
        additionalInformationRequestText.shouldBe(visible).click();
        return this;
    }


}
