package api.knd;

import api.DocumentExchangeTabService;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import apimodels.erknm.InspectionDocsTemplates;
import apimodels.erknm.Item;
import apimodels.erknm.Message;
import apimodels.erknm.SurveillanceItemsList;
import ui.knd.template.TorTemplateTests;
import ui.knd.erknm.ErknmTemplateTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static api.ErknmInspectionSortService.getErknmInspectionsSort;
import static api.InspectionDocsTemplatesService.getInspectionDocsTemplates;
import static java.lang.Thread.sleep;
import static java.util.Collections.sort;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Проверка вкладки обмена документами")
public class DocumentsExchangeTabTests extends BaseApiTests {

    String erknmIdisEmpty = "02210861000100043073522";
    public static final String SORT_ORDER_DESC = "desc";
    public static final String SORT_ORDER_ASC = "asc";

    private static final List<String> TYPE_EVENT = Arrays.asList(
            "GIS_TOR_KND_VKS",
            "KND_ADM_CASE",
            "KND_INSPECTION",
            "ERKNM_PASSED",
            "ERKNM_UPDATED",
            "KND_DOCUMENTS",
            "KNO_MOTIV_REQUEST",
            "ERKNM_UPCOMING",
            "ERKNM_RESULTS",
            "ERKNM_RESULTS_UPDATED"
    );


    public void checkOpenDocumentExchangeTab(String accTValue) {
        List<SurveillanceItemsList> erknmInspectionsList = getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "all").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

