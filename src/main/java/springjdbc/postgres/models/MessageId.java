package springjdbc.postgres.models;

import lombok.Data;

@Data
public class MessageId {

    private String message_id;

    public MessageId(String message_id) {
        this.message_id = message_id;
    }

    public MessageId() {
    }

    @Override
    public String toString() {
        return  message_id;
    }

}
