package dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.Author;
import entity.QAuthor;
import entity.QBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Author entity reader
 *
 * @author Michal Siron
 */
public class AuthorReader {

    /**
     * Entity manager
     */
    @PersistenceContext
    private final EntityManager em;

    /**
     * Gets a new instance.
     *
     * @param inEm means entity manager.
     * @return a new instance of AuthorReader
     */
    public static AuthorReader getNewInstance(final EntityManager inEm){
        return new AuthorReader(inEm);
    }

    /**
     * Constructor.
     * @param em means entity manager
     */
    private AuthorReader(final EntityManager em) {
        this.em = em;
    }

    /**
     * @return Author with at least one book
     */
    public List<Author> withAtLeastOneBook(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QAuthor author = QAuthor.author;
        QBook book = QBook.book;

        return queryFactory.selectFrom(author).distinct().join(author.books, book).fetch();
    }

    /**
     * @return author according id
     */
    public Author getAuthor(int id){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QAuthor author = QAuthor.author;

        return queryFactory.selectFrom(author).where(author.id.eq(id))
                .fetchOne();
    }
}
