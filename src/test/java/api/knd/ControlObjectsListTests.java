package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.ControlObjectsService.getAllControlObjects;

@Tag("SmokeAPI")
@DisplayName("Проверка получения списка объектов контроля")
public class ControlObjectsListTests extends BaseApiTests {

    @Test
    @DisplayName("Проверка получения объектов контроля для ЮЛ")
    public void shouldRetrieveControlObjectsForUL() {
        getAllControlObjects(accTValueUl);
    }

    @Test
    @DisplayName("Проверка получения объектов контроля для ФЛ")
    public void shouldRetrieveControlObjectsForFL() {
        getAllControlObjects(accTValueFl);
    }

    @Test
    @DisplayName("Проверка получения объектов контроля для ИП")
    public void shouldRetrieveControlObjectsForIP() {
        getAllControlObjects(accTValueIp);
    }

}
