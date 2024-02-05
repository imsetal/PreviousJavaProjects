package cn.yzw.device.service.impl;

import cn.yzw.device.dao.impl.DownItemDaoImpl;
import cn.yzw.device.service.DownItemService;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.vo.Device;
import cn.yzw.device.vo.DownItem;
import cn.yzw.device.vo.PageVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DownItemServiceImpl implements DownItemService {

    private DownItemDaoImpl downItemDao = new DownItemDaoImpl();

    @Override
    public List<DownItem> listAll() throws Exception {
        return null;
    }

    @Override
    public DownItem getObjById(Integer id) throws Exception {
        return null;
    }

    @Override
    public boolean deleteObjById(Integer id) throws Exception {
        return false;
    }

    @Override
    public boolean updateObj(DownItem downItem) throws Exception {
        return false;
    }

    @Override
    public boolean addObj(DownItem downItem) throws Exception {
        return downItemDao.add(downItem) > 0;
    }

    @Override
    public DownItem getObj(String sql, Object... params) throws Exception {
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
    public List<DownItem> listObj(String sql, Object... params) throws Exception {
        return null;
    }

    @Override
    public PageVo<DownItem> pageObj(String currentPage, String rows, Map<String, Object> resources) throws Exception {

        List<Object> params = new ArrayList<>();

        StringBuffer queryPage = new StringBuffer();

        queryPage.append("select ").
                append(" id,number,name,category,specs,price,factory,")
                .append("QGP,buyDate,opinion,downDate,doPerson ")
                .append("from t_downItem ");

        StringBuffer queryCount = new StringBuffer();

        queryCount.append("select count(1) from t_downItem");

        PageVo<DownItem> pageVo = null;
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
                int count = downItemDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                System.out.println("queryPage:"+queryPage.toString());
                System.out.println("queryCount:"+queryCount.toString());

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());

                List<DownItem> data = downItemDao.findByPage(queryPage.toString(), DownItem.class, params.toArray());

                pageVo.setData(data);

            }else {
                queryPage.append("limit ? ,?");

                int count = downItemDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());

                System.out.println("queryPage:"+queryPage.toString());
                System.out.println("queryCount:"+queryCount.toString());

                List<DownItem> data = downItemDao.findByPage(queryPage.toString(), DownItem.class, params.toArray());

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
