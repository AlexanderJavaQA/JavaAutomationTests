package baseTest;

import appconfig.AppConfig;
import doknd.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import listener.RetryListener;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@ExtendWith(RetryListener.class)
public abstract class BaseTestSelenide {
    protected SmevPage smevPage = new SmevPage();
    protected LoginPage loginPage = new LoginPage();
    protected static ComplaintWithdrawalPage complaintWithdrawalPage = new ComplaintWithdrawalPage();
    protected HandleFilingComplaintPage handleFilingComplaint = new HandleFilingComplaintPage();
    protected SmevRequestPage smevRequest = new SmevRequestPage();
    protected RepeatFilingPage repeatFilingPage = new RepeatFilingPage();
    protected SubmitAdditionalDocumentsPage submitAdditionalDocumentsPage = new SubmitAdditionalDocumentsPage();
    protected AppConfig config = ConfigFactory.create(AppConfig.class);

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.fastSetValue = true;
        Configuration.headless = false;
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";

    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
        Selenide.clearBrowserCookies();
    }

}
