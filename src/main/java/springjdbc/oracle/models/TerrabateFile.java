package springjdbc.oracle.models;

import lombok.Data;

@Data
public class TerrabateFile {

    private String OBJECT_ID;
    private String FILE_MNEMONIC;

    public TerrabateFile() {
    }
}
