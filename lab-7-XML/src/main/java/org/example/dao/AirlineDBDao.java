package org.example.dao;

import org.example.model.Airline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AirlineDBDao extends DBDao<Airline, UUID> implements AirlineDao {

    protected AirlineDBDao(Connection con, String tableName) throws SQLException {
        super(con, tableName);


    }

    @Override
    protected void createTableIfNotExists() throws SQLException {
        PreparedStatement statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS airlines (" +
                "airline_id UUID PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "code VARCHAR(255) NOT NULL," +
                "country VARCHAR(255) NOT NULL" +
                ");");
        statement.executeUpdate();
    }
}
