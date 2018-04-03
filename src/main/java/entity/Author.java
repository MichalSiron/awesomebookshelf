package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id", "name"})
@Entity(name = "Author")
@Table(name = "tb1_author")
@NamedQueries(
        {
                @NamedQuery(name = Author.AT_LEAST_ONE_BOOK, query = "select a from Author a where a.id = :id")
        }
)
public class Author {

    /**
     * Finds entity by key
     */
    public static final String AT_LEAST_ONE_BOOK = "Author.atLeastOneBook";

    @Id
    @GenericGenerator(name="autincr" , strategy="increment")
    @GeneratedValue(generator = "autincr")
    @Column(name = "tb_id")
    private Integer id;

    @Column(name = "tb_name")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
