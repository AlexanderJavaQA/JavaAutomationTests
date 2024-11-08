package pages.doknd;

public class SmevRequestPage {

    ComplaintDetailsPage complaintDetailsPage = new ComplaintDetailsPage();

    public static String orderIdAppeal;

    public String getOrderIdFromComplaintDetails() {
        complaintDetailsPage.getOrderIdFromOrderHeader();
        return orderIdAppeal;
    }

    public void sendOrderToKuberAndRetrieveCorrelationId() {
        complaintDetailsPage.openKuberDiscoverPageInNewTabUat()
                .enterKuberOrderId()
                .clickUpdateButton()
                .getKuberCorrelationId();
    }

    public void getKuberSmevMessageId() {
        complaintDetailsPage.getKuberSmevMessageIdUAT();
    }

    public void submitXmlRequestsToSmev(String statusCodeAddActions, String statusCodeSolution) {
        SmevPage smevPage = new SmevPage();
        smevPage.openSmevResponseToPguUat()
                .clearMessageID()
                .setMessageID(complaintDetailsPage.getNewSmevMessageId())
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), "101")
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), statusCodeAddActions)
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), statusCodeSolution)
                .clickButtonSubmit()
                .clickButtonOk();
    }

    public void submitComplaintWithdrawalRequestToSmev() {
        SmevPage smevPage = new SmevPage();

        smevPage.openSmevResponseToPguUat()
                .clearMessageID()
                .setMessageID(complaintDetailsPage.getNewSmevMessageId())
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), "101")
                .clickButtonSubmit()
                .clickButtonOk();
    }

    public void submitAdditionalInfoRequestToSmev() {
        SmevPage smevPage = new SmevPage();

        smevPage.openSmevResponseToPguUat()
                .clearMessageID()
                .setMessageID(complaintDetailsPage.getNewSmevMessageId())
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), "101")
                .clickButtonSubmit()
                .clickButtonOk()
                .setXmlRequest(complaintDetailsPage.getNewOrderId(), "114")
                .clickButtonSubmit()
                .clickButtonOk();
    }

       public void processSmevRequestWithStatusCodesUat(String statusCodeAddActions, String statusCodeSolution) {
        getOrderIdFromComplaintDetails();
        sendOrderToKuberAndRetrieveCorrelationId();
        getKuberSmevMessageId();
        submitXmlRequestsToSmev(statusCodeAddActions,statusCodeSolution);
    }

    public void processSmevComplaintWithdrawalRequestUat() {
        getOrderIdFromComplaintDetails();
        sendOrderToKuberAndRetrieveCorrelationId();
        getKuberSmevMessageId();
        submitComplaintWithdrawalRequestToSmev();
    }

    public void processAdditionalInfoSmevRequest() {
        getOrderIdFromComplaintDetails();
        sendOrderToKuberAndRetrieveCorrelationId();
        getKuberSmevMessageId();
        submitAdditionalInfoRequestToSmev();
    }
}
