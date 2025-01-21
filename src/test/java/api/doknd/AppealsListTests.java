package api.doknd;

import api.knd.BaseApiTests;
import apimodels.appeals.ItemsItem;
import apimodels.erknm.SurveillanceItemsList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import springjdbc.postgres.models.StatusCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static api.AppealsListService.getAppealsList;
import static api.AppealsSearchService.getAppealsSearch;
import static api.doknd.SearchForAppealTests.SORT_ORDER_ASC;
import static api.doknd.SearchForAppealTests.SORT_ORDER_DESC;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка отображения и сортировки списка жалоб")
public class AppealsListTests extends BaseApiTests {

    public void checkAppealsSearch(String accTValue, Function<ItemsItem, String> getField, String errorMessage) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();

        List<String> values = appealLists.stream()
                .map(getField)
                .collect(Collectors.toList());

        for (String value : values) {
            List<ItemsItem> searchResults = (List<ItemsItem>) getAppealsSearch(accTValue, SORT_ORDER_DESC, value);

            List<String> valuesFromApi = searchResults.stream()
                    .map(getField)
                    .collect(Collectors.toList());

            for (String testValue : valuesFromApi) {
                assertTrue(testValue.contains(value), errorMessage);
            }
        }
    }

    /**
     * Проверяет корректность поиска жалоб по номеру проверки для заданного значения `accTValue`.
     */
    public void checkSearchByCheckNumber(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> checkNumbers = appealLists.stream()
                .filter(x -> x.getData() != null && x.getData().getCheckNumber() != null)
                .map(x -> x.getData().getCheckNumber()).collect(Collectors.toList());

        for (String checkNumber : checkNumbers) {
            List<ItemsItem> searchResults = (List<ItemsItem>) getAppealsSearch(accTValue, SORT_ORDER_DESC, checkNumber);
            assertTrue(searchResults.stream()
                            .anyMatch(result ->
                                    result.getData().getCheckNumber() != null && result.getData()
                                            .getCheckNumber()
                                            .contains(checkNumber)),
                    "Проверяем, что в результатах поиска API присутствует номер проверки, который содержит искомое значение checkNumber");
        }

        checkAppealsSearch(accTValue, ItemsItem::getExtId, "error");
    }

    public void checkSearchByControlName(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> controlNames = appealLists.stream()
                .filter(x -> x.getData() != null && x.getData().getControlName() != null)
                .map(x -> x.getData().getControlName()).collect(Collectors.toList());

        for (String controlName : controlNames) {
            List<ItemsItem> searchResults = (List<ItemsItem>) getAppealsSearch(accTValue, SORT_ORDER_DESC, controlName);
            assertTrue(searchResults.stream()
                            .anyMatch(result ->
                                    result.getData().getControlName() != null && result.getData().getControlName()
                                            .contains(controlName)),
                    "Проверяем, что в результатах поиска API присутствует ВК, который содержит искомое значение controlName");
        }
    }

    public void checkSearchByKnoName(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> knoNames = appealLists.stream()
                .filter(x -> x.getData() != null && x.getData().getKnoName() != null)
                .map(x -> x.getData().getKnoName()).collect(Collectors.toList());

        for (String knoName : knoNames) {
            List<ItemsItem> searchResults = (List<ItemsItem>) getAppealsSearch(accTValue, SORT_ORDER_DESC, knoName);
            assertTrue(searchResults.stream()
                            .anyMatch(result ->
                                    result.getData().getKnoName() != null && result.getData().getKnoName()
                                            .contains(knoName)),
                    "Проверяем, что в результатах поиска API присутствует КНО, который содержит искомое значение knoName");
        }
    }

    public void checkStatusCodeNotZeroForDescOrder(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> extIds = appealLists.stream()
                .map(ItemsItem::getExtId)
                .collect(Collectors.toList());

        for (String extId : extIds) {
            List<StatusCode> statusCodes = new DataAccessObjectPostgres(DatabaseConnectPostgres
                    .surveillanceDatabaseUat()).getStatusCodeByOrderId(extId);

            assertFalse(statusCodes.isEmpty(), "Список statusCode не должен быть пустым для extId: " + extId);
            assertNotEquals("0", statusCodes.get(0), "Проверка, что statusCode равен 0 для extId: " + extId);
        }
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю для Юридического лица")
    public void checkStatusCodeNotZeroUL() {
        checkStatusCodeNotZeroForDescOrder(accTValueUl);
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю для Индивидуального предпринимателя")
    public void checkStatusCodeNotZeroIP() {
        checkStatusCodeNotZeroForDescOrder(accTValueIp);
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю для Физического лица")
    public void checkStatusCodeNotZeroFL() {
        checkStatusCodeNotZeroForDescOrder(accTValueFl);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Юридических Лиц")
    public void checkAppealsSearchUL() {
        checkSearchByCheckNumber(accTValueUl);
        checkSearchByKnoName(accTValueUl);
        checkSearchByControlName(accTValueUl);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Индивидуальных Предпринимателей")
    public void checkAppealsSearchIP() {
        checkSearchByCheckNumber(accTValueIp);
        checkSearchByKnoName(accTValueIp);
        checkSearchByControlName(accTValueIp);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Физических Лиц")
    public void checkAppealsSearchFL() {
        checkSearchByCheckNumber(accTValueFl);
        checkSearchByKnoName(accTValueFl);
        checkSearchByControlName(accTValueFl);
    }

    public void checkAppealsListSortedDesc(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> dates = appealLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void checkAppealsListSortedAsc(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_ASC).getItems();
        List<String> dates = appealLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.naturalOrder());
        assertTrue(dates.equals(sortedDates));
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по возрастанию для Юридических Лиц")
    public void checkAppealsListSortedAscUL() {
        checkAppealsListSortedAsc(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по возрастанию для Индивидуальных Предпринимателей")
    public void checkAppealsListSortedAscIP() {
        checkAppealsListSortedAsc(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по возрастанию для Физических Лиц")
    public void checkAppealsListSortedAscFL() {
        checkAppealsListSortedAsc(accTValueFl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по убыванию для Юридических Лиц")
    public void checkAppealsListSortedDescUL() {
        checkAppealsListSortedDesc(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по убыванию для Индивидуальных Предпринимателей")
    public void checkAppealsListSortedDescIP() {
        checkAppealsListSortedDesc(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб по убыванию для Физических Лиц")
    public void checkAppealsListSortedDescFL() {
        checkAppealsListSortedDesc(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Юридических Лиц")
    public void checkDisplayAppealsListUL() {
        getAppealsList(accTValueUl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Физических Лиц")
    public void checkDisplayAppealsListFL() {
        getAppealsList(accTValueFl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Индивидуальных Предпринимателей")
    public void checkDisplayAppealsListIP() {
        getAppealsList(accTValueIp, SORT_ORDER_DESC);
    }

}
