package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ObjRepresentative{
    public String inspReprName;
    public String inspReprPos;
    public String inspReprTypeCode;
    public String inspReprType;
}