package apimodels.erknm;

import apimodels.erknm.Object;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GepsDatum {
    public String extId;
    public String messageId;
    public String type;
    public Object orderId;
    public int orderStatusId;
    public int ownerId;
    public int userId;
    public int orgId;
    public ArrayList<Object> params;
}
