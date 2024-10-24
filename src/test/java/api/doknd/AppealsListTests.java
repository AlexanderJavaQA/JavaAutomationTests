package api.doknd;

import api.ErknmInspectionsListService;
import api.knd.BaseApiTests;
import apimodels.appeals.Items;
import apimodels.appeals.ItemsItem;
import apimodels.erknm.SurveillanceItemsList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static api.AppealsListService.getAppealsList;
import static api.SearchForAppealService.getSearchForAppeal;
import static api.doknd.SearchForAppealTests.SORT_ORDER_ASC;
import static api.doknd.SearchForAppealTests.SORT_ORDER_DESC;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Проверка отображения списка жалоб")
public class AppealsListTests extends BaseApiTests {
    public void shouldVerifyAppealsListIsSortedInDescendingOrder(String accTValue) {
        List<ItemsItem> dateLists = getAppealsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> dates = dateLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void shouldVerifyAppealsListIsSortedInAscendingOrder(String accTValue) {
        List<ItemsItem> dateLists = getAppealsList(accTValue, SORT_ORDER_ASC).getItems();
        List<String> dates = dateLists.stream()
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
