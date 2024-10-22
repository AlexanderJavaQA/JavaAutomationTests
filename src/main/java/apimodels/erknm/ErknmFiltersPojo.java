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
public class ErknmFiltersPojo {

    private List<StatusList> statusList;
    private List<KindList> kindList;


}
