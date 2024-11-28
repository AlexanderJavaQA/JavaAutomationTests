package screenshots;

import appconfig.AppConfig;
import com.codeborne.selenide.*;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static screenshots.DisplayNoneElement.displayNoneElements;

@DisplayName("Проверка верстки личного кабинета и формы досудебного обжалования контрольно-надзорной деятельности")
public class ScreenshotsTests extends BaseScreenshotTest {

}