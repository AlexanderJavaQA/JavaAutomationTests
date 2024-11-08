package api.knd;

import api.ErknmInspectionSortService;
import api.ErknmInspectionsListService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import apimodels.erknm.SurveillanceItemsList;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static api.KnmInspectionsListService.getKnmInspectionsList;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("API")
@DisplayName("Проверка сортировки, списка и детальной карточки ЕРКНМ")
public class ErknmInspectionsListTests extends BaseApiTests {

    public static final String SORT_ORDER_DESC = "desc";
    public static final String SORT_ORDER_ASC = "asc";
    public static Integer total = null;

    private static final List<String> TYPE_KNM_LIST = Arrays.asList("Контрольная закупка",
            "Мониторинговая закупка",
            "Выборочный контроль",
            "Инспекционный визит",
            "Рейдовый осмотр",
            "Документарная проверка",
            "Выездная проверка",
            "Наблюдение за соблюдением обязательных требований",
            "Контрольная закупка",
            "Постоянный государственный контроль (надзор)");

    private static final List<String> TYPE_PM_LIST = Arrays.asList(
            "Профилактический визит",
            "Объявление предостережения");

    private static final List<String> PLANNED_TYPE = Arrays.asList(
            "Плановое КНМ",
            "Плановая проверка",
            "Плановая проверка по 248-ФЗ (утвержденная по плану 294-ФЗ)");

    private static final List<String> UNPLANNED_TYPE = Arrays.asList(
            "Внеплановое КНМ",
            "Внеплановая проверка");


    public void checkTypeNameAndKindErknmInspections(String accTValue) {

        assertKindList("KNM", 30, TYPE_KNM_LIST, accTValue);
        assertKindList("PM", 30, TYPE_PM_LIST, accTValue);
        assertTypeNameList("PLANNED", 30, PLANNED_TYPE, accTValue);
        assertTypeNameList("UNPLANNED", 30, UNPLANNED_TYPE, accTValue);
    }

    private void assertKindList(String type, Integer total, List<String> typeKind, String accTValue) {

        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValue, total, SORT_ORDER_DESC, type).getList();
        erknmInspectionsList.stream().map(SurveillanceItemsList::getKind).filter(kind -> kind != null).forEach(System.out::println);

        erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getKind)
                .forEach(kind -> assertTrue(typeKind.contains(kind), "Не фильтрует по типу проверки"));
    }

    private void assertTypeNameList(String type, Integer total, List<String> typeName, String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValue, total, SORT_ORDER_DESC, type).getList();
        erknmInspectionsList.stream().map(SurveillanceItemsList::getTypeName).filter(kind -> kind != null).forEach(System.out::println);

        erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getTypeName)
                .filter(x -> x != null)
                .forEach(x -> assertTrue(typeName.contains(x), "Не фильтрует по типу проверки"));
    }


    public void checkOpenKNMInspectionDetailsCard(String accTValue) {

        // Получение списка erknmId для КНМ
        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "KNM").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

        // Вызов метода getKnmInspectionsListApi для каждого erknmId
        for (String erknmId : erknmIdList) {
            System.out.println(erknmId);
            getKnmInspectionsList(accTValue, erknmId);
            for (SurveillanceItemsList item : erknmInspectionsList) {
                if (item.getErknmId().equals(erknmId)) {
                    System.out.println(item.getAppealKnm());
                }
            }
        }
    }


    public void checkOpenPMInspectionDetailsCard(String accTValue) {

        // Получение списка erknmId для КНМ
        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "PM").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

        // Вызов метода getKnmInspectionsListApi для каждого erknmId
        for (String erknmId : erknmIdList) {
            getKnmInspectionsList(accTValue, erknmId);
            System.out.println(erknmId);
            for (SurveillanceItemsList item : erknmInspectionsList) {
                if (item.getErknmId().equals(erknmId)) {
                    System.out.println(item.getAppealPreventiveVisit());
                }
            }
        }
    }


    public void verifyErknmInspectionListSorting(String accTValueType) {

        List<SurveillanceItemsList> erknmInspectionsList = ErknmInspectionSortService.getErknmInspectionsSort(accTValueType, 10, SORT_ORDER_DESC, "all").getList();
        total = ErknmInspectionSortService.getErknmInspectionsSort(accTValueType, 10, SORT_ORDER_DESC, "all").getTotal();


        Integer erknmInspectionListSize = erknmInspectionsList.size();

        String erknmIdList = erknmInspectionsList.stream().map(x -> x.getErknmId())
                .filter(x -> !x.startsWith("0"))
                .collect(Collectors.joining(","));

        List<String> startDate = erknmInspectionsList.stream().map(x -> x.getStartDate())
                .collect(Collectors.toList());

        List<String> reserveStartDate = new ArrayList<>(startDate);
        Collections.sort(reserveStartDate, reverseOrder());

        assertTrue(startDate.equals(reserveStartDate));

        assertListSize(25, total, accTValueType);
        assertListSize(50, total, accTValueType);

        List<SurveillanceItemsList> erknmInspectionsListSort = ErknmInspectionSortService.getErknmInspectionsSort(accTValueType, total, SORT_ORDER_ASC, "all").getList();
        List<String> startDateList = erknmInspectionsListSort.stream().map(x -> x.getStartDate()).collect(Collectors.toList());
        List<String> sortStartDatelist = new ArrayList<>(startDateList);
        sort(sortStartDatelist);
        assertTrue(sortStartDatelist.equals(startDateList));


    }

    /**
     * Проверка размера списка для заданного totalSize.
     *
     * @param totalSize Размер списка, который нужно проверить.
     * @param total     Общее количество элементов.
     */
    private void assertListSize(int totalSize, int total, String accTValueType) {
        if (total >= totalSize) {
            List<SurveillanceItemsList> erknmInspectionsListSize = ErknmInspectionSortService.getErknmInspectionsSort(accTValueType, totalSize, SORT_ORDER_DESC, "all").getList();
            int erknmInspectionsListSizeTotal = erknmInspectionsListSize.size();
            assertEquals(erknmInspectionsListSizeTotal, totalSize);
        }
    }




       @Disabled("Нужно дорабоать - падает API")
       @SneakyThrows
        @Test
        @DisplayName("Проверка сортировки списка ЕРКНМ для ЮЛ")
        public void assertErknmInspectionListSortingForCorporateTest() {
            verifyErknmInspectionListSorting(accTValueUl);
        }

        @SneakyThrows
        @Disabled("Нужно дорабоать - падает API")
        @Test
        @DisplayName("Проверка сортировки списка ЕРКНМ для ФЛ")
        public void assertErknmInspectionListSortingForPersonalTest() {
            verifyErknmInspectionListSorting(accTValueFl);
        }

        @SneakyThrows
        @Disabled("Нужно дорабоать - падает API")
        @Test
        @DisplayName("Проверка сортировки списка ЕРКНМ для ИП")
        public void assertErknmInspectionListSortingForIndividualTest() {
            verifyErknmInspectionListSorting(accTValueIp);
        }

        @SneakyThrows
        @Test
        @DisplayName("Открытие всех детальных карточек проверок КНМ для ЮЛ")
        public void assertOpenKNMInspectionDetailsCardForCorporateTest() {
            checkOpenKNMInspectionDetailsCard(accTValueUl);
        }

        @SneakyThrows
        @Test
        @DisplayName("Открытие всех детальных карточек проверок КНМ для ФЛ")
        public void assertOpenKNMInspectionDetailsCardForPersonTest() {
            checkOpenKNMInspectionDetailsCard(accTValueFl);
        }

        @SneakyThrows
        @Test
        @DisplayName("Открытие всех детальных карточек проверок КНМ для ИП")
        public void assertOpenKNMInspectionDetailsCardForIndividualTest() {
            checkOpenKNMInspectionDetailsCard(accTValueIp);
        }

        @SneakyThrows
        @Test
        @DisplayName("Открытие всех детальных карточек проверок ПМ для ЮЛ")
        public void assertOpenPMInspectionDetailsCardForCorporateTest() {
            checkOpenPMInspectionDetailsCard(accTValueUl);
        }

        @SneakyThrows
        @Test
        @DisplayName("ООткрытие всех детальных карточек проверок ПМ для ФЛ")
        public void assertOpenPMInspectionDetailsCardForPersonTest() {
            checkOpenPMInspectionDetailsCard(accTValueFl);
        }

        @SneakyThrows
        @Test
        @DisplayName("Открытие всех детальных карточек проверок ПМ для ИП")
        public void assertOpenPMInspectionDetailsCardForIndividualTest() {
            checkOpenPMInspectionDetailsCard(accTValueIp);
        }

        @SneakyThrows
        @Test
        @DisplayName("Проверка типа, названия и вида данных списка ЕРКНМ для ЮЛ")
        public void assertTypeNameAndKindErknmInspectionsListResponseDataForCorporateTest() {
            checkTypeNameAndKindErknmInspections(accTValueUl);
        }

        @SneakyThrows
        @Test
        @DisplayName("Проверка типа, названия и вида данных списка ЕРКНМ для ФЛ")
        public void assertTypeNameAndKindErknmInspectionsListResponseDataForPersonTest() {
            checkTypeNameAndKindErknmInspections(accTValueFl);
        }

        @SneakyThrows
        @Test
        @DisplayName("Проверка типа, названия и вида данных списка ЕРКНМ для ИП")
        public void assertTypeNameAndKindErknmInspectionsListResponseDataForIndividualTest() {
            checkTypeNameAndKindErknmInspections(accTValueIp);
        }
    }

















