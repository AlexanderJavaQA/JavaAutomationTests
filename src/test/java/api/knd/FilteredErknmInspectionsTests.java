package api.knd;

import api.FilteredErknmInspectionsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import apimodels.erknm.KindList;
import apimodels.erknm.StatusList;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("API")
@DisplayName("Проверка фильтрации ЕРКНМ карточек")
public class FilteredErknmInspectionsTests extends BaseApiTests {

    public void checkFilteredErknmInspections(String accountTypeValue) {

        // Получение списка статусов
        List<StatusList> statusList = FilteredErknmInspectionsService.getFilteredErknmInspections(accountTypeValue).getStatusList();

        List<Integer> expectedIds = Arrays.asList(5, 6, 7, 8, 9, 21, 22, 23, 24);

        List<String> expectedCodes = Arrays.asList(
                "TYPE_WAITING_CARRY_OUT",
                "TYPE_CARRIED_OUT",
                "TYPE_COMPLETED",
                "DECISION_APPEALED",
                "REJECT",
                "APPEALED",
                "TYPE_CANT_BE_CARRY_OUT",
                "REMARK"
        );

        List<String> expectedNames = Arrays.asList(
                "Ожидает проведения",
                "Ожидает завершения",
                "Завершено",
                "Решение обжаловано",
                "Отказ в проведении",
                "Есть возражение",
                "Не может быть проведено",
                "Предостережение объявлено"
        );

        // Вывод значений для отладки
        statusList.stream().map(StatusList::getId).forEach(System.out::println);
        statusList.stream().map(StatusList::getName).forEach(System.out::println);
        statusList.stream().map(StatusList::getCode).forEach(System.out::println);

        // Проверка, что все ID, имена и коды соответствуют ожидаемым
        statusList.stream().map(StatusList::getId).forEach(id -> assertTrue(expectedIds.contains(id)));
        statusList.stream().map(StatusList::getName).forEach(name -> assertTrue(expectedNames.contains(name)));
        statusList.stream().map(StatusList::getCode).forEach(code -> assertTrue(expectedCodes.contains(code)));

        // Подробная проверка каждого статуса
        assertStatusMatches(statusList, 5, "TYPE_WAITING_CARRY_OUT", "Ожидает проведения");
        assertStatusMatches(statusList, 6, "TYPE_CARRIED_OUT", "Ожидает завершения");
        assertStatusMatches(statusList, 7, "TYPE_COMPLETED", "Завершено");
        assertStatusMatches(statusList, 21, "DECISION_APPEALED", "Решение обжаловано");
        assertStatusMatches(statusList, 22, "REJECT", "Отказ в проведении");
        assertStatusMatches(statusList, 24, "APPEALED", "Есть возражение");

        // Получение списка видов
        List<KindList> kindList = FilteredErknmInspectionsService.getFilteredErknmInspections(accountTypeValue).getKindList();

        // Вывод кодов для отладки
        kindList.stream()
                .map(KindList::getCode)
                .forEach(System.out::println);

        List<String> expectedKindCodes = Arrays.asList("KNM", "PM", "PLANNED", "UNPLANNED");
        kindList.stream().map(KindList::getCode).forEach(code -> assertTrue(expectedKindCodes.contains(code)));

        List<String> expectedKindNames = Arrays.asList("Контрольные мероприятия", "Профилактические мероприятия", "Плановый", "Внеплановый");
        kindList.stream().map(KindList::getName).forEach(name -> assertTrue(expectedKindNames.contains(name)));

        // Подробная проверка каждого вида
        assertKindMatches(kindList, "KNM", "Контрольные мероприятия");
        assertKindMatches(kindList, "PM", "Профилактические мероприятия");
        assertKindMatches(kindList, "PLANNED", "Плановый");
        assertKindMatches(kindList, "UNPLANNED", "Внеплановый");

        // Дополнительная фильтрация и вывод для кода "PM"
        kindList.stream()
                .filter(kind -> "PM".equals(kind.getCode()))
                .forEach(System.out::println);
    }

    private void assertKindMatches(List<KindList> kindList, String code, String expectedName) {
        assertTrue(kindList.stream()
                .filter(kind -> code.equals(kind.getCode()))
                .allMatch(kind -> expectedName.equals(kind.getName())));
    }

    private void assertStatusMatches(List<StatusList> statusList, int id, String code, String expectedName) {
        assertTrue(statusList.stream()
                .filter(status -> status.getId() == id && code.equals(status.getCode()))
                .allMatch(status -> expectedName.equals(status.getName())));
    }

    @Test
    @DisplayName("Должен фильтровать ЕРКНМ карточки для юридических лиц")
    public void checkFilterErknmInspectionsUL() {
        checkFilteredErknmInspections(accTValueUl);
    }

    @Test
    @DisplayName("Должен фильтровать ЕРКНМ карточки для физических лиц ")
    public void checkFilterErknmInspectionsFL() {
        checkFilteredErknmInspections(accTValueFl);
    }

    @Test
    @DisplayName("Должен фильтровать ЕРКНМ карточки для индивидуальных предпринимателей")
    public void checkFilterErknmInspectionsIP() {
        checkFilteredErknmInspections(accTValueIp);
    }


}