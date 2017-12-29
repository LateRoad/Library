package com.lateroad.library.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException {
//        ResourceBundle resource = ResourceBundle.getTextBundle("database.properties");
//        System.out.println(resource.getString("db.url"));
//        String url = resource.getString("db.url");
//        String user = resource.getString("db.user");
//        String pass = resource.getString("db.password");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "021929");

    }
}
