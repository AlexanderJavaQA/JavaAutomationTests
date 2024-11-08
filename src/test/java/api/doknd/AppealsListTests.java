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

    public void shouldSearchAppeals(String accTValue, Function<ItemsItem, String> getField, String errorMessage) {
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
                assertTrue(testValue.contains(value),
                        errorMessage);
            }
        }
    }

    /**
     * Метод verifyAppealsSearchByCheckNumber проверяет, что поиск жалоб по номеру проверки работает корректно для заданного значения `accTValue`.
     * Для каждой жалобы из списка выполняется API-запрос поиска по номеру проверки, и проверяется, что
     * в результатах поиска содержится искомый номер проверки (через проверку `contains`).
     *
     * @param accTValue тип учетной записи (например, Юридическое Лицо, Индивидуальный Предприниматель, Физическое Лицо)
     */
    public void verifyAppealsSearchByCheckNumber(String accTValue) {
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

        shouldSearchAppeals(accTValue, ItemsItem::getExtId, "error");
    }

    public void verifyAppealsSearchByControlNamee(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> controlNames = appealLists.stream()
                .filter(x -> x.getData() != null && x.getData().getControlName() != null) // Фильтруем элементы, у которых checkNumber не равен null
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

    public void verifyAppealsSearchByKnoName(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> knoNames = appealLists.stream()
                .filter(x -> x.getData() != null && x.getData().getKnoName() != null) // Фильтруем элементы, у которых checkNumber не равен null
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

    public void verifyStatusCodeNotZeroForDescendingOrderAppeals(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> extIds = appealLists.stream()
                .map(ItemsItem::getExtId)
                .collect(Collectors.toList());

        for (String extId : extIds) {
            List<StatusCode> statusCodes = new DataAccessObjectPostgres(DatabaseConnectPostgres
                    .surveillanceDatabaseDev2()).getStatusCodeByOrderId(extId);

            assertFalse(statusCodes.isEmpty(), "Список statusCode не должен быть пустым для extId: " + extId);
            assertNotEquals("0", statusCodes.get(0), "Проверка, что statusCode равен 0 для extId: " + extId);
        }
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю в разделе Мои жалобы для Юридического лица")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderUL() {
        verifyStatusCodeNotZeroForDescendingOrderAppeals(accTValueUl);
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю в разделе Мои жалобы для Индивидульного предпиринимателя")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderIP() {
        verifyStatusCodeNotZeroForDescendingOrderAppeals(accTValueIp);
    }

    @Test
    @DisplayName("Проверка, что statusCode не равен нулю в разделе Мои жалобы для Физического лица")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderFL() {
        verifyStatusCodeNotZeroForDescendingOrderAppeals(accTValueFl);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Юридических Лиц по возрастанию")
    public void verifyAppealsSortedAscUL() {
        verifyAppealsSearchByCheckNumber(accTValueUl);
        verifyAppealsSearchByKnoName(accTValueUl);
        verifyAppealsSearchByControlNamee(accTValueUl);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Индивидуальных Предпринимателей по возрастанию")
    public void verifyAppealsSortedAscIP() {
        verifyAppealsSearchByCheckNumber(accTValueIp);
        verifyAppealsSearchByKnoName(accTValueIp);
        verifyAppealsSearchByControlNamee(accTValueIp);
    }

    @Test
    @DisplayName("Проверка поиска жалоб по номеру проверки, КНО и ВК для Физических Лиц по возрастанию")
    public void verifyAppealsSortedAscFL() {
        verifyAppealsSearchByCheckNumber(accTValueFl);
        verifyAppealsSearchByKnoName(accTValueFl);
        verifyAppealsSearchByControlNamee(accTValueFl);
    }


    public void shouldVerifyAppealsListIsSortedInDescendingOrder(String accTValue) {
        List<ItemsItem> appealLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> dates = appealLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void shouldVerifyAppealsListIsSortedInAscendingOrder(String accTValue) {
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
    @DisplayName("Проверка сортировки списка жалоб для Юридических Лиц по возрастанию")
    public void shouldVerifyAppealsListIsSortedInAscendingOrderUL() {
        shouldVerifyAppealsListIsSortedInAscendingOrder(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб для Индивидуальных Предпринимателей по возрастанию")
    public void shouldVerifyAppealsListIsSortedInAscendingOrderIP() {
        shouldVerifyAppealsListIsSortedInAscendingOrder(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб для Физических Лиц по возрастанию")
    public void shouldVerifyAppealsListIsSortedInAscendingOrderFL() {
        shouldVerifyAppealsListIsSortedInAscendingOrder(accTValueFl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб для Юридических Лиц по убыванию")
    public void shouldVerifyAppealsListIsSortedInDescendingOrderUL() {
        shouldVerifyAppealsListIsSortedInDescendingOrder(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб для Индивидуальных Предпринимателей по убыванию")
    public void shouldVerifyAppealsListIsSortedInDescendingOrderIP() {
        shouldVerifyAppealsListIsSortedInDescendingOrder(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки списка жалоб для Физических Лиц по убыванию")
    public void shouldVerifyAppealsListIsSortedInDescendingOrderFL() {
        shouldVerifyAppealsListIsSortedInDescendingOrder(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Юридических Лиц")
    public void shouldDisplayAppealsListForUL() {
        getAppealsList(accTValueUl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Физических Лиц")
    public void shouldDisplayAppealsListForFL() {
        getAppealsList(accTValueFl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка жалоб для Индивидуальных Предпринимателей")
    public void shouldDisplayAppealsListForIP() {
        getAppealsList(accTValueIp, SORT_ORDER_DESC);
    }
}
