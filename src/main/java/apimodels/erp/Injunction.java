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
public class Injunction{
    public String kindDecisionName;
    public String kindDecisionId;
    public ArrayList<String> noteLawsuits;
    public String description;
    public ArrayList<Attachment> attachments;
    public String isProsecution;
    public String dateDecision;
    public String numberDecision;
    public String fioSigner;
    public String titleSigner;
    public String injunctionDateResolved;
    public String injunctionNote;
}
