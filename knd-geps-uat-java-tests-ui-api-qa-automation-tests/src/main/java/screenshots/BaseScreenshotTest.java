package screenshots;

import appconfig.AppConfig;
import com.codeborne.selenide.*;
import doknd.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
public abstract class BaseScreenshotTest {
    private String testName;
    private static File outputDir;
    protected LoginPage loginPage = new LoginPage();
    protected HandleFilingComplaintPage handleFilingComplaint = new HandleFilingComplaintPage();
    protected SmevRequestPage smevRequest = new SmevRequestPage();
    protected FillDetailsComplaintPage fillDetailsComplaintPage = new FillDetailsComplaintPage();

    @BeforeEach
    public void initTestName(TestInfo info) {
        testName = info.getTestMethod().get().getName();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWindow();
        Configuration.browserCapabilities = new SelenideConfig().browserCapabilities();
    }


    @BeforeAll
    public static void initFolder() {
        WebDriverManager.chromedriver().setup();
        outputDir = new File("build/actualScreen");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }


    @SneakyThrows
    protected void assertFullScreen(By ignoredElement) {
        AShot ashot = new AShot()
                .addIgnoredElement(ignoredElement)
                .shootingStrategy(ShootingStrategies.viewportPasting(2000));
        Screenshot screenshot = ashot.takeScreenshot(WebDriverRunner.getWebDriver());

        File actualScreen = new File(outputDir.getAbsolutePath() + "/" + testName + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualScreen);

        File expectedScreen = new File("src/main/java/screenshots/expectedScreen/expectedScreen/" + testName + ".png");

        if (!expectedScreen.exists()) {
            throw new RuntimeException("The expected screenshot file does not exist: " + expectedScreen.getPath());
        }

        assertImages(actualScreen, expectedScreen);

    }

    @SneakyThrows
    protected void assertFullScreen() {
        AShot ashot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(2000));
        Screenshot screenshot = ashot.takeScreenshot(WebDriverRunner.getWebDriver());

        File actualScreen = new File(outputDir.getAbsolutePath() + "/" + testName + ".png");
        ImageIO.write(screenshot.getImage(), "png", actualScreen);

        File expectedScreen = new File("src/main/java/screenshots/expectedScreen/" + testName + ".png");

        if (!expectedScreen.exists()) {
            throw new RuntimeException("The expected screenshot file does not exist: " + expectedScreen.getPath());
        }
        assertImages(actualScreen, expectedScreen);

    }

    @SneakyThrows
    private void assertImages(File actual, File expected) {
        ImageDiff differ = new ImageDiffer()
                .makeDiff(ImageIO.read(actual), ImageIO.read(expected))
                .withDiffSizeTrigger(10);

        if (differ.hasDiff()) {
            BufferedImage diffImage = differ.getMarkedImage();
            File diffFile = new File("build/resultScreen/" + actual.getName());

            if (!diffFile.getParentFile().exists()) {
                diffFile.getParentFile().mkdirs();
            }

            ImageIO.write(diffImage, "png", diffFile);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(diffImage, "png", bos);
            byte[] image = bos.toByteArray();
            Allure.getLifecycle().addAttachment("diff", "image/png", "png", image);
        }

        assertFalse(differ.hasDiff());
}}
