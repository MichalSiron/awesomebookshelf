package dao;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.Author;
import entity.QBook;
import exception.ApplicationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Book entity reader
 *
 * @author Michal Siron
 */
public class BookReader {

    /**
     * Entity manager
     */
    @PersistenceContext
    private final EntityManager em;

    /**
     * Gets a new instance.
     *
     * @param inEm means entity manager.
     * @return a new instance of BookReader
     */
    public static BookReader getNewInstance(final EntityManager inEm){
        return new BookReader(inEm);
    }

    /**
     * Constructor.
     * @param em means entity manager
     */
    private BookReader(final EntityManager em) {
        this.em = em;
    }


    /**
//     * @return reader according id
     */
    public List<PopularAuthor> getAll() throws ApplicationException{
        List<PopularAuthor> popularAuthors = new ArrayList<>();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBook book = QBook.book;

        JPAQuery<Tuple> query = queryFactory.select(book.author.id, book.reader.count())
                .from(book)
                .where(book.reader.isNotNull())
                .groupBy(book.author.id)
                .orderBy(book.reader.count().desc())
                .limit(3);

        for (Tuple row : query.fetch()){
            Author author = getAuthorById(row.get(book.author.id));
            popularAuthors.add(new PopularAuthor(author.getName(), row.get(book.reader.count())));
        }

        return popularAuthors;
    }

    private Author getAuthorById(Integer id) throws ApplicationException{
        if (id == null){
            throw new ApplicationException("Id for given author is null!");
        }
        return loadAuthorById(id);

    }

    private Author loadAuthorById(Integer id) throws ApplicationException {
        Author author = AuthorReader.getNewInstance(this.em).getAuthor(id);
        if (author == null){
            throw new ApplicationException("Author with id "+id+" not found in dbs!");
        }

        return author;
    }


}
