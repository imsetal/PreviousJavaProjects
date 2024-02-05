package cn.yzw.device.tool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    private static DataSource dataSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtil.class);

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }


    /**
     * 获取当前线程的连接
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) return connection;
        return dataSource.getConnection();
    }

    /**
     * 开启手动提交事务
     */
    public static void startTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            LOGGER.error("connection was be start Transaction,can not start again");
        }
        connection = getConnection();
        LOGGER.info("connection [" + connection + "] start Transaction");
        connection.setAutoCommit(false);
        threadLocal.set(connection);

    }

    /**
     * 事务回滚
     */
    public static void rollback() throws SQLException {

        Connection connection = threadLocal.get();
        //事务失败，事务回滚，关闭连接，并移除
        if (connection != null) {
            connection.rollback();
            LOGGER.info("Transaction fail,connection [" + connection + "] rollback");
            connection.close();
            LOGGER.info("connection close");
            connection = null;
            threadLocal.remove();
            LOGGER.info("threadLocal remove connection");
        }
    }

    /**
     * 事务提交
     */
    public static void commit() throws SQLException {
        Connection connection = threadLocal.get();
        //事务提交，事务结束，关闭连接，并移除
        if (connection != null) {
            connection.commit();
            LOGGER.info("connection [" + connection + "] commit");
            connection.close();
            LOGGER.info("connection close");
            connection = null;
            threadLocal.remove();
            LOGGER.info("threadLocal remove connection");
        }
    }

    /**
     * 关闭连接
     */
    public static void releaseConn(Connection connection) throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            LOGGER.info("close connection [" + connection + "]");
            connection.close();
        }
        if (connection != conn) {
            if (conn != null && !conn.isClosed()) {
                LOGGER.info("close connection [" + connection + "]");
                connection.close();
            }
        }
    }

}
