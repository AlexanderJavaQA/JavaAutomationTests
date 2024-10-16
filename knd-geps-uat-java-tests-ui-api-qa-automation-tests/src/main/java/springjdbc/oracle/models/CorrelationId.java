package springjdbc.oracle.models;

import lombok.Data;

@Data
public class CorrelationId {

    private String id;

    @Override
    public String toString() {
        return id;
    }

    public CorrelationId() {
    }
}
