package cn.yzw.device.web.servlet;

import cn.yzw.device.service.ApplyService;
import cn.yzw.device.service.CategoryService;
import cn.yzw.device.service.impl.ApplyServiceImpl;
import cn.yzw.device.service.impl.CategoryServiceImpl;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.tool.ResMessageBuilder;
import cn.yzw.device.vo.Apply;
import cn.yzw.device.vo.Category;
import cn.yzw.device.vo.PageVo;
import cn.yzw.device.vo.ResMessage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "ApplyServlet", urlPatterns = "/apply/*")
public class ApplyServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    private ApplyService applyService = new ApplyServiceImpl();

    public ResMessage add(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String level1 = request.getParameter("level1");
        String level2 = request.getParameter("level2");
        String level3 = request.getParameter("level3");
        String name = request.getParameter("name");

        String count = request.getParameter("count");
        String price = request.getParameter("price");
        String applyPerson = request.getParameter("applyPerson");
        String applyDate = request.getParameter("applyDate");
        String reason = request.getParameter("reason");
        String specs = request.getParameter("specs");

        Apply apply = new Apply();
        apply.setName(name);
        apply.setReason(reason);
        apply.setSpecs(specs);
        apply.setApplyPerson(applyPerson);
        apply.setCateNum(level1 + "-" + level2 + "-" + level3);

        ResMessage resMessage = null;

        try {
            JdbcUtil.startTransaction();

            if (checkField(level3)) {
                Category objById = categoryService.getObjById(Integer.parseInt(level3));
                apply.setCid(objById.getId());
                apply.setCategory(objById.getTitle());
            }
            if (checkField(count) && checkField(price)) {
                int c = Integer.parseInt(count);
                apply.setCount(c);
                double v = Double.parseDouble(price);
                apply.setPrice(v);
                apply.setAmount(v * c);
            }

            if (checkField(applyDate)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(applyDate);
                apply.setApplyDate(date);
            }

            if (applyService.addObj(apply)) {
                resMessage = new ResMessage(200, "success", "申购单提交成功！");
            } else resMessage = new ResMessage(200, "fail", "申购单提交失败！");

        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
            return new ResMessage(200, "fail", "系统错误！");
        } finally {
            JdbcUtil.commit();
        }

        return resMessage;
    }

    public ResMessage page(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String applyPerson = request.getParameter("applyPerson");
        String state = request.getParameter("state");
        Map<String, Object> params = new HashMap<>();
        if (checkField(name)) {
            params.put("name", name);
        }
        if (checkField(category)) {
            params.put("category", category);
        }
        if (checkField(applyPerson)) {
            params.put("applyPerson", applyPerson);
        }
        if (checkField(state)) {
            params.put("state", Integer.parseInt(state));
        }
        try {
            PageVo<Apply> pageVo = applyService.pageObj(page, limit, params);
            return new ResMessage(200, "success", pageVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200, "fail", "系统错误！");
        }

    }

    public ResMessage delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ResMessage message = null;

        try {
            if (checkField(id)) {
                if (applyService.deleteObjById(Integer.parseInt(id))) {
                    message = new ResMessage(200,"success","删除成功");
                }else message = new ResMessage(200,"fail","删除失败");
            }else message = new ResMessage(200,"fail","参数非法");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResMessage(200,"fail","服务器错误！");
        }

        return message;
    }

    public ResMessage batchDelete(HttpServletRequest request, HttpServletResponse response) {

        String ids = request.getParameter("ids");
        Set<Integer> idSet = new HashSet<>();
        ResMessage message = null;
        try {
            if (checkField(ids)) {
                String[] split = ids.split(",");
                for (String s : split) {
                    idSet.add(Integer.parseInt(s));
                }
                if (applyService.deleteObjList(idSet)) {
                    message = new ResMessage(200,"success","删除成功");
                }else message = new ResMessage(200,"fail","删除失败");
            }else message = new ResMessage(200,"fail","参数非法");
        } catch (Exception e) {
            e.printStackTrace();
            return ResMessageBuilder.resMessage(200,"fail","服务器错误");
        }

        return message;
    }

    public ResMessage edit(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }


    public ResMessage checkApply(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        return getResMessage(request,1);
    }

    public ResMessage rejectApply(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        return getResMessage(request,2);
    }

    private ResMessage getResMessage(HttpServletRequest request,Integer state) throws SQLException {
        String id = request.getParameter("id");
        String opinion = request.getParameter("opinion");
        String checkDate = request.getParameter("checkDate");
        String checkPerson = request.getParameter("checkPerson");

        ResMessage resMessage = null;
        try {
            JdbcUtil.startTransaction();
            if (checkField(id)){
                Apply objById = applyService.getObjById(Integer.parseInt(id));
                objById.setOpinion(opinion);
                if (checkField(checkDate)){
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sf.parse(checkDate);
                    objById.setCheckDate(date);
                }
                objById.setCheckPerson(checkPerson);
                objById.setState(state);
                if (applyService.updateObj(objById)) {
                    resMessage = ResMessageBuilder.resMessage(200,"success","操作成功");
                }else resMessage = ResMessageBuilder.resMessage(200,"fail","操作失败");
            }else resMessage = ResMessageBuilder.resMessage(200,"fail","参数非法");


        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtil.rollback();
            return new ResMessage(200,"fail","服务器错误");
        } finally {
            JdbcUtil.commit();
        }
        return resMessage;
    }

    boolean checkField(String field) {
        return field != null && !"".equals(field);
    }
}
