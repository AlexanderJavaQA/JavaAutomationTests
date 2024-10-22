package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ObjInspector{
    public String inspectorName;
    public String inspectorPosition;
    public String inspectorType;
    public String inspectorTypeCode;
}
