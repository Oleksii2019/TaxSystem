package org.model.dao;

public interface GenericDao<T> extends AutoCloseable {
    void close();
}
