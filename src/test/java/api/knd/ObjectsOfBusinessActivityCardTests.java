package api.knd;

import apimodels.businessActivity.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static api.BusinessActivityCardService.getObjectsOfBusinessActivityCardList;
import static api.BusinessActivityListService.getObjectsOfBusinessActivityList;

@Tag("SmokeAPITest")
@DisplayName("Проверка открытия каждой карточки объектов предпринимательской деятельности")
public class ObjectsOfBusinessActivityCardTests extends BaseApiTests {
    public void shouldOpenAllBusinessActivityCards(String accTValue) {
        List<apimodels.businessActivity.ItemsItem> objectsOfBusinessIdList = getObjectsOfBusinessActivityList(accTValue).getItems();
        List<String> objectsOfBusinessId = objectsOfBusinessIdList.stream()
                .map(ItemsItem::getId)
                .collect(Collectors.toList());
        for (String objectId : objectsOfBusinessId) {
            try {
                getObjectsOfBusinessActivityCardList(accTValue, objectId);
            } catch (Exception exception) {
                getObjectsOfBusinessActivityCardList(accTValue, objectId);
            }
        }
    }

    @Test
    @DisplayName("Проверка открытия каждой карточки объектов предпринимательской деятельности для ЮЛ")
    public void shouldOpenAllBusinessActivityCardsForUL() {
        shouldOpenAllBusinessActivityCards(accTValueUl);
    }

    @Test
    @DisplayName("Проверка открытия каждой карточки объектов предпринимательской деятельности для ИП")
    public void shouldOpenAllBusinessActivityCardsForIP() {
        shouldOpenAllBusinessActivityCards(accTValueIp);
    }
}
