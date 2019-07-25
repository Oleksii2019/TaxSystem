package org.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
//    void create (T entity);
//    T findById(int id);
//    List<T> findAll();
//    void update(T entity);
//    void delete(int id);
    List<T> getNotAcceptedReportsForOfficerLogin(String login);
    List<T> getNotAcceptedReportsForPayerLogin(String login);
    boolean matchForLoginAndPassword(String login, String Password);
    void close();

}
