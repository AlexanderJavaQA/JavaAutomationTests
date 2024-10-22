package api.knd;

import apimodels.businessActivity.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.BusinessActivityListService.getObjectsOfBusinessActivityList;
import static api.BusinessNotificationsService.getObjectsOfBusinessNotifications;

@Tag("SmokeAPITest")
@DisplayName("Проверка открытия каждой карточки уведомлений объектов предпринимательской деятельности")
public class ObjectsOfBusinessNotificationsTests extends BaseApiTests {

    public void ObjectsOfBusinessNotificationsTest(String accTValue) {
        List<ItemsItem> objectsOfBusinessIdList = getObjectsOfBusinessActivityList(accTValue).getItems();
        List<String> objectsOfBusinessId = objectsOfBusinessIdList.stream().map(ItemsItem::getId).collect(Collectors.toList());
        for (String objectId : objectsOfBusinessId) {
            getObjectsOfBusinessNotifications(accTValue, objectId);

        }
    }

    @Test
    @DisplayName("Проверка открытия уведомлений каждой карточки объектов предпринимательской деятельности для ЮЛ")
    public void ObjectsOfBusinessNotificationsForUL() {
        ObjectsOfBusinessNotificationsTest(accTValueUl);
    }

    @Test
    @DisplayName("Проверка открытия уведомлений каждой карточки объектов предпринимательской деятельности для ИП")
    public void ObjectsOfBusinessNotificationsForIP() {
        ObjectsOfBusinessNotificationsTest(accTValueIp);
    }
}
