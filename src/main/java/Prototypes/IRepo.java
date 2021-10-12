package Prototypes;

import java.util.ArrayList;

public interface IRepo <T> {
    ArrayList<T> getAll();
    ArrayList<T> getByID(int ID);
    void save(T t);
    void deleteByID(int ID);
}
