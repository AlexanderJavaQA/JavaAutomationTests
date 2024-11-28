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


    public void checkDraftsSortedDesc(String accTValue) {
        List<ItemsItem> dateLists = getDraftsList(accTValue, SORT_ORDER_DESC).getItems();
        List<String> dates = dateLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void checkDraftsSortedAsc(String accTValue) {
        List<ItemsItem> dateLists = getDraftsList(accTValue, SORT_ORDER_ASC).getItems();
        List<String> dates = dateLists.stream()
                .map(ItemsItem::getDate)
                .collect(Collectors.toList());
        dates.forEach(System.out::println);

        List<String> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.naturalOrder());
        assertTrue(dates.equals(sortedDates));
    }

    public void checkStatusCodeZeroForDrafts(String accTValue) {
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
    @DisplayName("Проверка, что statusCode равен нулю для Юридического лица")
    public void checkStatusCodeZeroUL() {
        checkStatusCodeZeroForDrafts(accTValueUl);
    }

    @Test
    @DisplayName("Проверка, что statusCode равен нулю для Индивидуального предпринимателя")
    public void checkStatusCodeZeroIP() {
        checkStatusCodeZeroForDrafts(accTValueIp);
    }

    @Test
    @DisplayName("Проверка, что statusCode равен нулю для Физического лица")
    public void checkStatusCodeZeroFL() {
        checkStatusCodeZeroForDrafts(accTValueFl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по возрастанию для Юридических Лиц")
    public void checkDraftsSortedAscUL() {
        checkDraftsSortedAsc(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по возрастанию для Индивидуальных Предпринимателей")
    public void checkDraftsSortedAscIP() {
        checkDraftsSortedAsc(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по возрастанию для Физических Лиц")
    public void checkDraftsSortedAscFL() {
        checkDraftsSortedAsc(accTValueFl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по убыванию для Юридических Лиц")
    public void checkDraftsSortedDescUL() {
        checkDraftsSortedDesc(accTValueUl);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по убыванию для Индивидуальных Предпринимателей")
    public void checkDraftsSortedDescIP() {
        checkDraftsSortedDesc(accTValueIp);
    }

    @Test
    @DisplayName("Проверка сортировки черновиков по убыванию для Физических Лиц")
    public void checkDraftsSortedDescFL() {
        checkDraftsSortedDesc(accTValueFl);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков для Юридических Лиц")
    public void checkDisplayDraftsUL() {
        getDraftsList(accTValueUl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков для Физических Лиц")
    public void checkDisplayDraftsFL() {
        getDraftsList(accTValueFl, SORT_ORDER_DESC);
    }

    @Test
    @DisplayName("Проверка отображения списка черновиков для Индивидуальных Предпринимателей")
    public void checkDisplayDraftsIP() {
        getDraftsList(accTValueIp, SORT_ORDER_DESC);
    }

}
