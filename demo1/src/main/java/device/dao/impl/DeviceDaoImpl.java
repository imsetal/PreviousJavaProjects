package cn.yzw.device.dao.impl;

import cn.yzw.device.dao.BaseDao;
import cn.yzw.device.vo.Device;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

public class DeviceDaoImpl extends AbstractBaseDaoImpl<Device> implements BaseDao<Device,Integer> {


    private static final String BASE_FIELD = " id,number,name,category,specs,price,factory,QGP,state,buyTime doPerson ";

    @Override
    public List<Device> findAll() throws Exception {

        String sql = "select"+BASE_FIELD+"from t_device";
        return findListObj(sql, Device.class,null);
    }

    @Override
    public int update(Device device) throws Exception {

        String sql = "update t_device set name = ?,factory = ?,specs = ?," +
                "price = ?,QGP = ?,state = ? where id = ?";

        return queryRunner.update(sql,device.getName(),device.getFactory(),device.getSpecs(),
                device.getPrice(),device.getQGP(),device.getState(),device.getId());
    }

    @Override
    public int insert(Device device) throws Exception {
        String sql = "insert into t_device(name,QGP,price,specs,factory,category,buyTime,doPerson,cid,number) values " +
                "(?,?,?,?,?,?,?,?,?,?)";
        return queryRunner.update(sql,device.getName(),device.getQGP(),device.getPrice(),device.getSpecs(),
                device.getFactory(),device.getCategory(),device.getBuyTime(),device.getDoPerson(),device.getCid(),
                device.getNumber());
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        String sql = "delete from t_device where id = ?";
        return queryRunner.update(sql,id);
    }

    @Override
    public Device findById(Integer id) throws Exception {
        String sql = "select"+BASE_FIELD+"from t_device where id = ?";
        return findObject(sql,Device.class,id);
    }

    public List<Map<String,Object>> getPieData()throws Exception{
        String sql = "select category as name,count(1) as value from t_device where state != 2 " +
                "group by category";
        return queryRunner.query(sql,new MapListHandler());
    }

    public List<Map<String,Object>> getDeviceValueData()throws Exception{
        String sql = "select category as name,sum(price) as data from t_device where state != 2 " +
                "group by category";
        return queryRunner.query(sql,new MapListHandler());
    }


}
