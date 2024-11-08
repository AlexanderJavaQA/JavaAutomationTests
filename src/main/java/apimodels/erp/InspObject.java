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
public class InspObject{
    public String objTypeCode;
    public String objType;
    public String objAddr;
    public String objAddrTypeCode;
    public String objAddrType;
    public boolean hasResult;
    public Result result;
    public ArrayList<ObjectSupervision> objectSupervisions;
    public ArrayList<InspectionObject> chLists;
    public String riskObjCode;
    public String riskObj;
}
