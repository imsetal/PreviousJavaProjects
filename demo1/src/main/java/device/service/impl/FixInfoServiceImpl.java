package cn.yzw.device.service.impl;

import cn.yzw.device.dao.impl.FixInfoDaoImpl;
import cn.yzw.device.service.FixInfoService;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.vo.Device;
import cn.yzw.device.vo.FixInfo;
import cn.yzw.device.vo.PageVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FixInfoServiceImpl implements FixInfoService {

    private FixInfoDaoImpl fixInfoDao = new FixInfoDaoImpl();

    @Override
    public List<FixInfo> listAll() throws Exception {
        return null;
    }

    @Override
    public FixInfo getObjById(Integer id) throws Exception {
        return null;
    }

    @Override
    public boolean deleteObjById(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean updateObj(FixInfo fixInfo) throws Exception {
        return false;
    }

    @Override
    public boolean addObj(FixInfo fixInfo) throws Exception {
        return fixInfoDao.insert(fixInfo) > 0;
    }

    @Override
    public FixInfo getObj(String sql, Object... params) throws Exception {
        return null;
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
    public List<FixInfo> listObj(String sql, Object... params) throws Exception {
        return null;
    }

    @Override
    public PageVo<FixInfo> pageObj(String currentPage, String rows, Map<String, Object> resources) throws Exception {
        List<Object> params = new ArrayList<>();

        StringBuffer queryPage = new StringBuffer();

        queryPage.append("select ").
                append(" id,number,name,fixFactory,fixDate,fixPrice,fixPerson,opinion ")
                .append("from t_fixInfo ");

        StringBuffer queryCount = new StringBuffer();

        queryCount.append("select count(1) from t_fixInfo");

        PageVo<FixInfo> pageVo = null;
        try {
            JdbcUtil.startTransaction();
            if (resources != null && resources.size() != 0){
                queryPage.append(" where ");
                queryCount.append(" where ");
                Set<String> set = resources.keySet();
                set.forEach(key ->{
                    Object value = resources.get(key);
                    if (value != null){
                        queryPage.append(key).append(" like concat('%',?,'%') and ");
                        queryCount.append(key).append(" like concat('%',?,'%') and ");
                        params.add(value);
                    }
                });
                queryCount.delete(queryCount.lastIndexOf("and "),queryCount.length());
                queryPage.delete(queryPage.lastIndexOf("and"),queryPage.length());
                queryPage.append("limit ? ,?");
                int count = fixInfoDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());

                List<FixInfo> data = fixInfoDao.findByPage(queryPage.toString(), FixInfo.class, params.toArray());

                pageVo.setData(data);

            }else {
                queryPage.append("limit ? ,?");

                int count = fixInfoDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());


                List<FixInfo> data = fixInfoDao.findByPage(queryPage.toString(), FixInfo.class, params.toArray());

                pageVo.setData(data);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
        } finally {
            JdbcUtil.commit();
        }

        return pageVo;
    }
}
