package api.knd;

import apimodels.controlObjects.ControlObjectsAllResponse;
import apimodels.controlObjects.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import springjdbc.postgres.models.TitleRecordId;

import java.util.List;
import java.util.stream.Collectors;

import static api.ControlObjectsService.getAllControlObjects;
import static api.ObjectsFilterByTypeService.getTypeOfControlFilte;

@Tag("SmokeAPITest")
@DisplayName("Проверка фильтрации объектов контроля по виду контроля")
public class TypeOfControlFilterForObjectsTests extends BaseApiTests {
    public void getAllTypeOfControlForObjects(String accTValue) {
        ControlObjectsAllResponse allTypesOfControl = getAllControlObjects(accTValue);
        List<ItemsItem> allControlType = allTypesOfControl.getItems();
        List<String> controlTypes = allControlType.stream().map(ItemsItem::getControlType).collect(Collectors.toList());

        for (String controlType : controlTypes) {
            List<TitleRecordId> titleRecordIds = new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseUat())
                    .findTitleRecordIdsByTitle(controlType);
            List<String> newTitleRecordIds = titleRecordIds.stream().map(TitleRecordId::getTitle_record_id).collect(Collectors.toList());
            for (String titleRecordId : newTitleRecordIds) {
                getTypeOfControlFilte(accTValue, 1, titleRecordId);
            }}
    }


    @Test
    @DisplayName("Проверка фильтрации объектов контроля по виду контроля для ЮЛ")
    public void shouldFilterControlObjectsByTypeForUL() {
        getAllTypeOfControlForObjects(accTValueUl);
    }

    @Test
    @DisplayName("Проверка фильтрации объектов контроля по виду контроля для ФЛ")
    public void shouldFilterControlObjectsByTypeForFL() {
        getAllTypeOfControlForObjects(accTValueFl);
    }

    @Test
    @DisplayName("Проверка фильтрации объектов контроля по виду контроля для ИП")
    public void shouldFilterControlObjectsByTypeForIP() {
        getAllTypeOfControlForObjects(accTValueIp);
    }
}
