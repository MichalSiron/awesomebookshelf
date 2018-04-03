package dao;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PopularAuthor {

    private String name;

    private Long popularity;

    PopularAuthor(String name, Long popularity){
        this.name = name;
        this.popularity = popularity;
    }
}
