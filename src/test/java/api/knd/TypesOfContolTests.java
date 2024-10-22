package api.knd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import apimodels.controlObjects.TypesOfContolPojo;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import springjdbc.postgres.models.TitleSlug;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static api.TypesOfContolService.getTypesOfContolList;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("SmokeAPITest")
@DisplayName("Проверка типов контроля")
public class TypesOfContolTests extends BaseApiTests {

    public void verifyTypesOfControl(String accTValue) {
        List<TypesOfContolPojo> typesOfControl = (List<TypesOfContolPojo>) getTypesOfContolList(accTValue);
        List<String> sortedTitlesFromApi = extractAndSortTitlesFromApi(typesOfControl);
        List<String> sortedTitlesFromDatabase = extractAndSortTitlesFromDatabase(typesOfControl);

        assertTrue(sortedTitlesFromApi.equals(sortedTitlesFromDatabase), "Titles from API do not match titles from the database.");
    }

    private List<String> extractAndSortTitlesFromApi(List<TypesOfContolPojo> typesOfControl) {
        List<String> titles = typesOfControl.stream()
                .map(TypesOfContolPojo::getTitle)
                .collect(Collectors.toList());
        Collections.sort(titles);
        titles.forEach(System.out::println);

        return titles;
    }

    private List<String> extractAndSortTitlesFromDatabase(List<TypesOfContolPojo> typesOfControl) {
        String slugs = typesOfControl.stream()
                .map(TypesOfContolPojo::getSlug)
                .map(s -> "'" + s + "'")
                .collect(Collectors.joining(","));

        List<TitleSlug> titleSlugList = new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseUat())
                .findTitleSlugsBySlug(slugs);

        Comparator<TitleSlug> titleComparator = Comparator.comparing(TitleSlug::getTitle);
        Collections.sort(titleSlugList, titleComparator);
        titleSlugList.forEach(System.out::println);

        return titleSlugList.stream()
                .map(TitleSlug::getTitle)
                .collect(Collectors.toList());
    }

    @Test
    @DisplayName("Проверка типов контроля для ЮЛ")
    public void shouldVerifyTypesOfControlForUL() {
        verifyTypesOfControl(accTValueUl);
    }

    @Test
    @DisplayName("Проверка типов контроля для ФЛ")
    public void shouldVerifyTypesOfControlForFL() {
        verifyTypesOfControl(accTValueFl);
    }

    @Test
    @DisplayName("Проверка типов контроля для ИП")
    public void shouldVerifyTypesOfControlForIP() {
        verifyTypesOfControl(accTValueIp);
    }

}
