package apimodels.erknm;

import apimodels.erp.Decision;
import apimodels.erp.ResultDecisions;
import apimodels.erp.ServiceDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KNMInspectionDetailsCardPojo {

    public String statusName;
    public String erknmId;
    public String knoOrganization;
    public String kindControl;
    public String kind;
    public String startDate;
    public String stopDate;
    public String durationDays;
    public String durationHours;
    public String ogrn;
    public String inn;
    public String organizationName;
    public String supervisionId;
    public ArrayList<InspectionObject> objects;
    public ArrayList<String> reasons;
    public ArrayList<String> places;
    public ArrayList<Inspector> inspectors;
    public ArrayList<InspectionObject> manuallyRequirements;
    public ArrayList<LinkedErknmId> linkedErknmIds;
    public ArrayList<String> collaboratingOrganizations;
    public String noticeDate;
    public String planId;
    public String appealType;
    public String typeName;
    public Decision decision;
    public ArrayList<Event> events;
    public String prosecutorOrganizationName;
    public String approveNote;
    public String dataContent;
    public ArrayList<Expert> experts;
    public ArrayList<DecisionDocuments> decisionDocuments;
    public ServiceDocument serviceDocument;
    public ArrayList<String> organizationDocuments;
    public String actFioSigner;
    public ArrayList<ViolatedRequirement> violatedRequirements;
    public String acquaintanceTypeName;
    public String numberAct;
    public Date creationActDateTime;
    public ArrayList<Attachment> attachments;
    public ArrayList<ActInspector> actInspectors;
    public ResultDecisions resultDecisions;
    public String noteAppeal;
    public String serviceDocumentDescription;
    public String withVideo;
    public String isPresence;
    public String isSelection;
    public String returnSelection;
    public String documentRequestDate;
    public boolean reviewFlag;
    public boolean reviewCompletedFlag;
    public boolean refusePreventiveVisit;
    private String announcementDate;
    private String mspCodeName;
    public boolean isNotAppeal;
    public boolean appealKnm;
    public boolean isRejectSubject;

}
