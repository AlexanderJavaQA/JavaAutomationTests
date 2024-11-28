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
public class InspectionDocsTemplates {


    public List<Item> items;

    public String pageNumber;
    public String pageSize;
    public String total;
}
