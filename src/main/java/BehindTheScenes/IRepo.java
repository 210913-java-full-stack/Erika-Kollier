package BehindTheScenes;

import java.util.List;
import java.util.UUID;

public interface IRepo <T> {
    List<T> getAll();
    T getByUUID(UUID ID);
    T getByID(int ID);
    void save(T t);
    void deleteByUUID(UUID ID);
    void deleteByID(int ID);
}
