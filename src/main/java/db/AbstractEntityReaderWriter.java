package db;

import javax.persistence.EntityManager;

abstract class AbstractEntityReaderWriter {

    EntityManager em = EntityManagerProvider.getEntityManager();

    public void closeEm(){
        em.close();
    }

    public void closeEmf(){
        EntityManagerProvider.close();
    }

}
