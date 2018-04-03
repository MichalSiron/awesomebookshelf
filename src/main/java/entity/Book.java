package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@ToString(of = {"id", "name", "author"})
@Entity(name = "Book")
@Table(name = "tb2_book")
public class Book {

    @Id
    @GenericGenerator(name = "autincr", strategy = "increment")
    @GeneratedValue(generator = "autincr")
    @Column(name = "tb2_id")
    private Integer id;

    @Column(name = "tb2_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb2_author", nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb2_reader")
    private Reader reader;

}
