package ui.knd.erknm;

import baseTest.BaseTestSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Удаление ЕРКНМ проверки")
public class DeleteErknmInspectionTests extends BaseTestSelenide {
    @Test
    @DisplayName("Удаление ЕРКНМ проверки")
    @Disabled("Нету необходимости в тесте")
    public void shouldDeleteErknmInspection() {
        smevPage
                .openSmevRequestBroadcastUat()
                .createDeleteBroadcastRequest("044108610001000430444")
                .clickButtonSubmit();
    }
}
