package cn.yzw.device.service.impl;

import cn.yzw.device.dao.impl.ApplyDaoImpl;
import cn.yzw.device.service.ApplyService;
import cn.yzw.device.vo.Apply;
import cn.yzw.device.vo.PageVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplyServiceImpl implements ApplyService {

    private ApplyDaoImpl applyDao = new ApplyDaoImpl();

    @Override
    public List<Apply> listAll() throws Exception {
        return applyDao.findAll();
    }

    @Override
    public Apply getObjById(Integer id) throws Exception {
        return applyDao.findById(id);
    }

    @Override
    public boolean deleteObjById(Integer id) throws Exception {
        return applyDao.deleteById(id) > 0;
    }

    @Override
    public boolean updateObj(Apply apply) throws Exception {
        return applyDao.update(apply) > 0;
    }

    @Override
    public boolean addObj(Apply apply) throws Exception {
        return applyDao.insert(apply) > 0;
    }


    @Override
    public Apply getObj(String sql, Object... params) throws Exception {
        return applyDao.findObject(sql,Apply.class,params);
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
    public List<Apply> listObj(String sql, Object... params) throws Exception {
        return applyDao.findListObj(sql,Apply.class,params);
    }

    @Override
    public PageVo<Apply> pageObj(String currentPage, String rows, Map<String, Object> resources) throws Exception {
        //1.实例化资源，querySql用来拼接分页查询sql语句
        //countSql用来拼接数量查询的sql语句
        //params 用来储存参数
        StringBuffer querySql = new StringBuffer();
        StringBuffer countSql = new StringBuffer();
        List<Object> params = new ArrayList<>();

        querySql.append("select id,name,count,applyDate,applyPerson,amount,reason,category,state,")
                .append("specs,price,opinion,checkDate,checkPerson")
                .append(" from t_apply ");

        countSql.append("select count(*) from t_apply ");

        //2.申明pageVo对象
        PageVo<Apply> page = null;
        //3.遍历resources里的参数，开始拼接sql语句
        if (resources != null && resources.size() != 0){
            querySql.append("where isShow = 1 and ");
            countSql.append("where isShow = 1 and ");
            Set<String> keySet = resources.keySet();

            keySet.forEach(key ->{
                Object value = resources.get(key);
                if (value != null){
                    querySql.append(key).append(" like concat('%',?,'%') and ");
                    countSql.append(key).append(" like concat('%',?,'%') and ");
                    params.add(value);
                }
            });
            //删除多余的and，加入limit语句
            countSql.delete(countSql.lastIndexOf("and "),countSql.length());
            querySql.delete(querySql.lastIndexOf("and"),querySql.length());
            querySql.append("limit ? ,?");

            //4. 查询数据量
            int count = applyDao.findCount(countSql.toString(), params.toArray());
            //5.实例化pageVo
            page = new PageVo<>(currentPage,rows,count);
            //6.加入参数。（当前页查询下标，每页的数据量）
            params.add(page.getStart());
            params.add(page.getRows());

            //7.查询出分页数据，分装入PageVo
            List<Apply> data = applyDao.findByPage(querySql.toString(), Apply.class, params.toArray());

            page.setData(data);

        }else {
            countSql.append("where isShow = 1 ");
            querySql.append("where isShow = 1 ");
            querySql.append("limit ? ,?");

            int count = applyDao.findCount(countSql.toString(), params.toArray());

            page = new PageVo<>(currentPage,rows,count);

            params.add(page.getStart());
            params.add(page.getRows());


            List<Apply> data = applyDao.findByPage(querySql.toString(),Apply.class, params.toArray());

            page.setData(data);
        }



        return page;
    }
}
