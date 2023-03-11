package ma.enset.tp2javafxwithbd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBsingleton {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_TP2JAFAFX","root","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
