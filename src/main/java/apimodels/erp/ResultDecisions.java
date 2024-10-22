package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResultDecisions{
    public ArrayList<Injunction> injunction;
    public Arrangements arrangements;
    public Offenses offenses;
    public ControlViolations controlViolations;
    public Recommendations recommendations;
    public Responsible responsible;
}
