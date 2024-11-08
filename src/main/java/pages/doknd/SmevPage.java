package pages.doknd;


import appconfig.AppConfig;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.ElementShouldNot;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import springjdbc.oracle.DataAccessObjectOracle;
import springjdbc.oracle.DatabaseConnectOracle;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;


import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Data
public class SmevPage {

    private SelenideElement inputMessageId = $x("//input[@id='messageID']");
    private SelenideElement documentIdDisplay = $x("//div[17]/pre");
    private SelenideElement inputQuery = $x("//textarea[@data-test-subj='queryInput']");
    private SelenideElement submitButtonIcon = $x("//button[@class='euiButton euiButton--success euiButton--fill euiSuperUpdateButton']//span[@class='euiButtonContent euiButton__content']//*[name()='svg']");
    private SelenideElement documentTextField = $x("//td[@class='kbnDocTableCell__dataField eui-textBreakAll eui-textBreakWord']");
    private SelenideElement inputSenderCode = $x("//input[@id='senderCode']");
    private SelenideElement textareaXmlRequest = $x("//textarea[@id='xmlRequest']");
    private SelenideElement submitButton = $x("//button[@type='submit']");
    private SelenideElement okButton = $x("//button[normalize-space()='Ok']");
    private SelenideElement smevAddFileButton = $x("//button[contains(text(),'Добавить файл')]");
    private SelenideElement smevObjectIdInput = $x("//*[@id='objectId']");
    private SelenideElement smevFileMnemonicInput = $x("//*[@id='fileMnemonic']");
    private SelenideElement smevObjectTypeIdInput = $x("//*[@id='objectTypeId']");
    private SelenideElement addLinkButton = $x("//*[@id='addLink']");
    private SelenideElement resultModalBody = $x("//*[@id=\"resultModal\"]/div/div/div[2]/p");
    private SelenideElement smevDeliveryConfirmationMessage = $x("//*[text()='Сообщение доставлено']");
    private SelenideElement smevUpdateResultsResponse = $x("//*[text()='\"Ковшина Ирина Викторовна\"']");
    private SelenideElement smevUpdateErknmPassedResponse = $x("//*[text()='\"660012, КРАЙ, КРАСНОЯРСКИЙ, ГОРОД, КРАСНОЯРСК, УЛИЦА, КАРАМЗИНА, ДОМ 8, 240000010000878\"']");
    private SelenideElement smevErknmPassedResponse = $x("//*[text()='\"Аренда и управление собственным или арендованным нежилым недвижимым имуществом\"']");
    private SelenideElement smevErknmResultsAndUpcomingResponse = $x("//*[text()='\"п. 2.2.2. п. 2.4.3. п. 2.4.11. п. 2.5.2. п. 2.5.3. п. 2.6.1.  п. 2.11.4. п. 2.11.2. п. 3.2.4. п. 3.4.8.  п. 3.4.13. СП 2.4.3648-20 &quot;Санитарно-эпидемиологические требования к организациям воспитания и обучения, отдыха и оздоровления детей и молодежи&quot;, п. 2.13. СанПиН 2.3/2.4.3590-20 &quot;Санитарно-эпидемиологические требования к организации общественного питания населения&quot;, п. 144 СанПиН 1.2.3685-21 &quot;Гигиенические нормативы и требования к обеспечению безопасности и (или) безвредности для человека факторов среды обитания&quot;, предусмотренное ч. 1 ст. 6.7. КоАП РФ (постановление от 23.11.2021 г. № 35925).\"']");
    private ElementsCollection messageIdLinks = $$x("//a[@target='_blank']");
    AppConfig config = ConfigFactory.create(AppConfig.class);


    public SmevPage openRequestToSmevTorTemplatesUat() {
        open(config.requestToSmevTorTemplatesUat());
        return this;
    }

