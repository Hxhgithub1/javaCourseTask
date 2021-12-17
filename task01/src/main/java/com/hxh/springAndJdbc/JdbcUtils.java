package com.hxh.springAndJdbc;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Data
public class JdbcUtils {
    private String driver;
    private String url;
    private String userName;
    private String passWord;

    public JdbcUtils(String driver, String url, String userName, String passWord) throws ClassNotFoundException {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        Class.forName(driver);
    }
    public Connection getConnection(String url,String user, String password) throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
    public List<T> queryForList(){

    }

}
