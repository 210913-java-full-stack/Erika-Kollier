package Global;

import Logging.MyLogger;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public abstract class RepoHelper<T> implements IRepo<T> {
    // Hibernate Query Variables
    protected Transaction tx;
    protected Query query;
    protected TypedQuery<T> typedQuery;
    protected Logger logger = MyLogger.getFileLogger();

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T getByUUID(UUID ID) {
        return null;
    }

    @Override
    public T getByID(int ID) {
        return null;
    }

    @Override
    public void save(T t) {

    }

    @Override
    public void deleteByUUID(UUID ID) {

    }

    @Override
    public void deleteByID(int ID) {

    }
}
