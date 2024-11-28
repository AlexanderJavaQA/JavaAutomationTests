package ui.knd.template;


import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static templates.XmlTemplates.*;

@DisplayName("Проверка отправки ТОР шаблонов")
public class TorTemplateTests  extends BaseTestSelenide {

        public void sendTORTemplates(String erknmId, String gepsUserId) {
        String[] templates = {
                KND_DOCUMENTS(erknmId, gepsUserId),
                KNO_MOTIV_REQUEST(erknmId, gepsUserId),
                KND_INSPECTION_UAT(erknmId, gepsUserId),
                GIS_TOR_KND_VKS(erknmId, gepsUserId),
                KND_ADM_CASE(erknmId, gepsUserId)
        };

        for (String template : templates) {
            smevPage
                    .openRequestToSmevTorTemplatesUat()
                    .setXmlRequestValue(template)
                    .setSenderCode("MNSV08")
                    .clickButtonSubmit()
                    .checkResultModalIsVisible ();
                }
    }

    @Test
    @DisplayName("Проверка отправки ТОР шаблонов")
    @Disabled("СМЭВ адаптер не работает")
    public void sendTORTemplatesTest() {
        sendTORTemplates("10000000000350106", config.gepsIdBespalovUl());
    }
}



