package dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.QReader;
import entity.Reader;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Reader entity reader
 *
 * @author Michal Siron
 */
public class ReaderReader {
    /**
     * Entity manager
     */
    @PersistenceContext
    private final EntityManager em;

    /**
     * Gets a new instance.
     *
     * @param inEm means entity manager.
     * @return a new instance of ReaderReader
     */
    public static ReaderReader getNewInstance(final EntityManager inEm){
        return new ReaderReader(inEm);
    }

    /**
     * Constructor.
     * @param em means entity manager
     */
    private ReaderReader(final EntityManager em) {
        this.em = em;
    }


    /**
     * @return reader according id
     */
    public List<Reader> getAll(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReader reader = QReader.reader;

        return queryFactory.selectFrom(reader)
                .fetch();
    }
}
