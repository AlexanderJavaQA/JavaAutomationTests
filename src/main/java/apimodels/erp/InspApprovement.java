package apimodels.erp;

import apimodels.erknm.InspectionObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InspApprovement{
    public String inspTarget;
    public String durDays;
    public String isApproved;
    public String startDate;
    public String endDate;
    public boolean isStartDateAsMonth;
    public ArrayList<ApproveDoc> approveDocs;
    public ArrayList<ControlEvent> controlEvents;
    public ArrayList<Reason> reasons;
    public ArrayList<InspectionObject> denyReasons;
    public String durHours;
}
