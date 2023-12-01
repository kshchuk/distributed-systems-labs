package org.example.dao;

import java.sql. *;

public class DAOManager {
    public enum Table { AIRLINE, FLIGHT }

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        if(this.con==null || this.con.isClosed())
            this.con = DriverManager.getConnection(
                    this.DATABASE_URL + "/" + this.DATABASE_NAME,
                    this.DATABASE_USER_NAME,
                    this.DATABASE_USER_PASSWORD
            );
    }

    public void close() throws SQLException {
        if(this.con!=null && !this.con.isClosed())
            this.con.close();
    }
    private Connection con;
    private String DATABASE_URL = "jdbc:mysql://localhost:3306";
    private String DATABASE_NAME = "airlines";
    private String DATABASE_USER_NAME = "root";
    private String DATABASE_USER_PASSWORD = "secret";
    private DAOManager() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        System.getenv().forEach((k, v) -> {
            switch (k) {
                case "DATABASE_URL" -> this.DATABASE_URL = v;
                case "DATABASE_NAME" -> this.DATABASE_NAME = v;
                case "DATABASE_USER_NAME" -> this.DATABASE_USER_NAME = v;
                case "DATABASE_USER_PASSWORD" -> this.DATABASE_USER_PASSWORD = v;
            }
        });
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static
        {
            ThreadLocal<DAOManager> dm;
            try
            {
                dm = ThreadLocal.withInitial(() -> {
                    try
                    {
                        return new DAOManager();
                    }
                    catch(Exception e)
                    {
                        return null;
                    }
                });
            }
            catch(Exception e) { dm = null; }
            INSTANCE = dm;
        }

    }

    public CrudDao getDAO(Table t) throws SQLException
    {
        if(this.con == null || this.con.isClosed()) //Let's ensure our connection is open
            this.open();

        return switch (t) {
            case AIRLINE -> new AirlineDBDao(this.con, "airline");
            // case FLIGHT -> new FlightDao(this.con, "flight") {);
            default -> throw new SQLException("Trying to link to an unexistant table.");
        };

    }

    private void init() throws SQLException {
        if(this.con == null || this.con.isClosed()) //Let's ensure our connection is open
            this.open();

        PreparedStatement statement = con.prepareStatement("CREATE DATABASE IF NOT EXISTS " + this.DATABASE_NAME + ";");
        statement.executeUpdate();

        statement = con.prepareStatement("USE " + this.DATABASE_NAME + ";");
        statement.executeUpdate();

        statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS airline (" +
                "airline_id BINARY(16) PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "code VARCHAR(255) NOT NULL," +
                "country VARCHAR(255) NOT NULL" +
                ");");
        statement.executeUpdate();

        statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS flight (" +
                "flight_id BINARY(16) PRIMARY KEY," +
                "airline_id BINARY(16) NOT NULL," +
                "departure VARCHAR(255) NOT NULL," +
                "arrival VARCHAR(255) NOT NULL," +
                "departure_time DATETIME NOT NULL," +
                "arrival_time DATETIME NOT NULL," +
                "price DOUBLE NOT NULL," +
                "FOREIGN KEY (airline_id) REFERENCES airline(airline_id)" +
                ");");
        statement.executeUpdate();
    }
}