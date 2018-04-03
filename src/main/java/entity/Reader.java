package entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "Reader")
@Table(name = "tb3_reader")
public class Reader {

    @Id
    @GenericGenerator(name="autincr" , strategy="increment")
    @GeneratedValue(generator = "autincr")
    @Column(name = "tb3_id")
    private Integer id;

    @Column(name = "tb3_name")
    private String name;

    @Column(name = "tb3_birth")
    private Date birth;

    @OneToMany(mappedBy = "reader")
    private List<Book> books;

}


