package apimodels.controlObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ControlObject {

    private String registryObjectId;
    private String objectId;
    private String objectName;
    private String controlType;
    private String riskCategory;
    private String address;
    private String objectType;
    private String objectSpecType;
    private String objectSubspecType;
    private String controlOrganId;
    private String controlOrganOGRN;
    private String controlOrganTitle;


}
