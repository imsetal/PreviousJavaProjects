package cn.yzw.device.web.servlet;

import cn.yzw.device.tool.CheckCodeUtil;
import cn.yzw.device.tool.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckCodeServlet", urlPatterns = "/checkCode")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String checkCode = Strings.getString(4);

        request.getSession().setAttribute("CHECK_CODE",checkCode);

        CheckCodeUtil.getCode(response.getOutputStream(),checkCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
