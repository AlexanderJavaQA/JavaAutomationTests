package springjdbc.oracle.models;

import lombok.Data;

@Data
public class StatusHistory {

    private String ORDER_STATUS_ID;

    public StatusHistory() {
    }

    @Override
    public String toString() {
        return ORDER_STATUS_ID;
    }
}
