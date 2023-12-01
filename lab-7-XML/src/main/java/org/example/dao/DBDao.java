package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class DBDao<T, Id> implements CrudDao<T, Id>{
    @Override
    public T create(T entity) throws Exception {
        return null;
    }

    @Override
    public T read(Id id) throws Exception {
        return null;
    }

    @Override
    public void update(T entity) throws Exception {

    }

    @Override
    public void delete(Id id) throws Exception {

    }

    @Override
    public List<T> findAll() throws Exception {
        return null;
    }

    protected final String tableName;
    protected Connection con;

    protected DBDao(Connection con, String tableName) throws SQLException {
        this.tableName = tableName;
        this.con = con;

        createTableIfNotExists();
    }

    protected abstract void createTableIfNotExists() throws SQLException;
}
