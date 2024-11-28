package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.BusinessActivityListService.getObjectsOfBusinessActivityList;

@Tag("SmokeAPITest")
@DisplayName("Проверка получения объектов предпринимательской деятельности")
public class ObjectsOfBusinessActivityListTests extends BaseApiTests{

    @Test
    @DisplayName("Проверка получения объектов предпринимательской деятельности для Юридических Лиц")
    public void checkBusinessActivityObjectsUL() {
        getObjectsOfBusinessActivityList(accTValueUl);
    }

    @Test
    @DisplayName("Проверка получения объектов предпринимательской деятельности для Индивидуальных Предпринимателей")
    public void checkBusinessActivityObjectsIP() {
        getObjectsOfBusinessActivityList(accTValueIp);
    }

}
