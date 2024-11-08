package springjdbc.postgres.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class StatusCode {
    private String status_code;

    @Override
    public String toString() {
        return status_code;
    }
}
