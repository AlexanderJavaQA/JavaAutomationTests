package templates;

public class XmlTemplates {

    public static String KND_DOCUMENTS(String InspectionNumber, String gepsUserId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KND_DOCUMENTS</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольно (надзорного) мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }


    public static String KND_DOCUMENTS_DEV(String InspectionNumber, String gepsUserId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KND_DOCUMENTS</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольног (надзорного) мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>orgFullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }


    public static String KND_DOCUMENTS_DEV2(String InspectionNumber, String gepsUserId) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KND_DOCUMENTS</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольного надзорного мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>orgFullName</geps:Name>\n" +
                "      <geps:Value>Управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }

    public static String KNO_MOTIV_REQUEST(String InspectionNumber, String gepsUserId) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KNO_MOTIV_REQUEST</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольно (надзорного) мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское1 межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>orgFullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>EMAIL</geps:Name>\n" +
                "      <geps:Value>ivan.ivanov@pochta.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>dueDate</geps:Name>\n" +
                "      <geps:Value>10.12.2023</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }


    public static String KNO_MOTIV_REQUEST_DEV(String InspectionNumber, String gepsUserId) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KNO_MOTIV_REQUEST</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольног (надзорного) мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>orgFullName</geps:Name>\n" +
                "      <geps:Value>ООО Ромашка</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>EMAIL</geps:Name>\n" +
                "      <geps:Value>ivan.ivanov@pochta.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>dueDate</geps:Name>\n" +
                "      <geps:Value>10.12.2023</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }

    public static String KNO_MOTIV_REQUEST_DEV2(String InspectionNumber, String gepsUserId) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KNO_MOTIV_REQUEST</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольного надзорного мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>orgFullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>FIO</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "     <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivan.ivanov@pochta.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2023</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>12332134</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>dueDate</geps:Name>\n" +
                "      <geps:Value>10.12.2023</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }


    public static String KND_INSPECTION_DEV2(String InspectionNumber, String gepsUserId) {
        return  "<geps:epguGepsServiceRequest xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" env=\"UAT\">\n" +
                "<geps:getSendMessageReq ID=\"16\">\n" +
                "<!-- Отправка сообщения в адрес ЮЛ по EsiaID -->\n" +
                "<geps:GepsUserId>"+gepsUserId+"</geps:GepsUserId>\n" +
                "<geps:MessageType>KND_INSPECTION</geps:MessageType>\n" +
                "<geps:Param>\n" +
                "<geps:Name>processType</geps:Name>\n" +
                "<geps:Value>Контрольног надзорного мероприятия</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>system</geps:Name>\n" +
                "<geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>ID</geps:Name>\n" +
                "<geps:Value>"+InspectionNumber+"</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>doc_1</geps:Name>\n" +
                "<geps:Value>Протокол отбора проб(образцов1</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>doc_2</geps:Name>\n" +
                "<geps:Value>Протокол отбора проб(образцов)</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>fullName</geps:Name>\n" +
                "<geps:Value>Иванов И И</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>orgFullName</geps:Name>\n" +
                "<geps:Value>ИП Иванов</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>position</geps:Name>\n" +
                "<geps:Value>Старший эксперт</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>phone</geps:Name>\n" +
                "<geps:Value>+7(999)654-56-36</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>email</geps:Name>\n" +
                "<geps:Value>KND_INSPECTION@sibir.rosavtodor.ru</geps:Value>\n" +
                "</geps:Param>\n" +
                "</geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>";
    }

    public static String KND_INSPECTION_UAT(String InspectionNumber, String gepsUserId) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KND_INSPECTION</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>processType</geps:Name>\n" +
                "      <geps:Value>контрольно (надзорного) мероприятия</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>system</geps:Name>\n" +
                "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>ID</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>fullName</geps:Name>\n" +
                "      <geps:Value>Иванов Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>position</geps:Name>\n" +
                "      <geps:Value>Старший инспектор</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>phone</geps:Name>\n" +
                "      <geps:Value>+7(999)654-56-78</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>email</geps:Name>\n" +
                "      <geps:Value>ivanovps@sibir.rosavtodor.ru</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_date</geps:Name>\n" +
                "      <geps:Value>10.10.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2_date</geps:Name>\n" +
                "      <geps:Value>10.09.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_2</geps:Name>\n" +
                "      <geps:Value>Документы</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>doc_1_number</geps:Name>\n" +
                "      <geps:Value>123321</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>threadId</geps:Name>\n" +
                "      <geps:Value>123</geps:Value>\n" +
                "    </geps:Param>\n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }

