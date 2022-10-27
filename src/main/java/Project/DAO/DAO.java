package Project.DAO;

import java.util.ArrayList;

public interface DAO<T> {
    public int insert(T t);
    public int update(T t);
    public int delete(T t);
    public ArrayList<T> selectAll();
}