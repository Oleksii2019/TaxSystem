package org.model.dao;

public interface GenericDao<T> extends AutoCloseable {
//    void create (T entity);
//    T findById(int id);
//    List<T> findAll();
//    void update(T entity);
//    void delete(int id);
    boolean matchForLoginAndPassword(String login, String Password);
    void close();

}
