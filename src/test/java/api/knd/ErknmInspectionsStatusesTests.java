package api.knd;

import apimodels.erknm.SurveillanceItemsList;
import lombok.SneakyThrows;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.ErknmInspectionSortService.getErknmInspectionsSort;
import static api.knd.ErknmInspectionsListTests.SORT_ORDER_DESC;
import static api.ErknmInspectionsStatusesService.getErknmInspectionsStatuses;

@Tag("API")
@DisplayName("Проверка фильтрации по статусу ЕРКНМ проверок")
public class ErknmInspectionsStatusesTests extends BaseApiTests {

    private static final List<String> STATUS_NAME_LIST = Arrays.asList(
            "Все",
            "Ожидает проведения",
            "Ожидает завершения",
            "Завершено",
            "Не может быть проведено",
            "Решение обжаловано",
            "Отказ в проведении",
            "Предостережение объявлено",
            "Есть возражение");


    private void checkValidateErknmInspectionStatuses(String accountType) {
        SoftAssertions softAssertions = new SoftAssertions();

        checkErknmInspectionStatus(softAssertions, accountType, "Ожидает проведения", "5");
        checkErknmInspectionStatus(softAssertions, accountType, "Ожидает проведения", "5");
        checkErknmInspectionStatus(softAssertions, accountType, "Ожидает завершения", "6");
        checkErknmInspectionStatus(softAssertions, accountType, "Завершено", "7");
        checkErknmInspectionStatus(softAssertions, accountType, "Не может быть проведено", "8");
        checkErknmInspectionStatus(softAssertions, accountType, "Решение обжаловано", "21");
        checkErknmInspectionStatus(softAssertions, accountType, "Отказ в проведении", "22");
        checkErknmInspectionStatus(softAssertions, accountType, "Предостережение объявлено", "23");
        checkErknmInspectionStatus(softAssertions, accountType, "Есть возражение", "24");

        softAssertions.assertAll();
    }

    private void checkErknmInspectionStatus(SoftAssertions softAssertions, String accountType, String statusName, String statusId) {
        List<SurveillanceItemsList> allInspectionList = getErknmInspectionsSort(accountType, 30, SORT_ORDER_DESC, "all").getList();

        List<String> matchedInspectionStatuses = allInspectionList.stream()
                .map(SurveillanceItemsList::getStatusName)
                .filter(status -> statusName.equals(status))
                .collect(Collectors.toList());

        int matchedStatusCount = matchedInspectionStatuses.size();

        List<SurveillanceItemsList> filteredInspectionList = getErknmInspectionsStatuses(accountType, 30, SORT_ORDER_DESC, statusId).getList();
        int filteredInspectionCount = filteredInspectionList.size();

        matchedInspectionStatuses.forEach(status -> System.out.println("Matched Inspection Status: " + status));
        filteredInspectionList.forEach(inspection -> System.out.println("Filtered Inspection: " + inspection.getStatusName()));

        filteredInspectionList.stream()
                .map(SurveillanceItemsList::getStatusName)
                .forEach(status ->
                        softAssertions.assertThat(status)
                                .as("Проверка, что статус '%s' содержится в списке допустимых статусов", status)
                                .isIn(STATUS_NAME_LIST)
                );
    }

    @SneakyThrows
    @Test
    @DisplayName("Проверка фильтрации по статусу ЕРКНМ проверок для UL")
    public void checkValidateErknmInspectionStatusesUL() {
        checkValidateErknmInspectionStatuses(accTValueUl);
    }

    @SneakyThrows
    @Test
    @DisplayName("Проверка фильтрации по статусу ЕРКНМ проверок для FL")
    public void checkValidateErknmInspectionStatusesFL() {
        checkValidateErknmInspectionStatuses(accTValueFl);
    }

    @SneakyThrows
    @Test
    @DisplayName("Проверка фильтрации по статусу ЕРКНМ проверок для IP")
    public void checkValidateErknmInspectionStatusesIP() {
        checkValidateErknmInspectionStatuses(accTValueIp);
    }
}
