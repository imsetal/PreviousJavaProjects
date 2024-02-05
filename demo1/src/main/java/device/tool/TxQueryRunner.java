package cn.yzw.device.tool;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxQueryRunner extends QueryRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxQueryRunner.class);

    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {

        Connection connection = JdbcUtil.getConnection();
        int[] rows = super.batch(connection,sql,params);

        log(connection,sql,rows,params);

        JdbcUtil.releaseConn(connection);
        return rows;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {

        Connection connection = JdbcUtil.getConnection();

        T query = super.query(connection, sql, rsh, params);

        log(connection,sql,query,params);

        JdbcUtil.releaseConn(connection);

        return query;


    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {

        Connection connection = JdbcUtil.getConnection();

        T query = super.query(connection, sql, rsh);
        log(connection,sql,query,null);
        JdbcUtil.releaseConn(connection);

        return query;
    }

    @Override
    public int update(String sql) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int update = super.update(connection,sql);
        log(connection,sql,update,null);
        JdbcUtil.releaseConn(connection);
        return update;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int update = super.update(connection,sql,param);
        log(connection,sql,update,param);
        JdbcUtil.releaseConn(connection);
        return update;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtil.getConnection();
        int update = super.update(connection,sql,params);
        log(connection,sql,update,params);
        JdbcUtil.releaseConn(connection);
        return update;
    }

    private synchronized void  log(Connection connection,String sql,Object result,Object...params){
        StringBuffer sb = new StringBuffer();
        LOGGER.info("open connection [" + connection + "]");
        LOGGER.info("sql : [" + sql + "]");
        if (params == null){
            LOGGER.info("params : []");
        }else {
            LOGGER.info("params : "+ Arrays.toString(params)+"");
        }
        if (result.getClass() == ArrayList.class){
            List list = (List) result;
            sb.append("rows : " + list.size()+", data : ");
            list.forEach(element ->{
                sb.append("\n row : ").append(element);
            });
            LOGGER.info(sb.toString());
        }else {
            LOGGER.info("result : "+result);
        }

    }
}
