package api.knd;

import appconfig.AppConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import pages.doknd.LoginPage;
import pages.doknd.SmevPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import listener.RetryListener;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;

@ExtendWith(RetryListener.class)
@Execution(ExecutionMode.CONCURRENT)
public class BaseApiTests {

    public static String accTValueUl;
    public static String accTValueFl;
    public static String accTValueIp;
    private static boolean isSetupExecuted = false;
    protected static SmevPage smevPage = new SmevPage();
    LoginPage loginPage = new LoginPage();

    protected static Stream<Arguments> userTypes() {
        return Stream.of(
                Arguments.of("ЮЛ", accTValueUl),
                Arguments.of("ФЛ", accTValueFl),
                Arguments.of("ИП", accTValueIp)
        );
    }

    @BeforeAll
    public static void setup() {
        RestAssured.filters(new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured());

        WebDriverManager.chromedriver().setup();
        if (!isSetupExecuted) {
            LoginPage loginPage = new LoginPage();
            accTValueUl = getAccTValue(loginPage.getBespalovULAuth());
            accTValueFl = getAccTValue(loginPage.getBespalovFLAuth());
            accTValueIp = getAccTValue(loginPage.getBespalovIPAuth());
            isSetupExecuted = true;
        }
    }

    public static String getAccTValue(SelenideElement element) {
        AppConfig config = ConfigFactory.create(AppConfig.class);
        Configuration.headless = false;
        String accTValue = new LoginPage().getCookieNamed(config.appealsPage(), config.userLoginBespalov(), config.userPasswordBespalov(), element);
        clearBrowserCache();
        clearBrowserCookies();
        closeWindow();
        return accTValue;
    }
}

