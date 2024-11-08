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
public class InspViolation{
    public String violNote;
    public int violId;
    public String violType;
    public String violTypeCode;
    public ArrayList<ViolJudList> violJudList;
    public ArrayList<InspectionObject> violOrders;
}
