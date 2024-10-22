package api.doknd;

import api.ErknmInspectionSortService;
import api.ErknmInspectionsListService;
import api.knd.BaseApiTests;
import apimodels.erknm.SurveillanceItemsList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.ErknmInspectionSearchService.getErknmInspectionSearch;
import static api.SearchForAppealService.getSearchForAppeal;

@Tag("SmokeAPI")
@DisplayName("Поиск ЕРКНМ проверок для досудебного оббжалования")
public class SearchForAppealTests extends BaseApiTests {
    public static final String SORT_ORDER_DESC = "desc";
    public static final String SORT_ORDER_ASC = "asc";

    private static final List<Integer> APPEAL_ID = Arrays.asList(
            1,
            2,
            3,
            4,
            5,
            10,
            11,
            13);

    public void searchKNMInspectionsForAppeals(String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionsListService.getErknmInspectionsList(accTValue, 50, SORT_ORDER_DESC, "KNM", "").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

        for (Integer appealId : APPEAL_ID) {
            for (String erknmId : erknmIdList) {
                getSearchForAppeal(accTValue, erknmId, String.valueOf(appealId));
            }
        }
    }

    public void searchPMInspectionsForAppeals(String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValue, 50, SORT_ORDER_DESC, "PM").getList();
        List<String> pmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

            for (String erknmId : pmIdList) {
                getSearchForAppeal(accTValue, erknmId, "14");
            }
    }


    @Test
    @DisplayName("Поиск КНМ проверок для разных ЖС для ЮЛ")
    public void shouldSearchKNMForUL() {
        searchKNMInspectionsForAppeals(accTValueUl);
    }

    @Test
    @DisplayName("Поиск КНМ проверок для разных ЖС для ФЛ")
    public void shouldSearchKNMForFL() {
        searchKNMInspectionsForAppeals(accTValueFl);
    }

    @Test
    @DisplayName("Поиск КНМ проверок для разных ЖС для индивидуальных предпринимателей")
    public void shouldSearchKNMForIP() {
        searchKNMInspectionsForAppeals(accTValueIp);
    }

    @Test
    @DisplayName("Поиск профмероприятий для ЮЛ")
    public void shouldSearchPMForUL() {
        searchPMInspectionsForAppeals(accTValueUl);
    }

    @Test
    @DisplayName("Поиск профмероприятий для ФЛ")
    public void shouldSearchPMForFL() {
        searchPMInspectionsForAppeals(accTValueFl);
    }

    @Test
    @DisplayName("Поиск профмероприятий для ИП")
    public void shouldSearchPMForIP() {
        searchPMInspectionsForAppeals(accTValueIp);
    }

}
