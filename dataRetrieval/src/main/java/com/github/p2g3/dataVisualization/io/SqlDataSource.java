package com.github.p2g3.dataVisualization.io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataSource {

    private static SqlDataSource instance;
    private String ip;
    private String url;
    private String user;
    private String password;

    private SqlDataSource() {
        //URL needs to be changed accordingly when the IP for the ec2 changes
        ip = "52.15.208.159";
        url = System.getProperty("database.url", "jdbc:postgresql://"+ip+":5432/projsc");
        user = System.getProperty("database.username", "projsc");
        password = System.getProperty("database.password", "projsc");
    }

    public static SqlDataSource getInstance() {
        if (instance == null) {
            instance = new SqlDataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
}