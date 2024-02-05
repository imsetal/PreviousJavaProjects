package cn.yzw.device.service.impl;

import cn.yzw.device.dao.FixInfoDao;
import cn.yzw.device.dao.impl.DeviceDaoImpl;
import cn.yzw.device.dao.impl.DownItemDaoImpl;
import cn.yzw.device.dao.impl.FixInfoDaoImpl;
import cn.yzw.device.service.DeviceService;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.vo.Device;
import cn.yzw.device.vo.DownItem;
import cn.yzw.device.vo.FixInfo;
import cn.yzw.device.vo.PageVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DeviceServiceImpl implements DeviceService {

    private DeviceDaoImpl deviceDao = new DeviceDaoImpl();
    private DownItemDaoImpl downItemDao = new DownItemDaoImpl();
    private FixInfoDaoImpl fixInfoDao = new FixInfoDaoImpl();


    @Override
    public List<Device> listAll() throws Exception {
        return deviceDao.findAll();
    }

    @Override
    public Device getObjById(Integer id) throws Exception {
        return deviceDao.findById(id);
    }

    @Override
    public boolean deleteObjById(Integer id) throws Exception {
        return deviceDao.deleteById(id) > 0;
    }

    @Override
    public boolean updateObj(Device device) throws Exception {
        return deviceDao.update(device) > 0;
    }

    @Override
    public boolean addObj(Device device) throws Exception {
        return deviceDao.insert(device) > 0 ;
    }


    @Override
    public Device getObj(String sql, Object... params) throws Exception {
        return null;
    }

    @Override
    public boolean deleteObj(Object... params) throws Exception {
        return false;
    }

    @Override
    public boolean deleteObjList(Set<Integer> ids) throws Exception {
        if (ids == null || ids.isEmpty()) return false;
        int count = 0;
        for (Integer id : ids) {
            if (deleteObj(id)) {
                count++;
            }
        }
        return count == ids.size();
    }

    @Override
    public List<Device> listObj(String sql, Object... params) throws Exception {
        return null;
    }

    @Override
    public PageVo<Device> pageObj(String currentPage,String rows,Map<String, Object> resources) throws Exception {

        List<Object> params = new ArrayList<>();

        StringBuffer queryPage = new StringBuffer();

        queryPage.append("select ").
                append(" id,number,name,category,specs,price,factory,QGP,state,buyTime,doPerson ")
                .append("from t_device ");

        StringBuffer queryCount = new StringBuffer();

        queryCount.append("select count(1) from t_device");

        PageVo<Device> pageVo = null;
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
                int count = deviceDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                System.out.println("queryPage:"+queryPage.toString());
                System.out.println("queryCount:"+queryCount.toString());

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());

                List<Device> data = deviceDao.findByPage(queryPage.toString(), Device.class, params.toArray());

                pageVo.setData(data);

            }else {
                queryPage.append("limit ? ,?");

                int count = deviceDao.findCount(queryCount.toString(), params.toArray());

                pageVo = new PageVo<>(currentPage,rows,count);

                params.add(pageVo.getStart());
                params.add(pageVo.getRows());

                System.out.println("queryPage:"+queryPage.toString());
                System.out.println("queryCount:"+queryCount.toString());

                List<Device> data = deviceDao.findByPage(queryPage.toString(), Device.class, params.toArray());

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

    @Override
    public boolean fix(Integer id) throws Exception {

        try {
            JdbcUtil.startTransaction();
            Device obj = deviceDao.findById(id);

            if (obj.getState() == 1 || obj.getState() == 2) return false;

            obj.setState(1);

            return deviceDao.update(obj) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
        }finally {
            JdbcUtil.commit();
        }
        return false;
    }

    @Override
    public boolean down(Integer id,DownItem downItem) throws Exception {
        try {
            JdbcUtil.startTransaction();

            Device obj = deviceDao.findById(id);

            downItem.setBuyDate(obj.getBuyTime());
            downItem.setCategory(obj.getCategory());
            downItem.setFactory(obj.getFactory());
            downItem.setName(obj.getName());
            downItem.setPrice(obj.getPrice());
            downItem.setQGP(obj.getQGP());
            downItem.setSpecs(obj.getSpecs());
            downItem.setNumber(obj.getNumber());

            if (obj.getState() == 2) return false;

            obj.setState(2);

            return deviceDao.update(obj) > 0 && downItemDao.add(downItem) > 0;

        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
        } finally {
            JdbcUtil.commit();
        }
        return false;
    }

    public boolean wasFixed(Integer id, FixInfo fixInfo)throws Exception{

        try {
            JdbcUtil.startTransaction();
            Device byId = deviceDao.findById(id);
            if ( byId.getState() == 0 || byId.getState() == 2) return false;
            fixInfo.setName(byId.getName());
            fixInfo.setNumber(byId.getNumber());
            byId.setState(0);
            return deviceDao.update(byId) > 0 && fixInfoDao.insert(fixInfo) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JdbcUtil.rollback();
        } finally {
            JdbcUtil.commit();
        }
        return false;
    }



}
