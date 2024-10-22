package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static api.ControlObjectsFilterService.getControlObjectsWithFilters;


@DisplayName("Проверка фильтрации объектов контроля")
@Tag("API")
public class ControlObjectsWithFiltersTests extends BaseApiTests{

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100})
    @DisplayName("Проверка фильтрации объектов контроля для ЮЛ")
    public void shouldFilterControlObjectsForUL(int pageNumber) {
        getControlObjectsWithFilters(accTValueUl, pageNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100})
    @DisplayName("Проверка фильтрации объектов контроля для ФЛ")
    public void shouldFilterControlObjectsForFL(int pageNumber) {
        getControlObjectsWithFilters(accTValueFl, pageNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 10, 100})
    @DisplayName("Проверка фильтрации объектов контроля для ИП")
    public void shouldFilterControlObjectsForIP(int pageNumber) {
        getControlObjectsWithFilters(accTValueIp, pageNumber);
    }

}
