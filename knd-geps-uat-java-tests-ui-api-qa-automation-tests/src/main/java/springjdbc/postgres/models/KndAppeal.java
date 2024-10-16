package springjdbc.postgres.models;

import lombok.Data;

@Data
public class KndAppeal {

    private String sp_sign_type;
    private Long order_id;

    public KndAppeal() {
    }

    @Override
    public String toString() {
        return sp_sign_type + "  " + order_id;
    }
}
