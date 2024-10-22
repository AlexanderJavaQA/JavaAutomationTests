package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Classification{
    public String controlTypeCode;
    public String controlType;
    public String kindOfInspCode;
    public String kindOfInsp;
    public String noticeWayCode;
    public String noticeWay;
    public String noticeDate;
    public String riskCode;
    public String risk;
}
