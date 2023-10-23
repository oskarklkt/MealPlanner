package mealplanner;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    T get(int id) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
    void add(T t) throws SQLException;
    void update(T t);
    void deleteById(int id);

}
