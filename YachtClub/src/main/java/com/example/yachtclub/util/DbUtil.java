package com.example.yachtclub.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    // 创建Druid数据源
    private static DruidDataSource dataSource = new DruidDataSource();
    private static final String URL = "jdbc:mysql://114.132.61.68:43306/yachtclub?serverTimezone=UTC&autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
    //public static final String URL = "jdbc:mysql://localhost:3306/YachtClub";
    private static final String USER = "root";
    private static final String PASSWORD = "@Aa654321";

    public DbUtil(){
        // 设置数据库连接信息
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        // 配置连接池参数
        dataSource.setInitialSize(10); // 初始化连接数
        dataSource.setMaxActive(50);  // 最大连接数
        dataSource.setMinIdle(10);    // 最小空闲连接数
        dataSource.setTestOnBorrow(true);
        dataSource.setKeepAlive(true);
        dataSource.setMinEvictableIdleTimeMillis(15);
    }

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        // 获取数据库连接
        Connection connection = dataSource.getConnection();
        return connection;
    }

}
