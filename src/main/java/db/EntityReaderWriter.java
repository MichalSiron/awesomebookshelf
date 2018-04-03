package db;

import dao.*;
import entity.Author;
import entity.Reader;
import exception.ApplicationException;

import java.util.List;

/**
 * Operate with database. Selects, inserts, deletes from entity package
 */

public class EntityReaderWriter extends AbstractEntityReaderWriter {


    /**
     * Selects all authors from database
     * @return List<Author>
     * @throws ApplicationException
     */
    public List<Author> withAtLeastOneBook() throws ApplicationException {
        return AuthorReader.getNewInstance(this.em).withAtLeastOneBook();
    }

    /**
     * Inserts new Author into databse
     * @param name
     * @throws ApplicationException
     */
    public void insertNewAuthor(String name) throws ApplicationException{
        AuthorUpdater.getNewInstance(this.em).insertNewAuthor(name);
    }

    /**
     * Selects all readers from database
     * @return List<Reader>
     */
    public List<Reader> getAllReaders(){
        return ReaderReader.getNewInstance(this.em).getAll();
    }

    /**
     * Prints statistics
     */
    public List<PopularAuthor> printStats() throws ApplicationException{
        return BookReader.getNewInstance(this.em).getAll();
    }

}
