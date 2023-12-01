package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public abstract class DBDao<T, Id> implements CrudDao<T, Id>{
    @Override
    public T create(T entity) throws Exception {
        StringBuilder query = new StringBuilder("INSERT INTO " + this.tableName + " (");
        StringBuilder values = new StringBuilder(" VALUES (");
        for (var field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == UUID.class) {
                query.append(field.getName()).append(", ");
                values.append("UUID_TO_BIN(?), ");
            }
            else if (field.get(entity) != null) {
                query.append(field.getName()).append(", ");
                values.append("?, ");
            }
        }

        PreparedStatement statement = con.prepareStatement(query.substring(0, query.length() - 2) + ")" +
                values.substring(0, values.length() - 2) + ")");

        int i = 1;
        for (var field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() == UUID.class) {
                statement.setObject(i, field.get(entity).toString());
                i++;
            }
            else if (field.get(entity) != null) {
                statement.setObject(i, field.get(entity));
                i++;
            }
        }

        statement.executeUpdate();
        return entity;
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
