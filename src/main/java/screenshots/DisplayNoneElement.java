package screenshots;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class DisplayNoneElement {

    @SneakyThrows
    public static void displayNoneElements(SelenideElement... elements) {
        for (SelenideElement element : elements) {
            executeJavaScript("arguments[0].style.display='none';", element);
        }
}}
