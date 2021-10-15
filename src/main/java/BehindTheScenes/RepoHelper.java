package BehindTheScenes;

import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public abstract class RepoHelper<T> implements IRepo<T> {
    // Hibernate Query Variables
    protected Transaction tx;
    protected Query query;
    protected TypedQuery<T> typedQuery;

    @Override
    public List getAll() {
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
