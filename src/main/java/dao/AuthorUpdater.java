package dao;

import entity.Author;
import exception.ApplicationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Author entity reader
 *
 * @author Michal Siron
 */

public class AuthorUpdater {
    /**
     * Entity manager
     */
    @PersistenceContext
    private final EntityManager em;

    /**
     * Author reader
     */
    private AuthorReader authorReader;

    /**
     * Gets a new instance.
     *
     * @param inEm means entity manager.
     * @return a new instance of AuthorUpdater
     */
    public static AuthorUpdater getNewInstance(final EntityManager inEm){
        return new AuthorUpdater(inEm);
    }

    /**
     * Constructor.
     * @param em means entity manager
     */
    private AuthorUpdater(final EntityManager em) {
        this.em = em;
        authorReader = AuthorReader.getNewInstance(em);

    }

    public void insertNewAuthor(String name) throws ApplicationException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        em.getTransaction().begin();

        Author author = new Author();
        author.setId(null);
        author.setName(name);
        em.persist(author);

        em.getTransaction().commit();
    }

    public void updateName(int id, String name) {
        Author entity = authorReader.getAuthor(id);

        em.getTransaction().begin();
        entity.setName(name);
        em.getTransaction().commit();
    }

}
