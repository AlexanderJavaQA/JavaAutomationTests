package pages.doknd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Getter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Data
@Getter
public class ComplaintProgressPage {

    // Заголовок ордера заявления на странице
    private SelenideElement orderHeader = $x("//h3[@class='title-h3 gray mb-20 ng-star-inserted']");

    // Кнопка для отзыва жалобы
    private ElementsCollection complaintWithdrawalButtons = $$x("//*[contains(text(), 'Отозвать жалобу')]");

    // Кнопка для отправки дополнительных документов
    private ElementsCollection submitAdditionalDocumentsButtons = $$x("//*[contains(text(), 'Направить дополнительные документы')]");

    // Кнопка для предоставления дополнительной информации
    private ElementsCollection provideAdditionalInfoButtons = $$x("//div//span[contains(text(),'Перейти')]");

    private String newOrderId;

    public String fetchOrderId() {
        System.out.println("New Order ID: " + (newOrderId = orderHeader.shouldBe(visible).text().substring(12)));
        return newOrderId;
    }

    public ComplaintWithdrawalPage clickWithdrawalButton() {
        complaintWithdrawalButtons.get(1).shouldBe(visible).click();
        return new ComplaintWithdrawalPage();
    }

    public SubmitAdditionalPage clickAdditionalDocumentsButton() {
        submitAdditionalDocumentsButtons.get(1).shouldBe(visible).click();
        return new SubmitAdditionalPage();
    }

    public ComplaintProgressPage clickAdditionalInfoButton() {
        provideAdditionalInfoButtons.get(1).shouldBe(visible).click();
        return this;
    }
}
