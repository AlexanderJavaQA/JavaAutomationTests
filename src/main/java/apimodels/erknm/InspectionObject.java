package apimodels.erknm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InspectionObject {
    public String address;
    public String objectType;
    public String objectKind;
    public String objectSubKind;
    public String riskCategory;
    public String id;
    public String fileName;


}