        for (String erknmId : erknmIdList) {
            try {
                DocumentExchangeTabService.getDocumentExchangeTab(accTValue, erknmId);
            } catch (AssertionError e) {
                System.out.println("Ошибка: " + e);
                System.out.println("Для проверки: " + erknmId);
            }
        }
    }


    @SneakyThrows
    public void checkSendTORAndERKNMTemplates(String accTValue) {
        smevPage.enableERKNMSmevaFlag();


        List<SurveillanceItemsList> erknmInspectionsList = getErknmInspectionsSort(accTValue, 30, SORT_ORDER_DESC, "all").getList();
        List<String> erknmIdList = erknmInspectionsList.stream()
                .map(SurveillanceItemsList::getErknmId)
                .collect(Collectors.toList());

        for (String erknmId : erknmIdList) {
            try {
                InspectionDocsTemplates responsePojo = getInspectionDocsTemplates(accTValue, erknmId);
                if (responsePojo.items == null || responsePojo.items.isEmpty()) {
                    erknmIdisEmpty = erknmId;
                    System.out.println(erknmIdisEmpty);
                    break;
                } else {
                }

            } catch (AssertionError e) {
                System.out.println("Ошибка API docs для проверки: " + erknmId + e.getMessage());
            }

        }


        ErknmTemplateTests erknmTemplateProcessingTests = new ErknmTemplateTests();

        ErknmTemplateTests ErknmTemplateProcessing = new ErknmTemplateTests();
        ErknmTemplateProcessing.verifySingleErknmTemplateSubmission();

        TorTemplateTests sendTORTemplates = new TorTemplateTests();
        sendTORTemplates.sendTORTemplates(erknmIdisEmpty, "2079552524");

        InspectionDocsTemplates responseApiDocs = getInspectionDocsTemplates(accTValue, erknmIdisEmpty);

        int iterations = 0;
        boolean isEqual = false;

        while (iterations < 10 && !isEqual) {
            InspectionDocsTemplates responseApiDocsIsEquals = getInspectionDocsTemplates(accTValue, erknmIdisEmpty);

            Thread.sleep(2000); // Подождать 2 секунды
            List<String> typeEvent = responseApiDocsIsEquals.items.stream()
                    .map(Item::getTypeEvent)
                    .sorted()
                    .collect(Collectors.toList());


            sort(typeEvent);
            List<String> sortedTypeEvent = new ArrayList<>(TYPE_EVENT);
            sort(sortedTypeEvent);

            System.out.println("sortedTypeEvent" + sortedTypeEvent);
            System.out.println("typeEvent" + typeEvent);

            isEqual = typeEvent.equals(sortedTypeEvent);
            if (!isEqual) {
                iterations++;
            }
        }

        if (isEqual) {
            System.out.println("Равны после " + iterations + " итераций");
        } else {
            System.out.println("Не удалось добиться равенства после 10 итераций");
        }


        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.getExtId().equals(erknmIdisEmpty)), "Error in ExtId");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.getOrderId() != null), "Error in OrderId");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.getFeedId() != null), "Error in FeedId");
        assertTrue(responseApiDocs.items.stream().flatMap(x -> x.messages.stream()).allMatch(y -> y.attachments.isEmpty()), "Error in attachment");
        assertTrue(responseApiDocs.items.stream().flatMap(x -> x.messages.stream()).allMatch(i -> i.sendDate != null), "Error in sendDate");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.getFiles().isEmpty()), "Error in Files");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.status.orderStatusName.equals("Черновик заявления")), "Error in StatusName");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.status.currentStatusHistory.date != null), "Error in currentStatusHistory");
        assertTrue(responseApiDocs.items.stream().allMatch(x -> x.status.currentStatusHistory.statusId == 0), "Error in currentStatusHistory");


        assertTextTypeEvent("KND_INSPECTION", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Документы в&nbsp;рамках контрольного (надзорного) мероприятия в&nbsp;ФГИС ЕРКНМ №&nbsp;" + erknmIdisEmpty + " направлены вам на&nbsp;подписание</div><div class=\"all-offset-top-8 mt-8\">Скачайте приложенные документы, подпишите их&nbsp;простой электронной подписью&nbsp;(ПЭП) и&nbsp;отправьте через форму справа</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div class=\"title-h5\">Документы на подписание</div><ul class=\"all-offset-top-8 mt-8 list-blue-dotted\"><li>Документы от&nbsp;10.10.2022 №&nbsp;123321</li><li class=\"all-offset-top-12 mt-12\">Документы от&nbsp;10.09.2022</li></ul></div><div class=\"all-offset-top-24 mt-24\">По&nbsp;вопросам обращайтесь к&nbsp;ответственному сотруднику</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>Ведомство:</b> Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</div><div class=\"all-offset-top-12 mt-12\"><b>ФИО:</b> Иванов Иван Иванович</div><div class=\"all-offset-top-12 mt-12\"><b>Должность:</b> Старший инспектор</div><div class=\"all-offset-top-12 mt-12\"><b>Телефон:</b> <span style=\"white-space: nowrap;\">+7(999)654-56-78</span></div><div class=\"all-offset-top-12 mt-12\"><b>Электронная почта:</b> <a href=\"mailto:ivanovps@sibir.rosavtodor.ru\" target=\"_blank\" style=\"text-decoration:none;\">ivanovps@sibir.rosavtodor.ru</a></div></div></div></div>", accTValue);
        assertTextTypeEvent("GIS_TOR_KND_VKS", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Вам назначена дистанционная проверка по&nbsp;видео-конференц-связи&nbsp;(ВКС)</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div><div class=\"all-offset-top-12 mt-12\"><b>Вид:</b> Оценка</div><div class=\"all-offset-top-12 mt-12\"><b>Дата и&nbsp;время:</b> <span style=\"white-space: nowrap;\">2023-10-27,</span> 15&nbsp;(МСК3)</div></div><div class=\"all-offset-top-24 mt-24\">В&nbsp;назначенное время подключитесь к&nbsp;ВКС в&nbsp;приложении «МП.&nbsp;Инспектор». Понадобится подтверждённая учётная запись на&nbsp;Госуслугах</div><div class=\"all-offset-top-8 mt-8\">По&nbsp;вопросам обращайтесь к&nbsp;инспектору</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>ФИО:</b> Павлов П И</div><div class=\"all-offset-top-12 mt-12\"><b>Должность:</b> Инспектор 1</div><div class=\"all-offset-top-12 mt-12\"><b>Телефон:</b> <span style=\"white-space: nowrap;\">+48962186328</span></div><div class=\"all-offset-top-12 mt-12\"><b>Электронная почта:</b> <a href=\"mailto:GIS_TOR_KND_VKS5@gos.ru\" target=\"_blank\" style=\"text-decoration:none;\">GIS_TOR_KND_VKS5@gos.ru</a></div></div><div class=\"all-offset-top-24 mt-24\">Для&nbsp;участия в&nbsp;проверке заранее установите приложение «МП.&nbsp;Инспектор»</div><div class=\"all-offset-top-16 mt-16\"><a href=\"https://knd.gov.ru/document/mp\" style=\"text-decoration:none;\" target=\"_blank\">Подробнее&nbsp;о&nbsp;приложении</a></div></div></div>\n", accTValue);
        assertTextTypeEvent("KND_ADM_CASE", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Контрольный орган Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта направил вам уведомление по&nbsp;административному делопроизводству в&nbsp;рамках проверки №&nbsp;" + erknmIdisEmpty + "</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>Контролируемое лицо:</b> Петров Иван Иванович</div><div class=\"all-offset-top-12 mt-12\"><b>Дата протокола:</b> 01.01.2022</div><div class=\"all-offset-top-12 mt-12\"><b>Место составления протокола:</b> г.Москва, улАрбатская, 12</div></div></div></div>\n", accTValue);
        assertTextTypeEvent("ERKNM_PASSED", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Для&nbsp;вашей организации объявлено предостережение. Подробности можно посмотреть <a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" target=\"_blank\" style=\"text-decoration:none;\">в&nbsp;разделе «Контроль и&nbsp;надзор»</a></div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 8px 20px 20px 20px; border-radius: 8px;\"><div class=\"all-offset-top-12 mt-12\"><b>Проверяемое лицо:</b> Бесплаов Ф.А.</div><div class=\"all-offset-top-12 mt-12\"><b>ОГРН:</b> 1020200715281</div><div class=\"all-offset-top-12 mt-12\"><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div><div class=\"all-offset-top-12 mt-12\"><b>Статус:</b> Предостережение объявлено</div><div class=\"all-offset-top-12 mt-12\"><b>Дата начала:</b> 2022-12-27</div><div class=\"all-offset-top-12 mt-12\"><b>Контрольный орган:</b> Федеральная служба по надзору в сфере здравоохранения</div></div><div class=\"all-offset-top-24 mt-24\"><a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" class=\"button-base button-blue\" target=\"_blank\">Перейти&nbsp;в&nbsp;раздел</a></div></div></div>\n", accTValue);
        assertTextTypeEvent("ERKNM_UPDATED", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Изменилась информация о&nbsp;предостережении для вашей организации. Подробности можно посмотреть <a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" target=\"_blank\" style=\"text-decoration:none;\"><span style=\" white-space: nowrap;\">в&nbsp;разделе</span> «Контроль <span style=\" white-space: nowrap;\">и&nbsp;надзор»</span></a></div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 8px 20px 20px 20px; border-radius: 8px;\"><div class=\"all-offset-top-12 mt-12\"><b>Проверяемое лицо:</b> Бесплаов Ф.А.</div><div class=\"all-offset-top-12 mt-12\"><b>ОГРН:</b> 1020200715281</div><div class=\"all-offset-top-12 mt-12\"><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div><div class=\"all-offset-top-12 mt-12\"><b>Статус:</b> Предостережение объявлено</div><div class=\"all-offset-top-12 mt-12\"><b>Дата начала:</b> 2022-12-29</div><div class=\"all-offset-top-12 mt-12\"><b>Контрольный орган:</b> Федеральная служба по надзору в сфере здравоохранения</div></div><div class=\"all-offset-top-24 mt-24\"><a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" class=\"button-base button-blue\" target=\"_blank\">Перейти&nbsp;в&nbsp;раздел</a></div></div></div>\n", accTValue);
        assertTextTypeEvent("KND_DOCUMENTS", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">В&nbsp;рамках контрольного (надзорного) мероприятия в&nbsp;ФГИС ЕРКНМ №&nbsp;" + erknmIdisEmpty + " вам направлены документы в&nbsp;электронной форме</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div class=\"title-h5\">Документы на подписание</div><ul class=\"all-offset-top-8 mt-8 list-blue-dotted\"><li>Документы от&nbsp;10.10.2022 №&nbsp;123321</li><li class=\"all-offset-top-12 mt-12\">Документы от&nbsp;10.09.2022</li></ul></div><div class=\"all-offset-top-24 mt-24\">По&nbsp;вопросам обращайтесь к&nbsp;ответственному сотруднику</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>Ведомство:</b> Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</div><div class=\"all-offset-top-12 mt-12\"><b>ФИО:</b> Иванов Иван Иванович</div><div class=\"all-offset-top-12 mt-12\"><b>Должность:</b> Старший инспектор</div><div class=\"all-offset-top-12 mt-12\"><b>Телефон:</b> <span style=\"white-space: nowrap;\">+7(999)654-56-78</span></div><div class=\"all-offset-top-12 mt-12\"><b>Электронная почта:</b> <a href=\"mailto:ivanovps@sibir.rosavtodor.ru\" target=\"_blank\" style=\"text-decoration:none;\">ivanovps@sibir.rosavtodor.ru</a></div></div></div></div>\n", accTValue);
        assertTextTypeEvent("KNO_MOTIV_REQUEST", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">В&nbsp;рамках контрольного (надзорного) мероприятия в&nbsp;ФГИС ЕРКНМ №&nbsp;" + erknmIdisEmpty + " запрошены документы. Предоставьте их до&nbsp;10.12.2023</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div class=\"title-h5\">Документы на подписание</div><ul class=\"all-offset-top-8 mt-8 list-blue-dotted\"><li>Документы от&nbsp;10.10.2022 №&nbsp;123321</li><li class=\"all-offset-top-12 mt-12\">Документы от&nbsp;10.09.2022</li></ul></div><div class=\"all-offset-top-24 mt-24\">Загрузите документы, подпишите их простой электронной подписью&nbsp;(ПЭП) и&nbsp;отправьте через форму справа</div><div class=\"all-offset-top-24 mt-24\">По&nbsp;вопросам обращайтесь к&nbsp;ответственному сотруднику</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 20px; border-radius: 8px;\"><div><b>Ведомство:</b> Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</div><div class=\"all-offset-top-12 mt-12\"><b>ФИО:</b> Иванов Иван Иванович</div><div class=\"all-offset-top-12 mt-12\"><b>Должность:</b> Старший инспектор</div><div class=\"all-offset-top-12 mt-12\"><b>Телефон:</b> <span style=\"white-space: nowrap;\">+7(999)654-56-78</span></div><div class=\"all-offset-top-12 mt-12\"><b>Электронная почта:</b> <a href=\"mailto:ivanovps@sibir.rosavtodor.ru\" target=\"_blank\" style=\"text-decoration:none;\">ivanovps@sibir.rosavtodor.ru</a></div></div></div></div>\n", accTValue);
        assertTextTypeEvent("ERKNM_UPCOMING", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Для&nbsp;вашей организации назначено контрольное (надзорное) мероприятие. Подробности можно посмотреть <a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" target=\"_blank\" style=\"text-decoration:none;\">в&nbsp;разделе «Контроль и&nbsp;надзор»</a></div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 8px 20px 20px 20px; border-radius: 8px;\"><div class=\"all-offset-top-12 mt-12\"><b>Проверяемое лицо:</b> Бесплаов Ф.А.</div><div class=\"all-offset-top-12 mt-12\"><b>ОГРН:</b> 1020200715281</div><div class=\"all-offset-top-12 mt-12\"><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div><div class=\"all-offset-top-12 mt-12\"><b>Тип:</b> Контрольная закупка</div><div class=\"all-offset-top-12 mt-12\"><b>Статус:</b> Решение обжаловано</div><div class=\"all-offset-top-12 mt-12\"><b>Дата начала:</b> 2025-07-05</div><div class=\"all-offset-top-12 mt-12\"><b>Контрольный орган:</b> Федеральная служба по надзору в сфере здравоохранения</div></div><div class=\"all-offset-top-24 mt-24\"><a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" class=\"button-base button-blue\" target=\"_blank\">Перейти&nbsp;в&nbsp;раздел</a></div></div></div>", accTValue);
        assertTextTypeEvent("ERKNM_RESULTS", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">По&nbsp;результатам контрольного (надзорного) мероприятия для&nbsp;вашей организации вынесено решение, есть привлечённые к&nbsp;ответственности</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 8px 20px 20px 20px; border-radius: 8px;\"><div class=\"all-offset-top-12 mt-12\"><b>Проверяемое лицо:</b> Бесплаов Ф.А.</div><div class=\"all-offset-top-12 mt-12\"><b>ОГРН:</b> 1020200715281</div><div class=\"all-offset-top-12 mt-12\"><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div></div><div class=\"all-offset-top-24 mt-24\">По&nbsp;кому принято решение:</div><ul class='list-dashed all-offset-top-12 mt-12'><li>Бесплаов Ф.А.</li></ul><div class=\"all-offset-top-24 mt-24\">Привлечённые к&nbsp;ответственности:</div><ul class='list-dashed all-offset-top-12 mt-12'><li>МКОУ \"Павловская средняя школа\"</li></ul><div class=\"all-offset-top-24 mt-24\">Подробности можно посмотреть <a href=\"https://lk.gosuslugi.ru/org-profile/knd\" target=\"_blank\" style=\"text-decoration:none;\">в&nbsp;разделе «Контроль и&nbsp;надзор»</a></div><div class=\"all-offset-top-24 mt-24\"><a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" class=\"button-base button-blue\" target=\"_blank\">Перейти&nbsp;в&nbsp;раздел</a></div></div></div>", accTValue);
        assertTextTypeEvent("ERKNM_RESULTS_UPDATED", "<div class=\"mail-body-content mini-bottom\"><div class=\"lg-h-title-16-big md-h-title-16-big sm-h-title-16 text-plain\"><div>Здравствуйте</div><div class=\"all-offset-top-8 mt-8\">Изменилась информация о&nbsp;вынесенном решении по&nbsp;результатам контрольного (надзорного) мероприятия для&nbsp;вашей организации</div><div class=\"all-offset-top-24 mt-24\" style=\"background-color: #EDF2FE; padding: 8px 20px 20px 20px; border-radius: 8px;\"><div class=\"all-offset-top-12 mt-12\"><b>Проверяемое лицо:</b> Бесплаов Ф.А.</div><div class=\"all-offset-top-12 mt-12\"><b>ОГРН:</b> 1020200715281</div><div class=\"all-offset-top-12 mt-12\"><b>Номер мероприятия:</b> " + erknmIdisEmpty + "</div></div><div class=\"all-offset-top-24 mt-24\">По&nbsp;кому принято решение:</div><ul class='list-dashed all-offset-top-12 mt-12'><li>Бесплаов Ф.А.</li></ul><div class=\"all-offset-top-24 mt-24\">Привлечённые к&nbsp;ответственности:</div><ul class='list-dashed all-offset-top-12 mt-12'><li></li></ul><div class=\"all-offset-top-24 mt-24\">Подробности можно посмотреть <a href=\"https://lk.gosuslugi.ru/org-profile/knd\" target=\"_blank\" style=\"text-decoration:none;\">в&nbsp;разделе «Контроль и&nbsp;надзор»</a></div><div class=\"all-offset-top-24 mt-24\"><a href=\"https://lk.gosuslugi.ru/org-profile/knd/inspect/" + erknmIdisEmpty + "\" class=\"button-base button-blue\" target=\"_blank\">Перейти&nbsp;в&nbsp;раздел</a></div></div></div>\n", accTValue);


        checkInnerTitle("KND_INSPECTION", "Подпишите документы в рамках контрольного (надзорного) мероприятия", accTValue);
        checkInnerTitle("GIS_TOR_KND_VKS", "Назначен профилактический визит", accTValue);
        checkInnerTitle("KND_ADM_CASE", "Уведомление по административному делопроизводству", accTValue);
        checkInnerTitle("ERKNM_PASSED", "Объявлено предостережение для вашей организации", accTValue);
        checkInnerTitle("KND_DOCUMENTS", "Предоставьте документы в рамках контрольного (надзорного) мероприятия", accTValue);
        checkInnerTitle("KNO_MOTIV_REQUEST", "Предоставьте документы в рамках контрольного (надзорного) мероприятия", accTValue);
        checkInnerTitle("ERKNM_UPCOMING", "Для вашей организации назначена проверка", accTValue);
        checkInnerTitle("ERKNM_RESULTS", "Принято решение по результатам проверки", accTValue);
        checkInnerTitle("ERKNM_RESULTS_UPDATED", "Изменено решение по результатам проверки", accTValue);
        checkInnerTitle("ERKNM_UPDATED", "Изменения в предостережении для вашей организации", accTValue);


        checkServiceName("KND_INSPECTION", "ФГИС ЕРКНМ", accTValue);
        checkServiceName("GIS_TOR_KND_VKS", "Госуслуги", accTValue);
        checkServiceName("KND_ADM_CASE", "Госуслуги", accTValue);
        checkServiceName("ERKNM_PASSED", "Госуслуги", accTValue);
        checkServiceName("KND_DOCUMENTS", "ФГИС ЕРКНМ", accTValue);
        checkServiceName("KNO_MOTIV_REQUEST", "ФГИС ЕРКНМ", accTValue);
        checkServiceName("ERKNM_UPCOMING", "Госуслуги", accTValue);
        checkServiceName("ERKNM_RESULTS", "Госуслуги", accTValue);
        checkServiceName("ERKNM_RESULTS_UPDATED", "Госуслуги", accTValue);
        checkServiceName("ERKNM_UPDATED", "Госуслуги", accTValue);

        smevPage.disableERKNMSmevaFlag();
    }

    public void assertTextTypeEvent(String eventType, String expectedtext, String accTValue) {
        InspectionDocsTemplates responseApiDocs = getInspectionDocsTemplates(accTValue, erknmIdisEmpty);
        String actualEventText = responseApiDocs.items.stream()
                .filter(x -> x.getTypeEvent().equals(eventType))
                .flatMap(item -> item.messages.stream())
                .map(Message::getText)
                .collect(Collectors.joining());

        assertThat(actualEventText).isEqualToIgnoringWhitespace(expectedtext);
    }

    public void checkInnerTitle(String typeEvent, String innerTitle, String accTValue) {
        InspectionDocsTemplates responseApiDocs = getInspectionDocsTemplates(accTValue, erknmIdisEmpty);
        assertTrue(responseApiDocs.items.stream().filter(x -> x.getTypeEvent().equals(typeEvent))
                .allMatch(x -> x.getInnerTitle().equals(innerTitle)), "Error in innerTitle");
    }

    public void checkServiceName(String typeEvent, String innerTitle, String accTValue) {
        InspectionDocsTemplates responseApiDocs = getInspectionDocsTemplates(accTValue, erknmIdisEmpty);
        assertTrue(responseApiDocs.items.stream().filter(x -> x.getTypeEvent().equals(typeEvent))
                .allMatch(x -> x.getServiceName().equals(innerTitle)), "Error in ServiceName");
    }

    @SneakyThrows
    @Test
    @Order(1)
    @Disabled("Нужно доработать логику")
    @DisplayName("Отправка шаблонов TOR и ERKNM для вкладки обмена документами для ЮЛ")
    public void checkSendTORAndERKNMTemplatesUL() {
        checkSendTORAndERKNMTemplates(accTValueUl);
    }

    @SneakyThrows
    @Test
    @Order(2)
    @Disabled("Нужно доработать логику")
    @DisplayName("Отправка шаблонов TOR и ERKNM для вкладки обмена документами для ФЛ")
    public void checkSendTORAndERKNMTemplatesFL() {
        checkSendTORAndERKNMTemplates(accTValueFl);
    }

    @SneakyThrows
    @Test
    @Order(3)
    @Disabled("Нужно доработать логику")
    @DisplayName("Отправка шаблонов TOR и ERKNM для вкладки обмена документами для ИП")
    public void checkSendTORAndERKNMTemplatesIP() {
        checkSendTORAndERKNMTemplates(accTValueIp);
    }

    @SneakyThrows
    @Test
    @DisplayName("Открытие всех вкладок обмена документами для ЮЛ")
    public void checkOpenDocumentExchangeTabUL() {
        checkOpenDocumentExchangeTab(accTValueUl);
    }

    @SneakyThrows
    @Test
    @DisplayName("Открытие всех вкладок обмена документами для ФЛ")
    public void checkOpenDocumentExchangeTabFL() {
        checkOpenDocumentExchangeTab(accTValueFl);
    }

    @SneakyThrows
    @Test
    @DisplayName("Открытие всех вкладок обмена документами для ИП")
    public void checkOpenDocumentExchangeTabIP() {
        checkOpenDocumentExchangeTab(accTValueIp);
    }

}




