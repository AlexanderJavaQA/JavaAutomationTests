package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MyСomplaintsPage {

    // Текст, подтверждающий, что жалоба зарегистрирована в ведомстве
    private ElementsCollection registeredComplaintText = $$x("//div[contains(text(), 'Жалоба зарегистрирована в ведомстве')]");

    // Текст, указывающий на наличие решения по жалобе
    private ElementsCollection complaintDecisionText = $$x("//div[contains(text(), 'Решение')]");

    // Текст, указывающий, что ведомство запрашивает дополнительную информацию
    private ElementsCollection additionalInfoRequestText = $$x("//div[contains(text(), 'Ведомство запрашивает дополнительную информацию')]");

    private AppConfig config = ConfigFactory.create(AppConfig.class);

    public MyСomplaintsPage openMyСomplaintsPage() {
        open(config.myAppealsPage());
        return this;
    }

    public MyСomplaintsPage openMyСomplaintsPageDev2() {
        open(config.myAppealsPageDev2());
        refresh();
        return this;
    }

    public MyСomplaintsPage clickRegisteredComplaint() {
        registeredComplaintText.get(1).shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickComplaintDecision() {
        complaintDecisionText.get(1).shouldBe(visible).click();
        return this;
    }

    public MyСomplaintsPage clickRequestAdditionalInformation() {
        additionalInfoRequestText.get(1).shouldBe(visible).click();
        return this;
    }


}
