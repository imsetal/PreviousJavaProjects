package org.example.crms.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DbUtil {
    // 创建Druid数据源
    private static DruidDataSource dataSource = new DruidDataSource();
    private static final String IP="localhost";
    private static final String URL = "jdbc:mysql://localhost:3306/crms?serverTimezone=UTC&autoReconnect=true&useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

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

    public static void bf() throws SQLException, ClassNotFoundException {
        try {
            // 构建 mysqldump 命令
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "mysqldump",
                    "-u", "root",
                    "-pYourPassword",  // 替换成你的MySQL密码
                    "--host=localhost",
                    "--port=3306",
                    "--databases", "crms"
            );

            // 生成带时间戳的文件名
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String timestamp = dateFormat.format(new Date());
            String fileName = timestamp + ".sql";

            // 设置导出文件路径
            processBuilder.redirectOutput(ProcessBuilder.Redirect.to(Paths.get(fileName).toFile()));

            // 启动进程并等待命令执行完成
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("导出成功！");
            } else {
                System.err.println("导出失败，退出码：" + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
