package cn.yzw.device.dao.impl;

import cn.yzw.device.dao.CategoryDao;
import cn.yzw.device.vo.Category;


import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends AbstractBaseDaoImpl<Category> implements CategoryDao {


    @Override
    public List<Category> findAll()throws Exception {
        String sql = "select id,pid,level,seq,name as title from t_category";



        return this.findListObj(sql,Category.class);
    }

    @Override
    public int update(Category category) throws SQLException {
        String sql = "update t_category set name=? where id = ?";
        return queryRunner.update(sql,category.getTitle(),category.getId());
    }

    @Override
    public int insert(Category category) throws SQLException {
        String sql = "insert into t_category(name,level,pid,seq)values(?,?,?,?)";
        return queryRunner.update(sql,category.getTitle(),category.getLevel(),
                category.getPid(),category.getSeq());
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        String sql = "delete from t_category where id = ?";
        return queryRunner.update(sql,id);
    }

    @Override
    public Category findById(Integer id) throws Exception {
        String sql = "select id,pid,level,seq,name as title from t_category where id = ?";

        return (Category) this.findObject(sql,Category.class,id);
    }




}