    public static String GIS_TOR_KND_VKS(String InspectionNumber, String gepsUserId) {
        return  "<geps:epguGepsServiceRequest xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" env=\"UAT\">\n" +
                "<geps:getSendMessageReq ID=\"16\"> <!-- Отправка сообщения в адрес ЮЛ по EsiaID --> \n" +
                "<geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>" +
                " <geps:MessageType>GIS_TOR_KND_VKS</geps:MessageType>\n" +
                "<geps:Param>\n" +
                "<geps:Name>DATE</geps:Name>\n" +
                "<geps:Value>2023-10-27</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>TIME</geps:Name>\n" +
                "<geps:Value>12</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>TIMEZONE</geps:Name>\n" +
                "<geps:Value>3</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>ORGANIZATION</geps:Name>\n" +
                "<geps:Value>ООО Ромашка</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>NUMBER</geps:Name>\n" +
                "<geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EVENT</geps:Name>\n" +
                "<geps:Value>Оценка</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EVENTNAME</geps:Name>\n" +
                "<geps:Value>Профилактический визит</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EVENTNAME1</geps:Name>\n" +
                "<geps:Value>профилактического визита</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EVENTNAME2</geps:Name>\n" +
                "<geps:Value>Профилактический визит</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EVENTNAME3</geps:Name>\n" +
                "<geps:Value>профилактическом визите</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>FIO</geps:Name>\n" +
                "<geps:Value>Павлов П И</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>POSITION</geps:Name>\n" +
                "<geps:Value>Инспектор 1</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>TEL</geps:Name>\n" +
                "<geps:Value>+48962186328</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>EMAIL</geps:Name>\n" +
                "<geps:Value>GIS_TOR_KND_VKS5@gos.ru</geps:Value>\n" +
                "</geps:Param>\n" +
                "<geps:Param>\n" +
                "<geps:Name>KNO</geps:Name>\n" +
                "<geps:Value>Управление Федеральной службы государственной регистрации, кадастра и картографии по Республике Башкортостан</geps:Value>\n" +
                "</geps:Param>\n" +
                "</geps:getSendMessageReq> </geps:epguGepsServiceRequest>";
    }

/*
               "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "  <geps:getSendMessageReq ID=\"16\">\n" +
                        "    <geps:GepsUserId>"+GepsUserId+"</geps:GepsUserId>\n" +
                        "    <geps:MessageType>KNO_ADDITIONAL_REQUEST</geps:MessageType>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>processType</geps:Name>\n" +
                        "      <geps:Value>контрольного (надзорного) мероприятия</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>system</geps:Name>\n" +
                        "      <geps:Value>ФГИС ЕРКНМ</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>inspection_number</geps:Name>\n" +
                        "      <geps:Value>"+InspectionNumber+"</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>doc_1_date</geps:Name>\n" +
                        "      <geps:Value>10.10.2022</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>doc_2_date</geps:Name>\n" +
                        "      <geps:Value>10.09.2022</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>doc_1</geps:Name>\n" +
                        "      <geps:Value>Документы от 10.09.2022</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>doc_2</geps:Name>\n" +
                        "      <geps:Value>Документы от 10.09.2022</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>doc_1_number</geps:Name>\n" +
                        "      <geps:Value>123321</geps:Value>\n" +
                        "    </geps:Param>\n" +
                        "\n" +
                        "    <geps:Param>\n" +
                        "      <geps:Name>dueDate</geps:Name>\n" +
                        "      <geps:Value>123321</geps:Value>\n" +
                        "    </geps:Param>\n" +
                       "\n" +
                       "    <geps:Param>\n" +
                       "      <geps:Name>serviceMnemonic</geps:Name>\n" +
                       "      <geps:Value>Госуслуги</geps:Value>\n" +
                       "    </geps:Param>\n" +
                       "\n" +
                       "    <geps:Param>\n" +
                       "      <geps:Name>serviceName</geps:Name>\n" +
                       "      <geps:Value>Госуслуги</geps:Value>\n" +
                       "    </geps:Param>\n" +
                        "\n" +
                        "  </geps:getSendMessageReq>\n" +
                        "</geps:epguGepsServiceRequest>\n",*/

    public static String KND_ADM_CASE(String InspectionNumber, String gepsUserId) {
        return  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<geps:epguGepsServiceRequest env=\"UAT\" xmlns:geps=\"http://epgu.gosuslugi.ru/geps/1.0.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "  <geps:getSendMessageReq ID=\"16\">\n" +
                "    <geps:GepsUserId>" + gepsUserId + "</geps:GepsUserId>\n" +
                "    <geps:MessageType>KND_ADM_CASE</geps:MessageType>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>authority_name</geps:Name>\n" +
                "      <geps:Value>Сибирское межрегиональное управление государственного автодорожного надзора Федеральной службы по надзору в сфере транспорта</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>inspection_number</geps:Name>\n" +
                "      <geps:Value>" + InspectionNumber + "</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>admCaseDate</geps:Name>\n" +
                "      <geps:Value>01.01.2022</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>admCaseAddress</geps:Name>\n" +
                "      <geps:Value>г.Москва, улАрбатская, 12</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>organization_name</geps:Name>\n" +
                "      <geps:Value>Петров Иван Иванович</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>inner_title</geps:Name>\n" +
                "      <geps:Value>ТЕСТ</geps:Value>\n" +
                "    </geps:Param>\n" +
                "\n" +
                "    <geps:Param>\n" +
                "      <geps:Name>serviceName</geps:Name>\n" +
                "      <geps:Value>Госуслуги</geps:Value>\n" +
                "    </geps:Param>\n" +
                "    \n" +
                "  </geps:getSendMessageReq>\n" +
                "</geps:epguGepsServiceRequest>\n";
    }

};

