package cn.yzw.device.web.servlet;

import cn.yzw.device.service.CategoryService;
import cn.yzw.device.service.impl.CategoryServiceImpl;
import cn.yzw.device.tool.JdbcUtil;
import cn.yzw.device.tool.ResMessageBuilder;
import cn.yzw.device.vo.Category;
import cn.yzw.device.vo.ResMessage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public ResMessage categoryTree(HttpServletRequest request, HttpServletResponse response) {
        List<Category> tree = null;
        try {
            tree = categoryService.toTree();
        } catch (Exception e) {
            e.printStackTrace();
            return ResMessageBuilder.resMessage(200, "fail", "服务器错误");
        }
        return ResMessageBuilder.success("success", tree);
    }

    public ResMessage getCategoryByPid(HttpServletRequest request, HttpServletResponse response) {

        String pid = request.getParameter("pid");

        if (pid == null || "".equals(pid)) {
            return ResMessageBuilder.resMessage(200, "fail", "pid为空或非法字符");
        } else {

            String sql = "select id,pid,level,seq,name as title from t_category where pid = ?";
            List<Category> list = null;
            try {
                list = categoryService.listObj(sql, Integer.parseInt(pid));
                return ResMessageBuilder.resMessage(200, "success", list);
            } catch (Exception e) {
                e.printStackTrace();
                return ResMessageBuilder.resMessage(200, "fail", "服务器错误！");
            }
        }

    }

    public ResMessage add(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String pid = request.getParameter("pid");
        String title = request.getParameter("title");
        String level = request.getParameter("level");

        Category category = new Category();

        ResMessage resMessage = null;


        category.setPid(Integer.parseInt(pid));
        category.setTitle(title);
        category.setLevel(Integer.parseInt(level));

        try {
            if (categoryService.addObj(category)) {
                resMessage = ResMessageBuilder.resMessage(200, "success", "添加成功");
            } else {
                resMessage = ResMessageBuilder.resMessage(200, "fail", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResMessageBuilder.resMessage(200, "fail", "服务器错误");
        }


        return resMessage;
    }

    public ResMessage update(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");

        try {
            Category obj = categoryService.getObjById(Integer.parseInt(id));
            obj.setTitle(title);
            if (categoryService.updateObj(obj)) {
                return ResMessageBuilder.resMessage(200, "success", "修改成功");
            } else {
                return ResMessageBuilder.resMessage(200, "fail", "修改失败");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResMessageBuilder.resMessage(200, "fail", "服务器错误");
        }
    }

    public ResMessage delete(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");

        try {
            if (categoryService.deleteObjById(Integer.parseInt(id))) {
                return ResMessageBuilder.resMessage(200, "success", "修改成功");
            } else {
                return ResMessageBuilder.resMessage(200, "fail", "修改失败");

            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResMessageBuilder.resMessage(200, "fail", "服务器错误");
        }


    }
}
