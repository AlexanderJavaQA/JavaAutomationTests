package api.knd;

import apimodels.erknm.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static api.ErknmInspectionSearchService.getErknmInspectionSearch;
import static api.ErknmInspectionSortService.getErknmInspectionsSort;
import static api.knd.ErknmInspectionsListTests.SORT_ORDER_DESC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("API")
@DisplayName("Поиск ЕРКНМ проверок по номеру, типу проверки, типу контроля, организации КНО и статусу")
public class ErknmInspectionSearchTests extends BaseApiTests {

    public void checkValidateInspectionsSearchResults(String accTValue) {
        checkSearchInspections(accTValue, SurveillanceItemsList::getErknmId, "Несоответствие идентификатора ERKNM в ответе API поиска.");
        checkSearchInspections(accTValue, SurveillanceItemsList::getKind, "Несоответствие типа проверки в ответе API поиска.");
        checkSearchInspections(accTValue, SurveillanceItemsList::getKindControl, "Несоответствие типа контроля в ответе API поиска.");
        checkSearchInspections(accTValue, SurveillanceItemsList::getKnoOrganization, "Несоответствие организации КНО в ответе API поиска.");
        checkSearchInspections(accTValue, SurveillanceItemsList::getStatusName, "Несоответствие статуса проверки в ответе API поиска.");
        checkSearchInspections(accTValue, SurveillanceItemsList::getTypeName, "Несоответствие наименования типа проверки в ответе API поиска.");
    }

    public void checkSearchInspections(String accTValue, Function<SurveillanceItemsList, String> getField, String errorMessage) {
        List<SurveillanceItemsList> erknmInspectionsList = getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "all").getList();

        List<String> values = erknmInspectionsList.stream()
                .map(getField)
                .collect(Collectors.toList());

        for (String value : values) {
            List<SurveillanceItemsList> inspectionList = getErknmInspectionSearch(accTValue, 10, "desc", "all", value).getList();

            List<String> valuesFromApi = inspectionList.stream()
                    .map(getField)
                    .collect(Collectors.toList());

            boolean matchFound = valuesFromApi.stream().anyMatch(testValue -> testValue.contains(value));
            assertTrue(matchFound, errorMessage);
        }
    }

    @Test
    @DisplayName("Поиск ЕРКНМ проверок по номеру, типу проверки, типу контроля, организации КНО и статусу для юридических лиц")
    public void checkSearchKNMInspectionsUL() {
        checkValidateInspectionsSearchResults(accTValueUl);
    }

    @Test
    @DisplayName("Поиск ЕРКНМ проверок по номеру, типу проверки, типу контроля, организации КНО и статусу для физических лиц")
    public void  checkSearchKNMInspectionsFL() {
        checkValidateInspectionsSearchResults(accTValueFl);
    }

    @Test
    @DisplayName("Поиск ЕРКНМ проверок по номеру, типу проверки, типу контроля, организации КНО и статусу для индивидуальных предпринимателей")
    public void checkSearchKNMInspectionsIP() {
        checkValidateInspectionsSearchResults(accTValueIp);
    }

}
