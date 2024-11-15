package pages.doknd;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

public class WaitingDecisionPage{

    // Ссылка для перехода на страницу деталей жалобы
    private SelenideElement linkComplaintDetails = $x("//a[contains(text(),'на странице деталей')]");

    public ComplaintProgressPage clickLinkComplaintDetails() {
        linkComplaintDetails.shouldBe(enabled).click();
        return new ComplaintProgressPage();
    }


}
