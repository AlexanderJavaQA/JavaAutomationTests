package springjdbc.postgres;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import springjdbc.postgres.models.*;

import java.util.List;

public class DataAccessObjectPostgres {

    private final JdbcTemplate jdbcTemplate;

    public DataAccessObjectPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RequestId> findRequestIdsByOrderId(String orderId) {
        String sql = "select request_id from sign_contract sc where desc_doc like '%" + orderId + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RequestId.class));
    }

    public List<MessageId> findMessageIdsByCorrelation(String correlation) {
        String sql = "select message_id from public.sma_messages where jms_correlation_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MessageId.class), correlation);
    }

    public List<TitleRecordId> findTitleRecordIdsByTitle(String titleTitle) {
        String sql = "select title_record_id from ervk_supervision where title_title = '" + titleTitle + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TitleRecordId.class));
    }

    public List<Title> findTitlesByRecordId(String title_record_id) {
        String sql = "select title_title from ervk_supervision where title_record_id  = '" + title_record_id + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Title.class));
    }

    public List<TitleSlug> findTitleSlugsBySlug(String slug) {
        String sql = "select distinct on (edr.record_id) title from " +
                "(select * from ervk_dictionary_row_control_register_item) edrce " +
                "left join ervk_dictionary_row edr ON edrce.record_id = edr.record_id " +
                "left join ervk_control_register_item ce on edrce.item_id = ce.item_id " +
                "where edr.dictionary_code = 'TYPE_OF_CONTROL' and ce.status in('PUBLISHED', 'NOT_MODERATION_PUBLISHED') " +
                "and ce.slug in (" + slug + ");";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TitleSlug.class));
    }

    public List<StatusCode> getStatusCodeByOrderId(String orderId) {
        String sql = "select status_code from knd_appeal where order_id = '" + orderId + "'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StatusCode.class));
    }

    public List<KndAppeal> findKndAppealsByOrderId(String orderId) {
        String sql = "select order_id , sp_sign_type from knd_appeal where order_id = cast(? as bigint)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(KndAppeal.class), orderId);
    }


    public void enableERPSmevaFlag() {
        String sql = "update sma_stubs set disabled = 'N' where namespace  = 'urn://ru.gov.proc.erp.communication/5.0.2';";
        jdbcTemplate.update(sql);
    }

    public void enableERKNMSmevaFlag() {
        String sql = "update sma_stubs set disabled = 'N' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';";
        jdbcTemplate.update(sql);
    }

    public void disableERPSmevaFlag() {
        String sql = "update sma_stubs set disabled = 'Y' where namespace  = 'urn://ru.gov.proc.erp.communication/5.0.2';";
        jdbcTemplate.update(sql);
    }

    public void disableERKNMSmevaFlag() {
        String sql = "update sma_stubs set disabled = 'Y' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';";
        jdbcTemplate.update(sql);
    }


    public void clearErknmGepsHistory() {
        String sql = "delete from erknm_geps_history;";
        jdbcTemplate.update(sql);
    }

    public void clearKndErknmInspectionHistory() {
        String sql = "delete from knd_erknm_inspection_history;";
        jdbcTemplate.update(sql);
    }

    public void updateErknmStubTemplate(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        String sql = "update sma_stubs set response = '<ns6:Response xmlns:ns6=\"urn://ru.gov.proc.erknm.communication/6.0.2\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:erknm_types=\"urn://ru.gov.proc.erknm.communication/types/6.0.2\" xmlns:ns0=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:ns1=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"> <erknm_types:Get> <erknm_types:FullInspection> <erknm_types:Criteria erknmId=\"" + erknmIdStubs + "\"/> <erknm_types:RequestStatus>SUCCESS</erknm_types:RequestStatus> <erknm_types:Inspection clientTime=\"2021-12-27T16:49:13.274\" creationDate=\"2021-12-27T16:42:09.993547\" dateCreate=\"2021-12-27T13:42:10.031566\" districtId=\"0\" erknmId=\"" + erknmIdStubs + "\" guid=\"a86140dd-5d87-403c-9134-c9b28cabb3c3\" prosecutorOrganizationId=\"0\" publishDateCreate=\"2021-12-27T16:54:27.000555\" publishedStatusId=\"2\" signed=\"false\" startDate=\"2022-12-29\" statusDateCreate=\"2021-12-27T13:42:10.031566\" statusId=\"23\" stopDate=\"2021-12-29\" typeId=\"0\"> <erknm_types:pm xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" dateOfNotification=\"2021-12-27\" dateRejectSubject=\"2021-12-27\" isHasNotification=\"true\" isRejectSubject=\"false\" location=\"123213123123132123\" resultOfInspection=\"12321321\" xsi:type=\"erknm_types:PmRead\"> <erknm_types:knoOrganization dictId=\"af002594-a4f0-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e92043ff007e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:supervisionType dictId=\"1f27d942-a52e-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e981282b0213\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:pmType dictId=\"e8703904-a545-11eb-bcbc-0242ac130033\" dictVersionId=\"e8703904-a545-11eb-bcbc-0242ac130033\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:reasons numGuid=\"e53cc562-f2ff-4728-85b6-40f77fd42d0f\" xsi:type=\"erknm_types:ReasonWithRiskRead\"> <erknm_types:reason reasonTypeId=\"301\" xsi:type=\"erknm_types:IReasonRead\"/> </erknm_types:reasons> \n" +
                "<erknm_types:subject isFiz=\"" + isFiz + "\" snils=\"222-288-813 60\" " +
                "inn=\"" + inn + "\" ogrn=\"" + ogrn + "\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectRead\"> <erknm_types:okveds code=\"68.20.2\" name=\"Аренда и управление собственным или арендованным нежилым недвижимым имуществом\" xsi:type=\"erknm_types:OkvedRead\"/> </erknm_types:subject> <erknm_types:objects address=\"660012, КРАЙ, КРАСНОЯРСКИЙ, ГОРОД, КРАСНОЯРСК, УЛИЦА, КАРАМЗИНА, ДОМ 8, 240000010000878\" guid=\"c707d425-73b3-4ded-8711-7403a39a08f8\" xsi:type=\"erknm_types:IObjectRead\"> <erknm_types:objectType dictId=\"641d3956-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-ea2e8d46040d\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectKind dictId=\"641d3e60-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectSubKind dictId=\"641d3e60-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:dangerClass dictId=\"aae78350-bdfd-12eb-8529-0242ac130003\" dictVersionId=\"aae78350-bdfd-12eb-8529-0242ac130013\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:objects> <erknm_types:inspectors fullName=\"12345678\" guid=\"7231ebe7-8f0d-412f-b807-1548e345f6a2\" xsi:type=\"erknm_types:IInspectorRead\"> <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75ca17810106\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:inspectors> </erknm_types:pm> </erknm_types:Inspection> </erknm_types:FullInspection> </erknm_types:Get> </ns6:Response>' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';\n";
        jdbcTemplate.update(sql);
    }

    public void createErknmStubTemplate(String isFiz, String inn, String ogrn, String erknmIdStubs) {
        String sql = "update sma_stubs set response = '<ns6:Response xmlns:ns6=\"urn://ru.gov.proc.erknm.communication/6.0.2\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:erknm_types=\"urn://ru.gov.proc.erknm.communication/types/6.0.2\" xmlns:ns0=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:ns1=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"> <erknm_types:Get> <erknm_types:FullInspection> <erknm_types:Criteria erknmId=\"" + erknmIdStubs + "\"/> <erknm_types:RequestStatus>SUCCESS</erknm_types:RequestStatus> <erknm_types:Inspection clientTime=\"2021-12-27T16:49:13.274\" creationDate=\"2021-12-27T16:42:09.993547\" dateCreate=\"2021-12-27T13:42:10.031566\" districtId=\"0\" erknmId=\"" + erknmIdStubs + "\" guid=\"a86140dd-5d87-403c-9134-c9b28cabb3c3\" prosecutorOrganizationId=\"0\" publishDateCreate=\"2021-12-27T16:54:27.000555\" publishedStatusId=\"2\" signed=\"false\" startDate=\"2022-12-27\" statusDateCreate=\"2021-12-27T13:42:10.031566\" statusId=\"23\" stopDate=\"2021-12-29\" typeId=\"0\"> <erknm_types:pm xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" dateOfNotification=\"2021-12-27\" dateRejectSubject=\"2021-12-27\" isHasNotification=\"true\" isRejectSubject=\"false\" location=\"123213123123132123\" resultOfInspection=\"12321321\" xsi:type=\"erknm_types:PmRead\"> <erknm_types:knoOrganization dictId=\"af002594-a4f0-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e92043ff007e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:supervisionType dictId=\"1f27d942-a52e-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e981282b0213\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:pmType dictId=\"e8703904-a545-11eb-bcbc-0242ac130033\" dictVersionId=\"e8703904-a545-11eb-bcbc-0242ac130033\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:reasons numGuid=\"e53cc562-f2ff-4728-85b6-40f77fd42d0f\" xsi:type=\"erknm_types:ReasonWithRiskRead\"> <erknm_types:reason reasonTypeId=\"301\" xsi:type=\"erknm_types:IReasonRead\"/> </erknm_types:reasons> \n" +
                "<erknm_types:subject isFiz=\"" + isFiz + "\" snils=\"222-288-813 60\" inn=\"" + inn + "\" ogrn=\"" + ogrn + "\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectRead\"> <erknm_types:okveds code=\"68.20.2\" name=\"Аренда и управление собственным или арендованным нежилым недвижимым имуществом\" xsi:type=\"erknm_types:OkvedRead\"/> </erknm_types:subject> <erknm_types:objects address=\"660012, КРАЙ, КРАСНОЯРСКИЙ, ГОРОД, КРАСНОЯРСК, УЛИЦА, КАРАМЗИНА, ДОМ 8, 240000010000878\" guid=\"c707d425-73b3-4ded-8711-7403a39a08f8\" xsi:type=\"erknm_types:IObjectRead\"> <erknm_types:objectType dictId=\"641d3956-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-ea2e8d46040d\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectKind dictId=\"641d3e60-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectSubKind dictId=\"641d3e60-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:dangerClass dictId=\"aae78350-bdfd-12eb-8529-0242ac130003\" dictVersionId=\"aae78350-bdfd-12eb-8529-0242ac130013\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:objects> <erknm_types:inspectors fullName=\"12345678\" guid=\"7231ebe7-8f0d-412f-b807-1548e345f6a2\" xsi:type=\"erknm_types:IInspectorRead\"> <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75ca17810106\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:inspectors> </erknm_types:pm> </erknm_types:Inspection> </erknm_types:FullInspection> </erknm_types:Get> </ns6:Response>' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';\n";
        jdbcTemplate.update(sql);
    }


    public void createERPStubTemplate(String inn, String ogrn, String erpId) {
        String responseTemplate = "<ns6:Response xmlns:ns6=\"urn://ru.gov.proc.erp.communication/5.0.2\">"
                + " <erp_types:Get xmlns:erp_types=\"urn://ru.gov.proc.erp.communication/types/5.0.2\">"
                + "   <erp_types:FullInspection>"
                + "     <erp_types:Criteria erpId=\"%s\"/>"
                + "     <erp_types:RequestStatus>SUCCESS</erp_types:RequestStatus>"
                + "     <erp_types:Inspection annualId=\"2020\" dateCreate=\"2020-01-23T14:38:53\""
                + "       domainId=\"1000000000000001\" erpId=\"%s\" erpIdDateCreate=\"2020-01-23T14:33:41\""
                + "       fzId=\"0\" guid=\"EFACE14C-F356-492B-ADD1-ACC94BB94735\" iTypeId=\"0\""
                + "       inspectionStatus=\"1\" isDel=\"false\" isStartMonth=\"false\" prosecId=\"1000000000\""
                + "       startDate=\"2020-02-03\">"
                + "       <erp_types:IAuthority dateCreate=\"2020-01-23T14:33:41\""
                + "         frguOrgGuid=\"20171224-1609-2819-1548-000000383432\" frguOrgIdBk=\"10002448426\""
                + "         frguOrgName=\"Федеральная служба по аккредитации\">"
                + "         <erp_types:IAuthorityServ dateCreate=\"2020-01-23T14:33:41\""
                + "           frguOrgGuid=\"20171224-1609-2819-1548-000000383432\""
                + "           frguServGuid=\"20171224-1609-4124-2827-000000383432\" frguServIdBk=\"10002716942\""
                + "           frguServName=\"(10002716942) Осуществление федерального государственного контроля за деятельностью аккредитованных лиц\""
                + "           iGuid=\"EFACE14C-F356-492B-ADD1-ACC94BB94735\"/>"
                + "         <erp_types:IInspector dateCreate=\"2020-01-23T14:33:41\" fullName=\"Кайль Л.А.\" inspectorTypeId=\"1\""
                + "           numGuid=\"20200123-1433-4267-6839-000000383432\" position=\"специалист 1 разряда отдела государственного контроля\"/>"
                + "       </erp_types:IAuthority>"
                + "       <erp_types:IClassification dateCreate=\"2020-01-23T14:33:49\" iCarryoutTypeId=\"1\" iGuid=\"EFACE14C-F356-492B-ADD1-ACC94BB94735\""
                + "         iNoticeDate=\"2020-01-23\" iNoticeTypeId=\"44\" iObjectTypeId=\"4\" iSubjectTypeId=\"0\" iSupervisionId=\"125\">"
                + "         <erp_types:IClassificationLb dateCreate=\"2020-01-23T14:33:49\" iGuid=\"EFACE14C-F356-492B-ADD1-ACC94BB94735\""
                + "           lbDocumentName=\"пункт 5.1.7 Положения о Федеральной службе по аккредитации\" numGuid=\"20200123-1433-4995-9295-000000383432\"/>"
                + "       </erp_types:IClassification>"
                + "       <erp_types:ISubject dateCreate=\"2020-05-09T00:31:41\" iGuid=\"EFACE14C-F356-492B-ADD1-ACC94BB94735\""
                + "         iSubjectTypeId=\"0\" inn=\"%s\" ogrn=\"%s\" orgName=\"Общества с ограниченной ответственностью «Бренд девело11пмент»\"/>"
                + "     </erp_types:Inspection>"
                + "   </erp_types:FullInspection>"
                + " </erp_types:Get>"
                + "</ns6:Response>";

        String response = String.format(responseTemplate, erpId, erpId, inn, ogrn);

        String sql = "UPDATE sma_stubs SET response = ? WHERE \"namespace\" = 'urn://ru.gov.proc.erp.communication/5.0.2';";
        jdbcTemplate.update(sql, response);
    }


    public void createErknmResultsStubTemplate(String isFiz, String inn, String ogrn, String erknmId) {
        String sql = "update sma_stubs set response = '<ns6:Response xmlns:ns6=\"urn://ru.gov.proc.erknm.communication/6.0.2\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:erknm_types=\"urn://ru.gov.proc.erknm.communication/types/6.0.2\" xmlns:ns0=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:ns1=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"> <erknm_types:Get> <erknm_types:FullInspection> <erknm_types:Criteria erknmId=\"" + erknmId + "\"/> <erknm_types:RequestStatus>SUCCESS</erknm_types:RequestStatus> <erknm_types:Inspection clientTime=\"2023-05-22T18:22:04.444\" creationDate=\"2023-05-05T16:22:02.059214\" dateCreate=\"2023-05-05T13:22:02.069678\" districtId=\"1000000000000001\" erknmId=\"" + erknmId + "\" guid=\"8a15e39e-9047-42b9-887c-e726cafca51d\" prosecutorOrganizationId=\"2\" publishDateCreate=\"2022-05-31T16:22:02.059214\" publishedStatusId=\"2\" signed=\"true\" startDate=\"2025-07-05\" statusDateCreate=\"2023-05-05T16:22:07.091069\" statusId=\"21\" typeId=\"5\"> <erknm_types:documents xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" description=\"КНМ в машиночитаемом формате (xml) для подписания УКЭП.\" documentTypeId=\"55\" guid=\"6d6d6217-9bb5-484d-9edf-10b09eb5e1d6\" xsi:type=\"erknm_types:IDocumentRead\"> <erknm_types:attachments dateCreate=\"2022-05-31T16:22:07.091069\" fileName=\"819130.xml\" guid=\"6d6d6217-9bb5-484d-9edf-10b09eb5e1d6\" xsi:type=\"erknm_types:IAttachmentRead\"/> </erknm_types:documents> <erknm_types:knm xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" durationDays=\"12\" xsi:type=\"erknm_types:IRead\"> <erknm_types:knoOrganization dictId=\"af002594-a4f0-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e92043ff007e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:decision dateTimeDecision=\"2022-05-04T18:21:00\" fioSigner=\"1\" numberDecision=\"1\" placeDecision=\"1\" xsi:type=\"erknm_types:IDecisionRead\"> <erknm_types:titleSigner dictId=\"b6fc9844-a528-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75bd23e900c9\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:decision> <erknm_types:kindControl dictId=\"1f27d942-a52e-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e981282b0213\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:kind dictId=\"d2ec803a-a53e-11eb-bcbc-0242ac130002\" dictVersionId=\"d2ec803a-a53e-11eb-bcbc-0242ac130003\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:reasons numGuid=\"a3742a87-31a5-4c66-939c-c2efc7bb444b\" xsi:type=\"erknm_types:ReasonWithRiskRead\"> <erknm_types:reason reasonTypeId=\"305\" xsi:type=\"erknm_types:IReasonRead\"/> </erknm_types:reasons> <erknm_types:approve approveRequiredId=\"2\" xsi:type=\"erknm_types:IApproveRead\"/>\n" +
                "<erknm_types:organizations guid=\"4a513815-86ae-41de-9ce4-99b3a5f9359d\" isFiz=\"" + isFiz + "\" snils=\"222-288-813 60\" inn=\"" + inn + "\" ogrn=\"" + ogrn + "\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectRead\">  \n" +
                "<erknm_types:resultDecisions dateDecision=\"2021-10-27\" fioSigner=\"Ковшина Ирина Викторовна test\" guid=\"c9230fe2-5b2d-426f-a8eb-83495c5c60bd\" isProsecution=\"true\" kindDecisionId=\"1\" numberDecision=\"32375\" xsi:type=\"erknm_types:IResultDecisionRead\">\n" +
                "              <erknm_types:document xsi:type=\"erknm_types:IDocumentRead\">\n" +
                "                <erknm_types:attachments dateCreate=\"2021-10-27T09:08:11.505417\" fileName=\"test Предписание Павловская СШ.pdf\" guid=\"23d54ff2-6ba8-4f60-9958-4bb187c7d675\" xsi:type=\"erknm_types:IAttachmentRead\"/>\n" +
                "              </erknm_types:document>\n" +
                "              <erknm_types:injunction dateResolved=\"2023-08-01\" note=\"предписание об устранении нарушений\" xsi:type=\"erknm_types:IInjunctionRead\"/>\n" +
                "              <erknm_types:titleSigner dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae96483-7a7d-1c35-817a-815237fa00cc\" xsi:type=\"erknm_types:IDictionaryRead\"/>\n" +
                "              <erknm_types:inspectors fullName=\"Ковшина Ирина Викторовна\" guid=\"6fad8df5-498b-4c0d-b4a6-d158e6a3703e\" xsi:type=\"erknm_types:IInspectorRead\">\n" +
                "                <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae96483-7a7d-1c35-817a-815237fa00cc\" xsi:type=\"erknm_types:IDictionaryRead\"/>\n" +
                "              </erknm_types:inspectors>\n" +
                "              <erknm_types:subjectDecision inn=\"1201004920\" ogrn=\"1020200715281\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectDecisionRead\"/> \n" +
                "              <erknm_types:responsibleEntities guid=\"8cf13eda-247f-47c5-91ed-1cb76e378eec\" inn=\"1201004920\" ogrn=\"1020200715281\" organizationName=\"МКОУ &quot;Павловская средняя школа&quot;\" punishmentAmount=\"30000.0\" punishmentAmountMeasure=\"руб.\" responsibilityTypeId=\"1\" typeId=\"1\" xsi:type=\"erknm_types:IResponsibleSubjectRead\">\n" +
                "                <erknm_types:structuresNPA value=\"п. 2.2.2. п. 2.4.3. п. 2.4.11. п. 2.5.2. п. 2.5.3. п. 2.6.1.  п. 2.11.4. п. 2.11.2. п. 3.2.4. п. 3.4.8.  п. 3.4.13. СП 2.4.3648-20 &quot;Санитарно-эпидемиологические требования к организациям воспитания и обучения, отдыха и оздоровления детей и молодежи&quot;, п. 2.13. СанПиН 2.3/2.4.3590-20 &quot;Санитарно-эпидемиологические требования к организации общественного питания населения&quot;, п. 144 СанПиН 1.2.3685-21 &quot;Гигиенические нормативы и требования к обеспечению безопасности и (или) безвредности для человека факторов среды обитания&quot;, предусмотренное ч. 1 ст. 6.7. КоАП РФ (постановление от 23.11.2021 г. № 35925).\" xsi:type=\"erknm_types:IStringRead\"/>\n" +
                "              </erknm_types:responsibleEntities>\n" +
                "            </erknm_types:resultDecisions>\n" +
                "</erknm_types:organizations>   <erknm_types:objects address=\"117246, ГОРОД, МОСКВА, ПРОЕЗД, НАУЧНЫЙ, ДОМ 101, 770000000001965\" guid=\"46d986c3-cf86-42f1-b139-bcab705b6b57\" xsi:type=\"erknm_types:IObjectRead\"> <erknm_types:objectType dictId=\"641d3956-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-ea2e8d48040f\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectKind dictId=\"641d3bb8-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectSubKind dictId=\"641d3bb8-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:riskCategory dictId=\"18d790d4-a5a9-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-d5aaf14a002a\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:dangerClass dictId=\"aae78350-bdfd-12eb-8529-0242ac130003\" dictVersionId=\"aae78350-bdfd-12eb-8529-0242ac130023\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:objects> <erknm_types:inspectors fullName=\"1\" guid=\"170fc5ca-5526-4691-83c7-da39770a87a9\" xsi:type=\"erknm_types:IInspectorRead\"> <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75ca17810106\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:inspectors> <erknm_types:events guid=\"6c76c34b-c2f9-46f9-bd9b-c9252053a6c7\" startDate=\"2022-05-31\" stopDate=\"2022-06-05\" xsi:type=\"erknm_types:IEventRead\"> <erknm_types:event dictId=\"f0f9a79a-a5b3-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-d5a7ba0e0012\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:events> <erknm_types:requirements guid=\"1637aa5a-5332-450f-8b2a-b9d1787caf3f\" xsi:type=\"erknm_types:IRequirementRead\"> <erknm_types:requirement dictId=\"a43f056e-b95c-11eb-8529-0242ac130003\" dictVersionId=\"00db6e43-8437-47d4-a1ee-3af0edf4d15f\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:requirements> <erknm_types:places guid=\"21d38b9a-6373-4413-82c2-c7e285dedae2\" value=\"1\" xsi:type=\"erknm_types:IStringRead\"/> </erknm_types:knm> </erknm_types:Inspection> </erknm_types:FullInspection> </erknm_types:Get> </ns6:Response>' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';\n";
        jdbcTemplate.update(sql);
    }

    public void updateErknmResultsStubTemplate(String isFiz, String inn, String ogrn, String erknmId) {
        String sql = "update sma_stubs set response = '<ns6:Response xmlns:ns6=\"urn://ru.gov.proc.erknm.communication/6.0.2\" xmlns=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:erknm_types=\"urn://ru.gov.proc.erknm.communication/types/6.0.2\" xmlns:ns0=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.2\" xmlns:ns1=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns2=\"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.2\" xmlns:ns3=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"> <erknm_types:Get> <erknm_types:FullInspection> <erknm_types:Criteria erknmId=\"" + erknmId + "\"/> <erknm_types:RequestStatus>SUCCESS</erknm_types:RequestStatus> <erknm_types:Inspection clientTime=\"2023-05-22T18:22:04.444\" creationDate=\"2023-05-05T16:22:02.059214\" dateCreate=\"2023-05-05T13:22:02.069678\" districtId=\"1000000000000001\" erknmId=\"" + erknmId + "\" guid=\"8a15e39e-9047-42b9-887c-e726cafca51d\" prosecutorOrganizationId=\"2\" publishDateCreate=\"2022-05-31T16:22:02.059214\" publishedStatusId=\"2\" signed=\"true\" startDate=\"2025-07-05\" statusDateCreate=\"2023-05-05T16:22:07.091069\" statusId=\"21\" typeId=\"5\"> <erknm_types:documents xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" description=\"КНМ в машиночитаемом формате (xml) для подписания УКЭП.\" documentTypeId=\"55\" guid=\"6d6d6217-9bb5-484d-9edf-10b09eb5e1d6\" xsi:type=\"erknm_types:IDocumentRead\"> <erknm_types:attachments dateCreate=\"2022-05-31T16:22:07.091069\" fileName=\"819130.xml\" guid=\"6d6d6217-9bb5-484d-9edf-10b09eb5e1d6\" xsi:type=\"erknm_types:IAttachmentRead\"/> </erknm_types:documents> <erknm_types:knm xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" durationDays=\"12\" xsi:type=\"erknm_types:IRead\"> <erknm_types:knoOrganization dictId=\"af002594-a4f0-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e92043ff007e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:decision dateTimeDecision=\"2022-05-04T18:21:00\" fioSigner=\"1\" numberDecision=\"1\" placeDecision=\"1\" xsi:type=\"erknm_types:IDecisionRead\"> <erknm_types:titleSigner dictId=\"b6fc9844-a528-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75bd23e900c9\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:decision> <erknm_types:kindControl dictId=\"1f27d942-a52e-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-e981282b0213\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:kind dictId=\"d2ec803a-a53e-11eb-bcbc-0242ac130002\" dictVersionId=\"d2ec803a-a53e-11eb-bcbc-0242ac130003\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:reasons numGuid=\"a3742a87-31a5-4c66-939c-c2efc7bb444b\" xsi:type=\"erknm_types:ReasonWithRiskRead\"> <erknm_types:reason reasonTypeId=\"305\" xsi:type=\"erknm_types:IReasonRead\"/> </erknm_types:reasons> <erknm_types:approve approveRequiredId=\"2\" xsi:type=\"erknm_types:IApproveRead\"/>\n" +
                "<erknm_types:organizations guid=\"4a513815-86ae-41de-9ce4-99b3a5f9359d\" isFiz=\"" + isFiz + "\" snils=\"222-288-813 60\" inn=\"" + inn + "\" ogrn=\"" + ogrn + "\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectRead\">  \n" +
                "<erknm_types:resultDecisions dateDecision=\"2021-10-27\" fioSigner=\"Ковшина Ирина Викторовна test\" guid=\"c9230fe2-5b2d-426f-a8eb-83495c5c60bd\" isProsecution=\"true\" kindDecisionId=\"1\" numberDecision=\"32375\" xsi:type=\"erknm_types:IResultDecisionRead\">\n" +
                "              <erknm_types:document xsi:type=\"erknm_types:IDocumentRead\">\n" +
                "                <erknm_types:attachments dateCreate=\"2021-10-27T09:08:11.505417\" fileName=\"test Предписание Павловская СШ.pdf\" guid=\"23d54ff2-6ba8-4f60-9958-4bb187c7d675\" xsi:type=\"erknm_types:IAttachmentRead\"/>\n" +
                "              </erknm_types:document>\n" +
                "              <erknm_types:injunction dateResolved=\"2023-08-01\" note=\"предписание об устранении нарушений\" xsi:type=\"erknm_types:IInjunctionRead\"/>\n" +
                "              <erknm_types:titleSigner dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae96483-7a7d-1c35-817a-815237fa00cc\" xsi:type=\"erknm_types:IDictionaryRead\"/>\n" +
                "              <erknm_types:inspectors fullName=\"Ковшина Ирина Викторовна\" guid=\"6fad8df5-498b-4c0d-b4a6-d158e6a3703e\" xsi:type=\"erknm_types:IInspectorRead\">\n" +
                "                <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae96483-7a7d-1c35-817a-815237fa00cc\" xsi:type=\"erknm_types:IDictionaryRead\"/>\n" +
                "              </erknm_types:inspectors>\n" +
                "              <erknm_types:subjectDecision inn=\"1201004920\" ogrn=\"1020200715281\" organizationName=\"Бесплаов Ф.А.\" xsi:type=\"erknm_types:ISubjectDecisionRead\"/> \n" +
                "              <erknm_types:responsibleEntities guid=\"8cf13eda-247f-47c5-91ed-1cb76e378eec\" inn=\"1201004920\" ogrn=\"1020200715281\" organizationName=\"МКОУ &quot;Павловская средняя школа&quot;\" punishmentAmount=\"30000.0\" punishmentAmountMeasure=\"руб.\" responsibilityTypeId=\"1\" typeId=\"5\" xsi:type=\"erknm_types:IResponsibleSubjectRead\">\n" +
                "                <erknm_types:structuresNPA value=\"п. 2.2.2.555 п. 2.4.3. п. 2.4.11. п. 2.5.2. п. 2.5.3. п. 2.6.1.  п. 2.11.4. п. 2.11.2. п. 3.2.4. п. 3.4.8.  п. 3.4.13. СП 2.4.3648-20 &quot;Санитарно-эпидемиологические требования к организациям воспитания и обучения, отдыха и оздоровления детей и молодежи&quot;, п. 2.13. СанПиН 2.3/2.4.3590-20 &quot;Санитарно-эпидемиологические требования к организации общественного питания населения&quot;, п. 144 СанПиН 1.2.3685-21 &quot;Гигиенические нормативы и требования к обеспечению безопасности и (или) безвредности для человека факторов среды обитания&quot;, предусмотренное ч. 1 ст. 6.7. КоАП РФ (постановление от 23.11.2021 г. № 35925).\" xsi:type=\"erknm_types:IStringRead\"/>\n" +
                "              </erknm_types:responsibleEntities>\n" +
                "            </erknm_types:resultDecisions>\n" +
                "</erknm_types:organizations>   <erknm_types:objects address=\"117246, ГОРОД, МОСКВА, ПРОЕЗД, НАУЧНЫЙ, ДОМ 101, 770000000001965\" guid=\"46d986c3-cf86-42f1-b139-bcab705b6b57\" xsi:type=\"erknm_types:IObjectRead\"> <erknm_types:objectType dictId=\"641d3956-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-ea2e8d48040f\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectKind dictId=\"641d3bb8-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:objectSubKind dictId=\"641d3bb8-a5b1-11eb-bcbc-0242ac130002\" dictVersionId=\"0ae94760-7a6d-1f16-817a-76349230021e\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:riskCategory dictId=\"18d790d4-a5a9-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-d5aaf14a002a\" xsi:type=\"erknm_types:IDictionaryRead\"/> <erknm_types:dangerClass dictId=\"aae78350-bdfd-12eb-8529-0242ac130003\" dictVersionId=\"aae78350-bdfd-12eb-8529-0242ac130023\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:objects> <erknm_types:inspectors fullName=\"1\" guid=\"170fc5ca-5526-4691-83c7-da39770a87a9\" xsi:type=\"erknm_types:IInspectorRead\"> <erknm_types:position dictId=\"003d7ce8-c474-11eb-8529-0242ac130003\" dictVersionId=\"0ae94760-7a6d-1f16-817a-75ca17810106\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:inspectors> <erknm_types:events guid=\"6c76c34b-c2f9-46f9-bd9b-c9252053a6c7\" startDate=\"2022-05-31\" stopDate=\"2022-06-05\" xsi:type=\"erknm_types:IEventRead\"> <erknm_types:event dictId=\"f0f9a79a-a5b3-11eb-bcbc-0242ac130002\" dictVersionId=\"0af4cd2e-78cb-109b-8178-d5a7ba0e0012\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:events> <erknm_types:requirements guid=\"1637aa5a-5332-450f-8b2a-b9d1787caf3f\" xsi:type=\"erknm_types:IRequirementRead\"> <erknm_types:requirement dictId=\"a43f056e-b95c-11eb-8529-0242ac130003\" dictVersionId=\"00db6e43-8437-47d4-a1ee-3af0edf4d15f\" xsi:type=\"erknm_types:IDictionaryRead\"/> </erknm_types:requirements> <erknm_types:places guid=\"21d38b9a-6373-4413-82c2-c7e285dedae2\" value=\"1\" xsi:type=\"erknm_types:IStringRead\"/> </erknm_types:knm> </erknm_types:Inspection> </erknm_types:FullInspection> </erknm_types:Get> </ns6:Response>' where namespace  = 'urn://ru.gov.proc.erknm.communication/6.0.2';\n";
        jdbcTemplate.update(sql);
    }

    public List<NamespaceStubs> findNamespaceStubs() {
        String sql = "select namespace from sma_stubs ss where namespace = 'urn://ru.gov.proc.erknm.communication/6.0.2';";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NamespaceStubs.class));
    }

}



