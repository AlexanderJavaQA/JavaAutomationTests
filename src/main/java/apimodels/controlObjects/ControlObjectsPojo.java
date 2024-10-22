package apimodels.controlObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ControlObjectsPojo {

    private int pageNumber;
    private int pageSize;
    private int total;
    private List<ControlObject> items;

}
