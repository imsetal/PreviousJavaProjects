package cn.yzw.device.web.servlet;

import cn.yzw.device.service.ApplyService;
import cn.yzw.device.service.DeviceService;
import cn.yzw.device.service.DownItemService;
import cn.yzw.device.service.FixInfoService;
import cn.yzw.device.service.impl.ApplyServiceImpl;
import cn.yzw.device.service.impl.DeviceServiceImpl;
import cn.yzw.device.service.impl.DownItemServiceImpl;
import cn.yzw.device.service.impl.FixInfoServiceImpl;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.tool.ResMessageBuilder;
import cn.yzw.device.vo.*;

import javax.lang.model.element.VariableElement;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "DeviceServlet", urlPatterns = "/device/*")
public class DeviceServlet extends BaseServlet {

    private DeviceService deviceService = new DeviceServiceImpl();
    private ApplyService applyService = new ApplyServiceImpl();
    private DownItemService downItemService = new DownItemServiceImpl();
    private FixInfoService fixInfoService = new FixInfoServiceImpl();

    public ResMessage page(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String factory = request.getParameter("factory");
        String doPerson = request.getParameter("doPerson");
        String state = request.getParameter("state");

        Map<String, Object> params = new HashMap<>();
        if (name != null && !"".equals(name)) {
            params.put("name", name);
        }
        if (category != null && !"".equals(category)) {
            params.put("category", category);
        }
        if (factory != null && !"".equals(factory)) {
            params.put("factory", factory);
        }
        if (doPerson != null && !"".equals(doPerson)) {
            params.put("doPerson", doPerson);
        }
        if (checkField(state)){
            params.put("state",state);
        }

        PageVo<Device> pageVo = null;
        try {
            pageVo = deviceService.pageObj(page, limit, params);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "服务器错误");
        }

