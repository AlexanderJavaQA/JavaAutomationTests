package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErpInspectionStatusPojo {
    private String statusCode;
    private String statusName;
    private Integer count;

}
