package apimodels.erknm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Attachment{
    public String guid;
    public String fileName;
    public String fileLink;
    public int attachmentId;
    public int fileSize;
    public int messageId;
    public String mimeType;
    public boolean signed;
    public String status;
}
