package api.doknd;

import api.knd.BaseApiTests;
import apimodels.appeals.ItemsItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import springjdbc.postgres.models.StatusCode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static api.DraftsListService.getDraftsList;
import static api.doknd.SearchForAppealTests.SORT_ORDER_ASC;
import static api.doknd.SearchForAppealTests.SORT_ORDER_DESC;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка отображения и сортировки списка черновиков")
public class DraftsListTests extends BaseApiTests {




    public void shouldVerifyDraftsListIsSortedInDescendingOrder(String accTValue) {
        List<ItemsItem> dateLists = getDraftsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> dates = dateLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void shouldVerifyDraftsListIsSortedInAscendingOrder(String accTValue) {
        List<ItemsItem> dateLists = getDraftsList(accTValue, SORT_ORDER_ASC).getItems();
        List<String> dates = dateLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.naturalOrder());
        assertTrue(dates.equals(sortedDates));
    }


    public void verifyStatusCodeEqualsZeroForAscendingOrderDrafts(String accTValue) {
        List<ItemsItem> dateLists = getDraftsList(accTValue, SORT_ORDER_ASC).getItems();
        List<String> extIds = dateLists.stream()
                .map(ItemsItem::getExtId)
                .collect(Collectors.toList());

        for (String extId : extIds) {
            List<StatusCode> statusCodes = new DataAccessObjectPostgres(DatabaseConnectPostgres
                    .surveillanceDatabaseDev2()).getStatusCodeByOrderId(extId);
            assertFalse(statusCodes.isEmpty(), "Список statusCode не должен быть пустым для extId: " + extId);
            assertEquals("0", statusCodes.get(0).getStatus_code(), "Проверка, что statusCode равен 0 для extId: " + extId);
        }
    }

    @Test
    @DisplayName("Проверка, что statusCode равен нулю в разделе Мои черновики для Юридического лица")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderUL() {
        verifyStatusCodeEqualsZeroForAscendingOrderDrafts(accTValueUl);
    }

    @Test
    @DisplayName("Проверка, что statusCode равен нулю в разделе Мои черновики для Индивидульного предпиринимателя")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderIP() {
        verifyStatusCodeEqualsZeroForAscendingOrderDrafts(accTValueIp);
    }

    @Test
    @DisplayName("Проверка, что statusCode равен нулю в разделе Мои черновики для Физического лица")
    public void shouqwldVerifyApplsListIsSortedInAscendingOrderFL() {
        verifyStatusCodeEqualsZeroForAscendingOrderDrafts(accTValueFl);
    }


    @Test
    @DisplayName("Проверка сортировки черновиков для Юридических Лиц по возрастанию")
    public void shouldVerifyDraftsSortedAscUL() {
        shouldVerifyDraftsListIsSortedInAscendingOrder(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков для Индивидуальных Предпринимателей по возрастанию")
    public void shouldVerifyDraftsSortedAscIP() {
        shouldVerifyDraftsListIsSortedInAscendingOrder(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков для Физических Лиц по возрастанию")
    public void shouldVerifyDraftsSortedAscFL() {
        shouldVerifyDraftsListIsSortedInAscendingOrder(accTValueFl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков для Юридических Лиц по убыванию")
    public void shouldVerifyDraftsSortedDescUL() {
        shouldVerifyDraftsListIsSortedInDescendingOrder(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков для Индивидуальных Предпринимателей по убыванию")
    public void shouldVerifyDraftsSortedDescIP() {
        shouldVerifyDraftsListIsSortedInDescendingOrder(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков для Физических Лиц по убыванию")
    public void shouldVerifyDraftsSortedDescFL() {
        shouldVerifyDraftsListIsSortedInDescendingOrder(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков для Юридических Лиц")
    public void shouldDisplayDraftsListForUL() {
        getDraftsList(accTValueUl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков для Физических Лиц")
    public void shouldDisplayDraftsListForFL() {
        getDraftsList(accTValueFl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков  для Индивидуальных Предпринимателей")
    public void shouldDisplayDraftsListForIP() {
        getDraftsList(accTValueIp, SORT_ORDER_DESC);
    }
}