    public SmevPage createErknmStubTemplateDev2(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseDev2()).createErknmStubTemplate(isFiz, inn, ogrn, erknmIdStubs);
        return this;
    }

    public SmevPage createERPStubTemplateDev2(String inn, String ogrn, String erpId) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseDev2()).createERPStubTemplate(inn, ogrn, erpId);
        return this;
    }

    public SmevPage ccreateErknmStubTemplateUat(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).createErknmStubTemplate(isFiz, inn, ogrn, erknmIdStubs);
        return this;
    }

    public SmevPage updateErknmStubTemplateDev2(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseDev2()).updateErknmStubTemplate(isFiz, inn, ogrn, erknmIdStubs);
        return this;
    }

    public SmevPage updateErknmStubTemplateUat(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).updateErknmStubTemplate(isFiz, inn, ogrn, erknmIdStubs);
        return this;
    }

    public SmevPage clearErknmGepsAndInspectionHistoryDev2() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseDev2()).clearErknmGepsHistory();
        new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseDev2()).clearKndErknmInspectionHistory();
        return this;
    }

    public SmevPage clearErknmGepsAndInspectionHistoryUat() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseDev2()).clearErknmGepsHistory();
        new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseDev2()).clearKndErknmInspectionHistory();
        return this;
    }

    public SmevPage enableSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).enableERPSmevaFlag();
        return this;
    }

    public SmevPage disableSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).disableERPSmevaFlag();
        return this;
    }

    public SmevPage createErknmResultsStubTemplateDev2(String isFiz, String inn, String ogrn, String erknmId) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseDev2()).createErknmResultsStubTemplate(isFiz, inn, ogrn, erknmId);
        return this;
    }

    public SmevPage createErknmResultsStubTemplateUat(String isFiz, String inn, String ogrn, String erknmId) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).createErknmResultsStubTemplate(isFiz, inn, ogrn, erknmId);
        return this;
    }

    public SmevPage updateErknmResultsStubTemplateDev2(String isFiz, String inn, String ogrn, String erknmId) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseDev2()).updateErknmResultsStubTemplate(isFiz, inn, ogrn, erknmId);
        return this;
    }

    public SmevPage updateErknmResultsStubTemplateUat(String isFiz, String inn, String ogrn, String erknmId) {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).updateErknmResultsStubTemplate(isFiz, inn, ogrn, erknmId);
        return this;
    }


    public SmevPage openSmevRequestBroadcastDev2() {
        open(config.smevRequestBroadcastDev2());
        return this;
    }

    public SmevPage openSmevRequestBroadcastUat() {
        open(config.smevRequestBroadcastUat());
        return this;
    }

    public SmevPage createBroadcastRequest(String erpIdValue) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erpIdValue + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"CREATE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
                "</erp:Broadcast>");
        return this;
    }

    public SmevPage createERPBroadcastRequest(String erpId) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/2.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/2.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/2.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\""+erpId+"\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"CREATE\"/>\n" +
                "</erp:Broadcast>");
        return this;
    }

    public SmevPage createUpdateRequestBroadcast(String erpIdValue) {
        String xmlValue = "<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erpIdValue + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"UPDATE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
                "</erp:Broadcast>";
        textareaXmlRequest.setValue(xmlValue);
        return this;
    }


    public SmevPage createDeleteBroadcastRequest(String erpIdValue) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erpIdValue + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"DELETE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
                "</erp:Broadcast>");
        return this;
    }

    public SmevPage setXmlRequestValue(String value) {
        textareaXmlRequest.shouldBe(enabled).setValue(value);
        return this;
    }

    public SmevPage clickButtonSubmit() {
        submitButton.shouldBe(enabled).click();
        return this;
    }


    public SmevPage setSenderCode(String code) {
        inputSenderCode.shouldBe(enabled).setValue(code);
        return this;
    }

    public SmevPage verifyModalBodyIsVisible() {
        resultModalBody.shouldBe(visible);
        return this;
    }


    public SmevPage clickAddFile() {
        smevAddFileButton.click();
        return this;
    }

    public SmevPage setInputObjectId(String objectId) {
        smevObjectIdInput.shouldBe(visible).setValue(objectId);
        return this;
    }

    public SmevPage setInputFileMnemonic(String fileMnemonic) {
        smevFileMnemonicInput.setValue(fileMnemonic);
        return this;
    }

    public SmevPage setInputtObjectTypeId(String objectTypeId) {
        smevObjectTypeIdInput.setValue(objectTypeId);
        return this;
    }

    public SmevPage clickAddLink() {
        addLinkButton.click();
        return this;
    }


    public void openAndVerifyDeliveryConfirmationInNewTab(String url, SelenideElement confirmationElement) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL не должен быть null или пустым.");
        }

        // Открываем новое окно браузера
        executeJavaScript("window.open('', '_blank');");
        switchTo().window(1);

        try {
            open(url);
            Selenide.refresh();

            int maxAttempts = 10;
            int attempt = 0;
            long refreshIntervalMillis = 1000; // 1 секунда

            while (attempt < maxAttempts) {
                try {
                    if (confirmationElement.shouldBe(visible, Duration.ofSeconds(1)).isDisplayed()) {
                        return;
                    }
                } catch (ElementNotFound | ElementShould | ElementShouldNot e) {
                }

                attempt++;
                sleep(refreshIntervalMillis);
                Selenide.refresh();
            }

            throw new AssertionError("Элемент подтверждения доставки не отображается после " + maxAttempts + " попыток.");
        } finally {
            switchTo().window(0);
        }
    }

    public String getMessageIDFromModal() {
        String messageID = resultModalBody.getText();
        return messageID.replace("MessageID:", "").trim();
    }

    public void verifyFullInspectionResponseDev2(String messageID, SelenideElement element) {
        executeJavaScript("window.open('', '_blank');");
        switchTo().window(1);
        open(config.smevAdapterUrlDev2());
        Selenide.refresh();

        int index = (messageIdLinks.indexOf(messageIdLinks.find(text(messageID))) - 1);
        String newMessageId = messageIdLinks.get(index).text();
        open(config.smevAdapterUrlDev2() + newMessageId);


        int i = 0;
        while (i == 10) {
            try {
                assertTrue(element.
                        shouldBe(visible, Duration.ofSeconds(1)).isDisplayed());
                break;
            } catch (ElementNotFound e) {
                Selenide.refresh();
            }
        }
        i++;
    }


    public void verifyFullInspectionResponseUat(String messageID, SelenideElement element) {
        executeJavaScript("window.open('', '_blank');");
        switchTo().window(1);
        open(config.smevAdapterUrlUat());
        Selenide.refresh();

        int index = (messageIdLinks.indexOf(messageIdLinks.find(text(messageID))) - 1);
        String newMessageId = messageIdLinks.get(index).text();
        open(config.smevAdapterUrlUat() + newMessageId);


        int i = 0;
        while (i == 10) {
            try {
                assertTrue(element.
                        shouldBe(visible, Duration.ofSeconds(1)).isDisplayed());
                break;
            } catch (ElementNotFound e) {
                Selenide.refresh();
            }
        }
        i++;
    }

    public SmevPage clickButtonOk() {
        okButton.shouldBe(enabled).click();
        return this;
    }

    public SmevPage getNewWindowHandles(String oldWindow) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        driver.switchTo().newWindow(WindowType.TAB);
        open("http://pgu-uat-fednlb.test.gosuslugi.ru/smevadapter/responseToPgu");
        String newWindow = driver.getWindowHandles().stream().filter(s -> !oldWindow.equals(s)).findFirst().get();
        return this;
    }


    public SmevPage clearMessageID() {
        inputMessageId.clear();
        return this;
    }

    public SmevPage setMessageID(String messageID) {
        this.inputMessageId.setValue(messageID);
        return this;
    }

    public SmevPage clearXmlRequest() {
        textareaXmlRequest.shouldBe(enabled).clear();
        return this;
    }

    public SmevPage setXmlRequest(String OrderIDadd, String statusCode) {
        textareaXmlRequest.setValue("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<tns:doKndResponse xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tns=\"http://epgu.gosuslugi.ru/do_knd/1.0.0\">\n" +
                "<tns:doAppealResponse>\n" +
                "<tns:orderId>" + OrderIDadd + "</tns:orderId>\n" +
                "<tns:statusData>\n" +
                "<tns:statusCode>\n" +
                "<tns:techCode>" + statusCode + "</tns:techCode>\n" +
                "</tns:statusCode>\n" +
                "<tns:comment>Зарегистрирована жалоба № 24170700137573</tns:comment>\n" +
                "<tns:cancelAllowed>true</tns:cancelAllowed>\n" +
                "</tns:statusData>\n" +
                "<tns:appealId>24170700137573</tns:appealId>\n" +
                "</tns:doAppealResponse>\n" +
                "</tns:doKndResponse>");
        return this;
    }


    public SmevPage switchOldWindow(String oldWindow) {
        switchTo().window(oldWindow);
        return this;
    }

    public SmevPage checkAttributeSrc() {
        ElementsCollection status = $$x("//div[@class='col-3 col-md-6 mb-40 col-lg-9']//img[@class='image']");
        for (WebElement i : status) {
            i.getAttribute("src");
            assertEquals("https://static-uat.egovdev.ru/knd-st/assets/svg/progress/done.svg",
                    i.getAttribute("src"));
        }
        return this;
    }

    public SmevPage openSmevResponseToPguUat() {
        switchTo().newWindow(WindowType.TAB);
        open(config.smevAdapterResponseToPguUrlUat());
        return this;
    }

    public SmevPage openSmevResponseToPguDev2() {
        switchTo().newWindow(WindowType.TAB);
        open(config.smevAdapterResponseToPguUrlDev2());
        return this;
    }

    public LoginPage checkOrderStatusHistory(String orderId) {
        String ArrayStatusHistory = String.valueOf(new DataAccessObjectOracle(DatabaseConnectOracle.oracleDatabaseUat()).getOrderStatusHistory(orderId));
        assertEquals("[3, 7, 1, 21, 17, 0]",
                ArrayStatusHistory);

        return new LoginPage();
    }

    public LoginPage checkOrderStatusHistoryGosKey(String orderId) {
        String ArrayStatusHistory = String.valueOf(new DataAccessObjectOracle(DatabaseConnectOracle.oracleDatabaseUat()).getOrderStatusHistory(orderId));
        assertEquals("[3, 7, 1, 21, 122, 120, 17, 0]",
                ArrayStatusHistory);
        return new LoginPage();
    }
}
