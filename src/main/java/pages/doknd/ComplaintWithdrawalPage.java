package pages.doknd;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class ComplaintWithdrawalPage {

    // Поле для ввода причины отзыва жалобы
    private SelenideElement reasonForWithdrawalField = $x("//textarea[@id='reason-cancel']");

    // Текстовый элемент, указывающий на раздел с прикрепленными документами для отзыва жалобы
    private SelenideElement attachedDocumentsText = $x("//*[contains(text(), 'Прикреплённые документы')]");

    public ComplaintWithdrawalPage setWithdrawalReason() {
        reasonForWithdrawalField.shouldBe(visible).setValue("Тестовые данные");
        return this;
    }

}
