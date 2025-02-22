package springjdbc.postgres.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class KndAppeal {

    private String sp_sign_type;
    private String status_code;
    private Long order_id;

}
