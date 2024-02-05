package cn.yzw.device.web.servlet;

import cn.yzw.device.dao.ApplyDao;
import cn.yzw.device.dao.impl.ApplyDaoImpl;
import cn.yzw.device.dao.impl.DeviceDaoImpl;
import cn.yzw.device.dao.impl.DownItemDaoImpl;
import cn.yzw.device.dao.impl.FixInfoDaoImpl;
import cn.yzw.device.service.ApplyService;
import cn.yzw.device.service.DeviceService;
import cn.yzw.device.service.DownItemService;
import cn.yzw.device.service.FixInfoService;
import cn.yzw.device.service.impl.ApplyServiceImpl;
import cn.yzw.device.service.impl.DeviceServiceImpl;
import cn.yzw.device.service.impl.DownItemServiceImpl;
import cn.yzw.device.service.impl.FixInfoServiceImpl;
import cn.yzw.device.vo.ResMessage;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ConsoleServlet",urlPatterns = "/console/*")
public class ConsoleServlet extends BaseServlet {

    private DeviceDaoImpl deviceDao = new DeviceDaoImpl();
    private ApplyDaoImpl applyDao = new ApplyDaoImpl();
    private DownItemDaoImpl downItemDao = new DownItemDaoImpl();
    private FixInfoDaoImpl fixInfoDao = new FixInfoDaoImpl();

    public ResMessage getElemNums(HttpServletRequest request, HttpServletResponse response){
        ResMessage resMessage = null;
        List<Integer> list = new ArrayList<>();
        try {
            int deviceCount = deviceDao.findCount("select count(1) from t_device where state != ?", 2);
            int applyCount = applyDao.findCount("select count(1) from t_apply where isShow = ?",1);
            int fixCount = deviceDao.findCount("select count(1) from t_device where state = ?", 1);
            int downItemCount = downItemDao.findCount("select count(1) from t_downItem");
            list.add(deviceCount);
            list.add(fixCount);
            list.add(downItemCount);
            list.add(applyCount);
            resMessage = new ResMessage(200,"success",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200,"fail","系统错误");
        }
        return resMessage;
    }


    public ResMessage getPieData(HttpServletRequest request,HttpServletResponse response){
        ResMessage resMessage = null;
        try {
            List<Map<String, Object>> pieData = deviceDao.getPieData();
            resMessage = new ResMessage(200,"success",pieData);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200,"fail","系统错误");
        }
        return resMessage;
    }

    public ResMessage getDeviceValueData(HttpServletRequest request,HttpServletResponse response){
        ResMessage resMessage = null;

        try {
            List<Map<String, Object>> data = deviceDao.getDeviceValueData();
            resMessage = new ResMessage(200,"success",data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200,"fail","系统错误");
        }

        return resMessage;
    }

    public ResMessage getDeviceIO(HttpServletRequest request,HttpServletResponse response){
        ResMessage resMessage = null;
        List<Integer> data = new ArrayList();
        try {
            int deviceAmount = deviceDao.findCount("select sum(price) as amount from t_device where state != 2");
            int fixAmount = fixInfoDao.findCount("select sum(fixPrice) as amount from t_fixInfo");
            int applyAmount = applyDao.findCount("select sum(amount) as amount from t_apply where isShow = 0");
            int downAmount = downItemDao.findCount("select sum(price) as amount from t_downItem");


            data.add(deviceAmount);
            data.add(fixAmount);
            data.add(applyAmount);
            data.add(downAmount);
            resMessage = new ResMessage(200,"success",data);
        } catch (Exception e) {
            e.printStackTrace();
          return new ResMessage(200,"fail","系统错误");
        }
        return resMessage;
    }


}
