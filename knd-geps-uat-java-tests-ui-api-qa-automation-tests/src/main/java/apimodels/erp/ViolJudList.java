package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ViolJudList {
    public String violJudCode;
    public String violJudNote;
    public String violJudDate;
    public String violJudIsRefused;
    public String violJudExecDate;
}

