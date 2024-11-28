package api.knd;

import apimodels.controlObjects.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.ControlObjectSearchService.getControlObjectSearch;
import static api.ControlObjectsService.getAllControlObjects;


@Tag("SmokeAPI")
@DisplayName("Проверка поиска объектов контроля")
public class ControlObjectSearchTests extends BaseApiTests {

    public void searchControlObjects(String accountTypeValue) {
        List<ItemsItem> objectIdList = getAllControlObjects(accountTypeValue).getItems();
        List<String> objectIds = objectIdList.stream()
                .map(ItemsItem::getObjectId)
                .collect(Collectors.toList());

        for (String objectId : objectIds) {
            getControlObjectSearch(accountTypeValue, objectId);
        }
    }

    @Test
    @DisplayName("Проверка поиска объектов контроля для ЮЛ")
    public void checkSearchControlObjectsUL() {
        searchControlObjects(accTValueUl);
    }

    @Test
    @DisplayName("Проверка поиска объектов контроля для ФЛ")
    public void checkSearchControlObjectsFL() {
        searchControlObjects(accTValueFl);
    }

    @Test
    @DisplayName("Проверка поиска объектов контроля для ИП")
    public void checkSearchControlObjectsIP() {
        searchControlObjects(accTValueIp);
    }
}
