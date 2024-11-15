package pages.doknd;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SubmitAdditionalPage {

    // Текстовое поле для ввода комментариев при подаче документов
    private SelenideElement fieldSubmitDocumentsWithComments = $x("//textarea[@id='comments-witch-docs']");


    public SubmitAdditionalPage setValueSubmitDocuments() {
        fieldSubmitDocumentsWithComments.shouldBe(visible).setValue("Тестовые данные");
        return this;
    }

}
