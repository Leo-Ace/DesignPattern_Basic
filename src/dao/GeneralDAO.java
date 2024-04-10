package dao;

import java.util.List;

public interface GeneralDAO<T> {
    public List<T> get();
    public List<T> getByName(String name);
    public T findId(Object id);
    public boolean add(T entity);
    public boolean edit(T entity);
    public boolean remove(T entity);
}
