package org.example.service;

public interface Service<T, Id> {
    void create(T t) throws Exception;
    T get(Id id) throws Exception;
    void update(T t) throws Exception;
    boolean delete(Id id) throws Exception;
}
