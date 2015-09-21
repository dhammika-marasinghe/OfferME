package com.illusionbox.offerme.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 3rd EYE
 */
public class DB {

    private static Connection c = null;

    public static Connection createDBConnection() throws ClassNotFoundException, SQLException {
        if (c == null) {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/offerme", "root", "123");
        }
        return c;
    }

    public static void update(String sql) throws ClassNotFoundException, SQLException {
        createDBConnection().createStatement().executeUpdate(sql);
    }

    public static ResultSet search(String sql) throws ClassNotFoundException, SQLException {
        return createDBConnection().createStatement().executeQuery(sql);
    }
}
