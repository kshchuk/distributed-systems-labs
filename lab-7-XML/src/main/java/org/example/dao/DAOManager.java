package org.example.dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql. *;

public class DAOManager {

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        if(this.con==null || this.con.isClosed())
            this.con = src.getConnection();
    }

    public void close() throws SQLException {
        if(this.con!=null && !this.con.isClosed())
            this.con.close();
    }

    private final DataSource src;
    private Connection con;

    private DAOManager() throws Exception {
        InitialContext ctx = new InitialContext();
        this.src = (DataSource)ctx.lookup("jndi/MYSQL");
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static
        {
            ThreadLocal<DAOManager> dm;
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
            dm = null;
            INSTANCE = dm;
        }
    }
}