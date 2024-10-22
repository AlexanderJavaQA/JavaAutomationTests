package apimodels.erknm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class File {
    public String id;
    public String fileName;
    public String fileSize;
    public String mimeType;
    public Boolean hasDigitalSignature;
    public String link;
    public String type;

}
