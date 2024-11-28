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

    public void checkBusinessNotifications(String accTValue) {
        List<ItemsItem> businessItems = getObjectsOfBusinessActivityList(accTValue).getItems();
        List<String> businessIds = businessItems.stream()
                .map(ItemsItem::getId)
                .collect(Collectors.toList());
        for (String objectId : businessIds) {
            getObjectsOfBusinessNotifications(accTValue, objectId);
        }
    }

    @Test
    @DisplayName("Проверка открытия уведомлений для объектов предпринимательской деятельности ЮЛ")
    public void checkBusinessNotificationsUL() {
        checkBusinessNotifications(accTValueUl);
    }

    @Test
    @DisplayName("Проверка открытия уведомлений для объектов предпринимательской деятельности ИП")
    public void checkBusinessNotificationsIP() {
        checkBusinessNotifications(accTValueIp);
    }

}
