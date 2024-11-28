package api.knd;

import apimodels.businessActivity.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import static api.BusinessActivityListService.getObjectsOfBusinessActivityList;
import static api.BusinessActivitySearchService.getObjectsOfBusinessActivitySearch;
@Tag("SmokeAPITest")
@DisplayName("Проверка поиска каждой карточки объектов предпринимательской деятельности по названию")
public class ObjectsOfBusinessActivitySearchTests extends BaseApiTests{

    public void checkBusinessActivitySearch(String accTValue) {
        List<ItemsItem> objectsOfBusinessIdList = getObjectsOfBusinessActivityList(accTValue).getItems();
        List<String> namesBusinessActivity = objectsOfBusinessIdList.stream()
                .map(ItemsItem::getName)
                .collect(Collectors.toList());
        for (String name : namesBusinessActivity) {
            getObjectsOfBusinessActivitySearch(accTValue, name);
        }
    }

    @Test
    @DisplayName("Проверка поиска объектов предпринимательской деятельности по названию для Юридических Лиц")
    public void checkBusinessActivitySearchUL() {
        checkBusinessActivitySearch(accTValueUl);
    }

    @Test
    @DisplayName("Проверка поиска объектов предпринимательской деятельности по названию для Индивидуальных Предпринимателей")
    public void checkBusinessActivitySearchIP() {
        checkBusinessActivitySearch(accTValueIp);
    }

}
