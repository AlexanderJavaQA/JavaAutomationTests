package api.knd;

import apimodels.erknm.ControlTypesResponse;
import apimodels.erknm.ControlTypesResponseItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import apimodels.controlObjects.TypesOfContolPojo;
import springjdbc.postgres.DataAccessObjectPostgres;
import springjdbc.postgres.DatabaseConnectPostgres;
import springjdbc.postgres.models.Title;
import springjdbc.postgres.models.TitleSlug;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static api.TypesOfContolService.getTypesOfContolList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("SmokeAPITest")
@DisplayName("Проверка применимых виды контроля на странице Контроль и надзор")
public class TypesOfContolTests extends BaseApiTests {

    private void extractAndCompareTitles(String accTValue) {
        List<ControlTypesResponseItem> typesOfControl = (List<ControlTypesResponseItem>) getTypesOfContolList(accTValue);

        List<String> supervisionIds = typesOfControl.stream()
                .map(ControlTypesResponseItem::getSupervisionId)
                .collect(Collectors.toList());

        for (String supervisionId : supervisionIds) {
            String title = new DataAccessObjectPostgres(DatabaseConnectPostgres.surveillanceDatabaseDev2())
                    .findTitlesByRecordId(supervisionId)
                    .stream()
                    .findFirst()
                    .map(Title::getTitle_title)
                    .orElse(null);

            String supervisionIdFromApi = typesOfControl.stream()
                    .filter(x -> x.getSupervisionId().equals(supervisionId))
                    .map(ControlTypesResponseItem::getTitle)
                    .findFirst()
                    .orElse(null);

            assertEquals(title, supervisionIdFromApi,
                    "Значение title из базы данных и идентификатор supervisionId из API должны совпадать");
        }
    }

    @Test
    @DisplayName("Проверка типов контроля для ЮЛ")
    public void shouldVerifyTypesOfControlForUL() {
        extractAndCompareTitles(accTValueUl);
    }

    @Test
    @DisplayName("Проверка типов контроля для ФЛ")
    public void shouldVerifyTypesOfControlForFL() {
        extractAndCompareTitles(accTValueFl);
    }

    @Test
    @DisplayName("Проверка типов контроля для ИП")
    public void shouldVerifyTypesOfControlForIP() {
        extractAndCompareTitles(accTValueIp);
    }
}
