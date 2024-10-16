package apimodels.erknm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SurveillanceItemsList {

    private String statusName;
    private String erknmId;
    private String knoOrganization;
    private String kindControl;
    private List<SurveillanceObject> objects;
    private String typeName;
    private String kind;
    private String startDate;
    private String stopDate;
    private Boolean isNotAppeal;
    private Boolean appealKnm;
    private Boolean appealPreventiveVisit;
    private String appealType;

}
