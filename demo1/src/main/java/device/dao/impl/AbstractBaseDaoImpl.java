package cn.yzw.device.dao.impl;

import cn.yzw.device.tool.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.util.List;

public abstract class AbstractBaseDaoImpl<E> {

    protected   QueryRunner queryRunner = new TxQueryRunner();



    public int findCount(String sql,Object... params) throws Exception {
        if (params == null){
            return queryRunner.query(sql,rs -> {
                Integer count = null;
                while (rs.next()){
                    count = rs.getInt(1);
                }
                return count;
            });
        }
        return queryRunner.query(sql,rs -> {
            Integer count = null;
            while (rs.next()){
                count = rs.getInt(1);
            }
            return count;
        },params);
    }


    public E findObject(String sql,Class cls, Object... params) throws Exception {
        if (params == null){
            return queryRunner.query(sql,new BeanHandler<E>(cls));
        }
        return queryRunner.query(sql,new BeanHandler<E>(cls),params);
    }


    public List<E> findListObj(String sql,Class cls, Object... params) throws Exception {
        if (params == null){
            return queryRunner.query(sql,new BeanListHandler<E>(cls));
        }
        return queryRunner.query(sql,new BeanListHandler<E>(cls),params);
    }


    public List<E> findByPage(String sql,Class cls,Object... params) throws Exception {
        if (params == null){
            return queryRunner.query(sql,new BeanListHandler<E>(cls));
        }
        return queryRunner.query(sql,new BeanListHandler<E>(cls),params);
    }




}
