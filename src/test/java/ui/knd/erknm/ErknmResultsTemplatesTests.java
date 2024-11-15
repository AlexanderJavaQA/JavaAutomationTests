package ui.knd.erknm;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.core.annotation.Order;

@DisplayName("Проверка отправки результатов ЕРКНМ шаблонов")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ErknmResultsTemplatesTests extends BaseTestSelenide {

    @Order(1)
    public void submittedErknmResultsTemplatesForEntity(String isFiz, String inn, String ogrn, String erknmId) {

        smevPage
                .enableERKNMSmevaFlag()
                .clearErknmGepsAndInspectionHistoryDev2()
                .createErknmResultsStubTemplateDev2(isFiz, inn, ogrn, erknmId)
                .openSmevRequestBroadcastDev2()
                .createERKNMBroadcastRequest(erknmId)
                .clickButtonSubmit()
                .checkResultModalIsVisible ();

        String messageID = smevPage.getMessageIDFromModal();
            smevPage.disableERKNMSmevaFlag();
    }

    @Order(2)
    public void updateSubmittedErknmResultsForEntity(String isFiz, String inn, String ogrn, String erknmId) {
        smevPage
                .enableERKNMSmevaFlag()
                .updateErknmResultsStubTemplateDev2(isFiz, inn, ogrn, erknmId)
                .openSmevRequestBroadcastDev2()
                .createUpdateERKNMRequestBroadcast(erknmId)
                .clickButtonSubmit()
                .checkResultModalIsVisible ();

        String messageID = smevPage.getMessageIDFromModal();
        smevPage.disableERKNMSmevaFlag();

    }

    @Test
    @DisplayName("Проверка отправки результатов ЕРКНМ шаблонов")
    public void verifyErknmResultsSubmissionProcess() {
        submittedErknmResultsTemplatesForEntity("false", "1201004920", "1020200715281", "1108610001000430444" );
        updateSubmittedErknmResultsForEntity("false", "1201004920", "1020200715281", "1108610001000430444");
    }
}

