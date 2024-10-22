package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reason {
    public String reasonId;
    public String reasonName;
    public String reasonText;
    public String reasonDate;
    public boolean reasonTextShow;
}
