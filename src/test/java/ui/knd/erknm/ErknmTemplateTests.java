package ui.knd.erknm;

import baseTest.BaseTestSelenide;

import org.junit.jupiter.api.*;

import java.util.Random;

@DisplayName("Проверка отправки ЕРКНМ шаблонов")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ErknmTemplateTests extends BaseTestSelenide {


    @Order(1)
    public void submittedErknmTemplateForEntity(String isFiz, String inn, String ogrn, String erknmId) {
        smevPage
                .enableERKNMSmevaFlag()
                .clearErknmGepsAndInspectionHistoryDev2()
                .createErknmStubTemplateDev2(isFiz, inn, ogrn, erknmId)
                .openSmevRequestBroadcastDev2()
                .createERKNMBroadcastRequest(erknmId)
                .clickButtonSubmit()
                .checkResultModalIsVisible ();

        String messageID = smevPage.getMessageIDFromModal();
        }

    @Order(2)
    public void updateSubmittedErknmTemplateForEntity(String isFiz, String inn, String ogrn, String erknmId) {
        smevPage
                .updateErknmStubTemplateDev2(isFiz, inn, ogrn, erknmId)
                .openSmevRequestBroadcastDev2()
                .createERKNMBroadcastRequest(erknmId)
                .clickButtonSubmit()
                .checkResultModalIsVisible ();

        String messageID = smevPage.getMessageIDFromModal();
       }

    @Test
    @DisplayName("Проверка отправки ЕРКНМ шаблонов")
    public void verifySingleErknmTemplateSubmission() {
        submittedErknmTemplateForEntity("false", "1201004920", "1020200715281", "1108610001000430444");
        updateSubmittedErknmTemplateForEntity("false", "1201004920", "1020200715281", "1108610001000430444");
    }

    @Test
    @DisplayName("Проверка отправки многих ЕРКНМ шаблонов")
    public void verifyMultipleErknmTemplateSubmissions() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String randomNumber = "1000" + String.format("%018d", random.nextLong() & Long.MAX_VALUE);
            submittedErknmTemplateForEntity("false", "1201004920", "1020200715281", randomNumber);
        }
    }
}