        return new ResMessage(200, "success", pageVo);
    }

    public ResMessage add(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String specs = request.getParameter("specs");
        String count = request.getParameter("count");
        String factory = request.getParameter("factory");
        String qgp = request.getParameter("qGP");
        String price = request.getParameter("price");
        String doPerson = request.getParameter("doPerson");
        String buyDate = request.getParameter("buyDate");
        String id = request.getParameter("id");
        String category = request.getParameter("category");

        Device device = new Device();
        device.setName(name);
        device.setCategory(category);
        device.setFactory(factory);
        device.setDoPerson(doPerson);
        device.setQGP(qgp);
        device.setSpecs(specs);
        if (checkField(price)) {
            device.setPrice(Double.parseDouble(price));
        }
        ResMessage resMessage = null;
        try {
            if (checkField(buyDate)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(buyDate);
                device.setBuyTime(date);
            }
            JdbcUtil.startTransaction();
            if (checkField(id)) {

                Apply objById = applyService.getObjById(Integer.parseInt(id));

                if (objById.getState() != 1) {
                    return new ResMessage(200, "fail", "该设备审核还未通过");
                }

                String cateNum = objById.getCateNum();

                String cid = cateNum.substring(cateNum.lastIndexOf("-") + 1, cateNum.length());
                device.setCid(Integer.parseInt(cid));
                int _count = 0;
                if (checkField(count)) {
                    _count = Integer.parseInt(count);
                }
                int flag = 0;
                for (int i = 0; i < _count; i++) {
                    device.setNumber(cateNum + "d" + creatNumber((i + 1) + ""));
                    if (deviceService.addObj(device)) {
                        flag++;
                    }
                }
                if (flag == _count) {
                    objById.setIsShow(0);
                    applyService.updateObj(objById);
                    resMessage = ResMessageBuilder.success("success", "入库登记成功");
                } else resMessage = ResMessageBuilder.resMessage(200, "fail", "入库登记失败");
            } else resMessage = ResMessageBuilder.resMessage(200, "fail", "参数非法");

        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
            return ResMessageBuilder.resMessage(200, "fail", "系统错误");
        } finally {
            JdbcUtil.commit();
        }

        return resMessage;
    }


    public ResMessage delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            return new ResMessage(200, "fail", "参数错误！");
        }

        try {
            if (deviceService.deleteObjById(Integer.parseInt(id))) {
                return new ResMessage(200, "success", "删除成功！");
            } else return new ResMessage(200, "fail", "删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(500, "fail", "系统错误");
        }

    }

    public ResMessage fix(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ResMessage resMessage = null;
        if (id == null || "".equals(id)) {
            return new ResMessage(200, "fail", "参数错误！");
        }
        try {

            if (deviceService.fix(Integer.parseInt(id))) {
                resMessage = new ResMessage(200, "success", "设备开始维修");
            } else resMessage = new ResMessage(200, "fail", "出错了,设备以及开始维修，或报废");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "系统错误");
        }

        return resMessage;
    }

    public ResMessage down(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("id");
        String doPerson = request.getParameter("pdoPerson");
        String opinion = request.getParameter("opinion");
        String downDate = request.getParameter("downDate");
        ResMessage resMessage = null;
        if (id == null || "".equals(id)) {
            return new ResMessage(200, "fail", "参数错误！");
        }
        try {


            DownItem downItem = new DownItem();
            if (checkField(downDate)) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                downItem.setDownDate(dateFormat.parse(downDate));
            }

            downItem.setOpinion(opinion);
            downItem.setDoPerson(doPerson);

            if (deviceService.down(Integer.parseInt(id), downItem)) {
                resMessage = new ResMessage(200, "success", "设备报废");
            } else resMessage = new ResMessage(200, "fail", "出错了，设备已经报废");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "系统错误");
        }

        return resMessage;
    }


    public ResMessage batchDel(HttpServletRequest request, HttpServletResponse response) {

        String ids = request.getParameter("ids");
        ResMessage resMessage = null;
        Set<Integer> idsSet = new HashSet<>();
        if (ids != null && !"".equals(ids)) {
            String[] split = ids.split(",");
            for (String s : split) {
                idsSet.add(Integer.parseInt(s));
            }
            try {
                if (deviceService.deleteObjList(idsSet)) {
                    resMessage = new ResMessage(200, "success", "删除成功");
                } else {
                    resMessage = resMessage = new ResMessage(200, "fail", "删除失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResMessage(200, "fail", "服务器错误");
            }

        } else {
            resMessage = new ResMessage(200, "fail", "参数错误");
        }


        return resMessage;
    }


    public ResMessage downItemPage(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String factory = request.getParameter("factory");
        String doPerson = request.getParameter("doPerson");

        Map<String, Object> params = new HashMap<>();

        if (checkField(name)) {
            params.put("name", name);
        }
        if (checkField(category)) {
            params.put("category", category);
        }
        if (checkField(factory)) {
            params.put("factory", factory);
        }
        if (checkField(doPerson)) {
            params.put("doPerson", doPerson);
        }

        PageVo<DownItem> pageVo = null;
        try {
            pageVo = downItemService.pageObj(page, limit, params);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "服务器错误");
        }

        return new ResMessage(200, "success", pageVo);

    }

    public ResMessage fixInfoPage(HttpServletRequest request,HttpServletResponse response){
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        String fixFactory = request.getParameter("fixFactory");
        String fixPerson = request.getParameter("fixPerson");

        Map<String, Object> params = new HashMap<>();

        if (checkField(name)) {
            params.put("name", name);
        }
        if (checkField(fixFactory)) {
            params.put("fixFactory", fixFactory);
        }
        if (checkField(fixPerson)) {
            params.put("fixPerson", fixPerson);
        }
        PageVo<FixInfo> pageVo = null;
        try {
            pageVo = fixInfoService.pageObj(page, limit, params);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "服务器错误");
        }

        return new ResMessage(200, "success", pageVo);

    }


    public ResMessage wasFixed(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String fixDate = request.getParameter("fixDate");
        String fixFactory = request.getParameter("fixFactory");
        String fixPerson = request.getParameter("fixPerson");
        String fixPrice = request.getParameter("fixPrice");
        String opinion = request.getParameter("opinion");

        FixInfo fixInfo = new FixInfo();

        fixInfo.setFixFactory(fixFactory);
        fixInfo.setFixPerson(fixPerson);
        fixInfo.setOpinion(opinion);

        if (checkField(fixPrice)){
            fixInfo.setFixPrice(Double.parseDouble(fixPrice));
        }

        if (checkField(fixDate)){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            fixInfo.setFixDate(df.parse(fixDate));
        }

        if (checkField(id)){
            if (deviceService.wasFixed(Integer.parseInt(id),fixInfo)) {
                return  new ResMessage(200,"success","修理成功");
            }
        }else {
            return new ResMessage(200,"fail","参数非法");
        }



        return new ResMessage(200,"fail","系统错误");
    }

    boolean checkField(String field) {
        return field != null && !"".equals(field);
    }

    private String creatNumber(String s) {
        int i = 6 - s.length();
        String str = "";
        for (int j = 0; j < i; j++) {
            str += "0";
        }
        return str += s;
    }


}
