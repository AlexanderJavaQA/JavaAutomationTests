package apimodels.erp;

import apimodels.erknm.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceDocument{
    public String type;
    public String description;
    public ArrayList<Attachment> attachments;
}