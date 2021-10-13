package Prototypes;

import java.util.ArrayList;
import java.util.List;

public interface IRepo <T> {
    List<T> getAll();
    T getByID(int ID);
    void save(T t);
    void deleteByID(int ID);
}
