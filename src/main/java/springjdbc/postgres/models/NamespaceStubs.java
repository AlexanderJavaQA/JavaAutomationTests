package springjdbc.postgres.models;

import lombok.Data;

@Data
public class NamespaceStubs {

    private String namespace;

    public NamespaceStubs() {
    }

    @Override
    public String toString() {
        return namespace;
    }

}

