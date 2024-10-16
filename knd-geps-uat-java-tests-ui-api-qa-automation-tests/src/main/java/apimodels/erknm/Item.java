package apimodels.erknm;

import apimodels.erp.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {
    public String extId;
    public Long orderId;
    public Long feedId;
    public String innerTitle;
    public String serviceName;
    public ArrayList<Message> messages;
    public ArrayList<File> files;
    public Status status;
    public String typeEvent;

}
