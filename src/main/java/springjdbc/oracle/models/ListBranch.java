package springjdbc.oracle.models;

import lombok.Data;

@Data
public class ListBranch {

    private String NAME;

    public ListBranch() {
    }
    @Override
    public String toString() {
        return  NAME ;
    }

}
