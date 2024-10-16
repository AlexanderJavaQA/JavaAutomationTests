package springjdbc.postgres.models;

import lombok.Data;

@Data
public class TitleSlug {

    private String title;

    public TitleSlug() {
    }

    @Override
    public String toString() {
        return  title;
    }
}
