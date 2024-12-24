package pages.doknd;

import appconfig.AppConfig;
import com.codeborne.selenide.*;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


@Data
public class SmevPage {

    // Поле ввода для идентификатора сообщения
    private SelenideElement inputMessageId = $x("//input[@id='messageID']");

    // Элемент для отображения идентификатора документа
    private SelenideElement documentIdDisplay = $x("//div[17]/pre");

    // Поле ввода для запроса
    private SelenideElement inputQuery = $x("//textarea[@data-test-subj='queryInput']");

    //Кнопка отправки запроса
    private SelenideElement submitButtonIcon = $x("//button[@class='euiButton euiButton--success euiButton--fill euiSuperUpdateButton']//span[@class='euiButtonContent euiButton__content']//*[name()='svg']");

    // Поле с текстом документа в таблице
    private SelenideElement documentTextField = $x("//td[@class='kbnDocTableCell__dataField eui-textBreakAll eui-textBreakWord']");

    // Поле ввода для кода отправителя
    private SelenideElement inputSenderCode = $x("//input[@id='senderCode']");

    // Текстовое поле для XML-запроса
    private SelenideElement textareaXmlRequest = $x("//textarea[@id='xmlRequest']");

    // Кнопка для отправки формы
    private SelenideElement submitButton = $x("//button[@type='submit']");

    // Кнопка для подтверждения (Ok)
    private SelenideElement okButton = $x("//button[normalize-space()='Ok']");

    // Кнопка для добавления файла в модуле СМЭВ
    private SelenideElement smevAddFileButton = $x("//button[contains(text(),'Добавить файл')]");

    // Поле ввода для Object ID в модуле СМЭВ
    private SelenideElement smevObjectIdInput = $x("//*[@id='objectId']");

    // Поле ввода для File Mnemonic в модуле СМЭВ
    private SelenideElement smevFileMnemonicInput = $x("//*[@id='fileMnemonic']");

    // Поле ввода для Object Type ID в модуле СМЭВ
    private SelenideElement smevObjectTypeIdInput = $x("//*[@id='objectTypeId']");

    // Кнопка для добавления ссылки
    private SelenideElement addLinkButton = $x("//*[@id='addLink']");

    // Элемент тела модального окна результата
    private SelenideElement resultModalBody = $x("//*[@id=\"resultModal\"]/div/div/div[2]/p");

    // Сообщение о доставке в модуле СМЭВ
    private SelenideElement smevDeliveryConfirmationMessage = $x("//*[text()='Сообщение доставлено']");

    // Ответ о результатах обновления от СМЭВ (Ирина Викторовна)
    private SelenideElement smevUpdateResultsResponse = $x("//*[text()='\"Ковшина Ирина Викторовна\"']");

    // Ответ о прошедшем ЭРКНМ в СМЭВ (адрес Красноярск)
    private SelenideElement smevUpdateErknmPassedResponse = $x("//*[text()='\"660012, КРАЙ, КРАСНОЯРСКИЙ, ГОРОД, КРАСНОЯРСК, УЛИЦА, КАРАМЗИНА, ДОМ 8, 240000010000878\"']");

    // Ответ о прошедшем ЭРКНМ в СМЭВ (описание аренды)
    private SelenideElement smevErknmPassedResponse = $x("//*[text()='\"Аренда и управление собственным или арендованным нежилым недвижимым имуществом\"']");

    // Ответ о результатах и предстоящих проверках в СМЭВ
    private SelenideElement smevErknmResultsAndUpcomingResponse = $x("//*[text()='\"п. 2.2.2. п. 2.4.3. п. 2.4.11. п. 2.5.2. п. 2.5.3. п. 2.6.1.  п. 2.11.4. п. 2.11.2. п. 3.2.4. п. 3.4.8.  п. 3.4.13. СП 2.4.3648-20 &quot;Санитарно-эпидемиологические требования к организациям воспитания и обучения, отдыха и оздоровления детей и молодежи&quot;, п. 2.13. СанПиН 2.3/2.4.3590-20 &quot;Санитарно-эпидемиологические требования к организации общественного питания населения&quot;, п. 144 СанПиН 1.2.3685-21 &quot;Гигиенические нормативы и требования к обеспечению безопасности и (или) безвредности для человека факторов среды обитания&quot;, предусмотренное ч. 1 ст. 6.7. КоАП РФ (постановление от 23.11.2021 г. № 35925).\"']");

    // Коллекция ссылок на идентификаторы сообщений (Message ID)
    private ElementsCollection messageIdLinks = $$x("//a[@target='_blank']");

    ComplaintProgressPage complaintDetailsPage = new ComplaintProgressPage();
    public static String orderIdAppeal;

    AppConfig config = ConfigFactory.create(AppConfig.class);


    public SmevPage openRequestToSmevTorTemplatesUat() {
        open(config.smevTorRequest());
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

    public SmevPage enableERPSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).enableERPSmevaFlag();
        return this;
    }

    public SmevPage enableERKNMSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).enableERKNMSmevaFlag();
        return this;
    }

    public SmevPage disableERKNMSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).disableERKNMSmevaFlag();
        return this;
    }

    public SmevPage disableERPSmevaFlag() {
        new DataAccessObjectPostgres(DatabaseConnectPostgres.smevaDatabaseUat()).disableERKNMSmevaFlag();
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
        open(config.smevBroadcastRequestDev2());
        return this;
    }

    public SmevPage openSmevStatusAppealRequest() {
        open(config.smevStatusAppealRequest());
        return this;
    }

    public SmevPage openSmevStatusAppealRequestDev2() {
        open(config.smevStatusAppealRequestDev2());
        return this;
    }

    public SmevPage createERKNMBroadcastRequest(String erknmId) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erknmId + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"CREATE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
                "</erp:Broadcast>");
        return this;
    }

    public SmevPage createERPBroadcastRequest(String erpId) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/2.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/2.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/2.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\""+erpId+"\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"CREATE\"/>\n" +
                "</erp:Broadcast>");
        return this;
    }

    public SmevPage createUpdateERKNMRequestBroadcast(String erknmId){
        String xmlValue = "<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erknmId + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"UPDATE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
                "</erp:Broadcast>";
        textareaXmlRequest.setValue(xmlValue);
        return this;
    }


    public SmevPage createDeleteERKNMBroadcastRequest(String erknmId) {
        textareaXmlRequest.setValue("<erp:Broadcast xmlns:erp=\"urn://ru.gov.proc.erp.broadcast/3.0.1\" xmlns:erp_types=\"urn://ru.gov.proc.erp.broadcast/types/3.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn://ru.gov.proc.erp.broadcast/3.0.1 ru.gov.proc.erp.broadcast.smev.xsd\">\n" +
                "<erp_types:Inspection domainId=\"1040550000000001\" erpId=\"" + erknmId + "\" eventDate=\"2017-12-17T12:14:42Z\" eventType=\"DELETE\" guid=\"20170915-1311-0134-2344-000000383432\"/>\n" +
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

    public SmevPage checkResultModalIsVisible() {
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



    public String getMessageIDFromModal() {
        String messageID = resultModalBody.getText();
        return messageID.replace("MessageID:", "").trim();
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

    public SmevPage setXmlRequest(String OrderId, String statusCode) {
        textareaXmlRequest.setValue("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<tns:doKndResponse xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:tns=\"http://epgu.gosuslugi.ru/do_knd/1.0.0\">\n" +
                "<tns:doAppealResponse>\n" +
                "<tns:orderId>" + OrderId + "</tns:orderId>\n" +
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


}
