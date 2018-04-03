package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Entity Manager Provider for working with JPA
 *
 * @author Michal Siron
 */

public class EntityManagerProvider {

    private static final String PERSISTENCE_UNIT_NAME = "bookshelf";

    /**
     * Entity manager factory for creating EntityManager
     *  can be created just once for all app scope
     */
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /**
     * Empty private constructor to avoid instantiation.
     */
    private EntityManagerProvider(){}

    /**
     * Creates new entity manager from Entity manager factory
     *
     * @return new EntityManager
     */
    static EntityManager getEntityManager(){
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    /**
     * Close EntityManagerFactory
     */
    static void close(){
        ENTITY_MANAGER_FACTORY.close();
    }

}
