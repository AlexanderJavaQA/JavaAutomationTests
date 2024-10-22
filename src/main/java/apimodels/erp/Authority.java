package apimodels.erp;

import apimodels.erknm.Inspector;
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
public class Authority{
    public String ctrlOrgName;
    public String ctrlOrgId;
    public String ctrlOrgFRGU;
    public ArrayList<Function> functions;
    public ArrayList<Inspector> inspectors;
    public ArrayList<Object> koJointlyFRGU;
}
