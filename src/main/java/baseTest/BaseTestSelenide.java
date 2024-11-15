package baseTest;

import appconfig.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import com.codeborne.selenide.*;
import pages.doknd.*;
import pages.knd.ControlSupervisionPage;

public abstract class BaseTestSelenide {
    protected SmevPage smevPage = new SmevPage();
    protected LoginPage loginPage = new LoginPage();
    protected static ComplaintWithdrawalPage complaintWithdrawalPage = new ComplaintWithdrawalPage();
    protected HandleFilingComplaintPage handleFilingComplaint = new HandleFilingComplaintPage();
    protected RepeatFilingPage repeatFilingPage = new RepeatFilingPage();
    protected SubmitAdditionalPage submitAdditionalDocumentsPage = new SubmitAdditionalPage();
    protected ControlSupervisionPage controlSupervisionPage = new ControlSupervisionPage();
    protected  ComplaintProgressPage complaintDetailsPage = new ComplaintProgressPage();
    protected  MyСomplaintsPage myComplaintsPage = new MyСomplaintsPage();
    protected  ComplaintProgressPage complaintProgressPage = new ComplaintProgressPage();
    protected  ElasticPage elasticPage = new ElasticPage();
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
