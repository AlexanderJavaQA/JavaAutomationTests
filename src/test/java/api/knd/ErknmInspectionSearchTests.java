package api.knd;

import api.ErknmInspectionsListService;
import apimodels.erknm.SurveillanceItemsList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static api.ErknmInspectionSearchService.getErknmInspectionSearch;
import static api.ErknmInspectionSortService.getErknmInspectionsSort;

@Tag("API")
@DisplayName("Поиск ЕРКНМ проверок в личном кабинете")
public class ErknmInspectionSearchTests extends BaseApiTests {

    public static final String SORT_ORDER_DESC = "desc";
    public static final String SORT_ORDER_ASC = "asc";

    public void shouldSearchKNMInspections(String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "KNM").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());
        System.out.printf("ЕРКНМ проверок всего: %d %s%n", erknmIdList.size(), erknmIdList.toString());

        for (String erknmId : erknmIdList) {
            getErknmInspectionSearch(accTValue, 10, "desc", "KNM", erknmId);
            for (SurveillanceItemsList item : erknmInspectionsList) {
                if (item.getErknmId().equals(erknmId)) {
                    System.out.println("ЕРКНМ проверка найдена: " + erknmId);
                }
            }
        }
    }

    public void shouldSearchPMInspections(String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "PM").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());
        System.out.printf("Профмероприятий всего: %d %s%n", erknmIdList.size(), erknmIdList.toString());

        for (String erknmId : erknmIdList) {
            getErknmInspectionSearch(accTValue, 10, "desc", "PM", erknmId);
            for (SurveillanceItemsList item : erknmInspectionsList) {
                if (item.getErknmId().equals(erknmId)) {
                    System.out.println("Профмероприятие найдено: " + erknmId);
                }
            }
        }
    }

    @Test
    @DisplayName("Поиск КНМ проверок для юридических лиц")
    public void shouldSearchKNMInspectionsForUL() {
        shouldSearchKNMInspections(accTValueUl);
    }

    @Test
    @DisplayName("Поиск КНМ проверок для физических лиц")
    public void shouldSearchKNMInspectionsForFL() {
        shouldSearchKNMInspections(accTValueFl);
    }

    @Test
    @DisplayName("Поиск КНМ проверок для индивидуальных предпринимателей")
    public void shouldSearchKNMInspectionsForIP() {
        shouldSearchKNMInspections(accTValueIp);
    }

    @Test
    @DisplayName("Поиск профмероприятий для юридических лиц")
    public void shouldSearchPMInspectionsForUL() {
        shouldSearchPMInspections(accTValueUl);
    }

    @Test
    @DisplayName("Поиск профмероприятий для физических лиц")
    public void shouldSearchPMInspectionsForFL() {
        shouldSearchPMInspections(accTValueFl);
    }

    @Test
    @DisplayName("Поиск профмероприятий для индивидуальных предпринимателей")
    public void shouldSearchPMInspectionsForIP() {
        shouldSearchPMInspections(accTValueIp);
    }

}
