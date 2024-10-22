package apimodels.erp;

import apimodels.erknm.Object;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {

    public Object actDate;
    public String actPlace;
    public String actNotReaded;
    public Object startDate;
    public String inspDurDays;
    public boolean hasViolations;
    public int violNumber;
    public String actPlaceTypeCode;
    public String actPlaceType;
    public ArrayList<ObjInspector> objInspectors;
    public ArrayList<InspViolation> inspViolations;
    public ArrayList<ObjRepresentative> objRepresentatives;
    public ArrayList<Object> resultInfos;
    public String inspDurHours;
}
