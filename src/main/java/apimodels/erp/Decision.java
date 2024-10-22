package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Decision{
    public Date dateTimeDecision;
    public String numberDecision;
    public String placeDecision;
    public String fioSigner;
    public String titleSigner;
}