package doknd.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

public class WaitingDecisionPage{

    private SelenideElement linkComplaintDetails = $x("//a[contains(text(),'на странице деталей')]");

    public ComplaintDetailsPage clickLinkComplaintDetails() {
        linkComplaintDetails.shouldBe(enabled).click();
        return new ComplaintDetailsPage();
    }


}
