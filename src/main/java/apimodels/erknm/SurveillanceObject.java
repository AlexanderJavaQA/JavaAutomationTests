package apimodels.erknm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SurveillanceObject {
    private String address;
    private String objectType;
    private String objectKind;
    private String objectSubKind;
    private String riskCategory;

}
