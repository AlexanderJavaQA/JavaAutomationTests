package apimodels.erp;

import apimodels.erknm.GepsDatum;
import apimodels.erknm.InspectionObject;
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
public class ErpInspectionListPojo {

    public String erpid;
    public String typeCode;
    public String type;
    public Integer month;
    public String startDate;
    public String year;
    public Date created;
    public Date erpCreated;
    public Date updated;
    public String inspStatusCode;
    public String inspStatus;
    public String prosec;
    public String prosecCode;
    public String fz;
    public String fzCode;
    public ProsecOkato prosecOkato;
    public ArrayList<Authority> authority;
    public Classification classification;
    public InspSub inspSub;
    public ArrayList<InspObject> inspObjects;
    public InspApprovement inspApprovement;
    public ArrayList<InspectionObject> linkedInspections;
    public PublishInfo publishInfo;
    public boolean notificationSent;
    public Integer yearMonth;
    public ArrayList<GepsDatum> gepsData;
    public boolean isStartMonth;
    public boolean isDel;
    public Integer lCycleid;
    public boolean isNotAppeal;

}



