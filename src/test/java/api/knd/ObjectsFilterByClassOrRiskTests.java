package api.knd;

import apimodels.controlObjects.ControlObjectsAllResponse;
import apimodels.controlObjects.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.ControlObjectsService.getAllControlObjects;
import static api.ObjectsFilterByClassOrRiskService.getObjectsFilterByClassOrRisk;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("SmokeAPITest")
@DisplayName("Проверка фильтрации объектов контроля по категории риска или классу опасности")
public class ObjectsFilterByClassOrRiskTests extends BaseApiTests {

    private static final List<String> RISK_CATEGORIES = Arrays.asList(
            "низкий риск",
            "II класс",
            "умеренный риск",
            "высокий риск",
            "чрезвычайно высокий риск",
            "средний риск",
            "IV класс",
            "значительный риск",
            "III класс"
    );

    private static final List<String> RISK_CATEGORIES_ID = Arrays.asList(
            "2",
            "136",
            "3",
            "165",
            "6",
            "1",
            "19",
            "160",
            "66",
            "19",
            "136"
    );

    public void shouldFilterTypesOfControl(String accTValue) {
        ControlObjectsAllResponse allTypesOfControl = getAllControlObjects(accTValue);
        List<ItemsItem> allControlType = allTypesOfControl.getItems();
        List<String> controlTypes = allControlType.stream()
                .map(ItemsItem::getRiskCategory)
                .collect(Collectors.toList());

        controlTypes.forEach(x -> assertTrue(RISK_CATEGORIES.contains(x),
                "Не фильтрует по категории риска или классу опасности проверки"));

        for (String riskCategoryId : RISK_CATEGORIES_ID) {
            getObjectsFilterByClassOrRisk(accTValue, 1, riskCategoryId);
        }
    }

    @Test
    @DisplayName("Проверка фильтрации объектов контроля по категории риска или классу опасности для ЮЛ")
    public void checkFilterTypesOfControlUL() {
        shouldFilterTypesOfControl(accTValueUl);
    }

    @Test
    @DisplayName("Проверка фильтрации объектов контроля по категории риска или классу опасности для ФЛ")
    public void checkFilterTypesOfControlFL() {
        shouldFilterTypesOfControl(accTValueFl);
    }

    @Test
    @DisplayName("Проверка фильтрации объектов контроля по категории риска или классу опасности для ИП")
    public void checkFilterTypesOfControlIP() {
        shouldFilterTypesOfControl(accTValueIp);
    }

}
