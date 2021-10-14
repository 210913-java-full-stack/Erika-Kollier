package Prototypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IRepo <T> {
    List<T> getAll();
    T getByID(UUID ID);
    void save(T t);
    void deleteByID(UUID ID);
}
