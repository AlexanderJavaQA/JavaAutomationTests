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
public class Message {
    public String sendDate;
    public String text;
    public List<Attachment> attachments;


}

