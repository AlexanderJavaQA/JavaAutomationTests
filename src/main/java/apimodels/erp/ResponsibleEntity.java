package apimodels.erp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponsibleEntity{
    public int typeId;
    public String fullName;
    public String position;
    public String inn;
    public String responsibilityTypeName;
    public double punishmentAmount;
    public String punishmentAmountMeasure;
    public String punishmentTerm;
    public String organizationName;
    public String ogrn;
}

