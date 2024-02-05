package cn.yzw.device.service.impl;

import cn.yzw.device.dao.impl.CategoryDaoImpl;
import cn.yzw.device.service.CategoryService;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.vo.Category;
import cn.yzw.device.vo.PageVo;

import java.util.*;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> listAll() throws Exception {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> toTree() throws Exception {

        List<Category> categoryList = listAll();
        Iterator<Category> iterator = categoryList.iterator();

        List<Category> rootNode = new ArrayList<>();

        while (iterator.hasNext()) {
            Category node = iterator.next();
            if (node.getPid() == 0) {
                rootNode.add(node);
                iterator.remove();
            }
        }

        if (!rootNode.isEmpty()) {
            rootNode.sort(new Category());
        }

        if (!categoryList.isEmpty() && !rootNode.isEmpty()) {
            rootNode.forEach(root -> constructTree(root, categoryList));
        }

        return rootNode;
    }

    private void constructTree(Category root, List<Category> categoryList) {

        Iterator<Category> iterator = categoryList.iterator();

        List<Category> child = new ArrayList<>();

        while (iterator.hasNext()) {
            Category node = iterator.next();
            if (root.getId().equals(node.getPid())) {
                child.add(node);
                iterator.remove();
            }
        }

        if (!child.isEmpty()) {
            child.sort(new Category());
        }

        if (!child.isEmpty()) {
            root.setChildren(child);
        }

        if (!categoryList.isEmpty() && !child.isEmpty()) {
            child.forEach(node -> constructTree(node, categoryList));
        }

    }

    @Override
    public Category getObjById(Integer id) throws Exception {
        return categoryDao.findById(id);
    }

    @Override
    public boolean deleteObjById(Integer id) throws Exception {
        return categoryDao.deleteById(id) > 0;
    }

    @Override
    public boolean updateObj(Category category) throws Exception {
        return categoryDao.update(category) > 0;
    }

    @Override
    public boolean addObj(Category category) throws Exception {


        String sql = "select level,max(seq) as seq from t_category \n" +
                "where level = ?\n" +
                "group by level";
        int flag = 0;
        try {
            JdbcUtil.startTransaction();
            Category obj = getObj(sql, category.getLevel());
            category.setSeq(obj.getSeq() + 1);
            flag = categoryDao.insert(category);

        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
        } finally {
            JdbcUtil.commit();
        }


        return flag >= 0;
    }


    @Override
    public Category getObj(String sql, Object... params) throws Exception {
        return categoryDao.findObject(sql,Category.class,params);
    }

    @Override
    public boolean deleteObj(Object... params) throws Exception {
        return false;
    }

    @Override
    public boolean deleteObjList(Set<Integer> ids) throws Exception {
        return false;
    }

    @Override
    public List<Category> listObj(String sql, Object... params) throws Exception {

        return categoryDao.findListObj(sql,Category.class,params);
    }

    @Override
    public PageVo<Category> pageObj(String currentPage, String rows, Map<String, Object> resources) throws Exception {
        return null;
    }
}
