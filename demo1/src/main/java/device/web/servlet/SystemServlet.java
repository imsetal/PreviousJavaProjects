package cn.yzw.device.web.servlet;

import cn.yzw.device.service.UserService;
import cn.yzw.device.service.impl.UserServiceImpl;
import cn.yzw.device.tool.ResMessageBuilder;
import cn.yzw.device.vo.ResMessage;
import cn.yzw.device.vo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SystemServlet",urlPatterns = "/system/*")
public class SystemServlet extends BaseServlet {

    private UserService userService;

    public ResMessage login(HttpServletRequest request, HttpServletResponse response){


        userService = new UserServiceImpl();

        String check_code = (String) request.getSession().getAttribute("CHECK_CODE");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");

        if (check_code.equalsIgnoreCase(captcha)){

            return userService.userLogin(request,account,password);

        }else {
           return ResMessageBuilder.resMessage(200,"fail","验证码错误！");
        }

    }

    public String exit(HttpServletRequest request,HttpServletResponse response) throws IOException {

        request.getSession().removeAttribute("USER");

        return "r:/page/login.jsp";
    }

    public String categoryList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/category/categoryList.jsp";
    }

    public String addDevice(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/addDevice.jsp";
    }

    public String deviceList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/deviceList.jsp";
    }

    public String downDevice(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/downDevice.jsp";
    }


    public String downList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/downList.jsp";
    }

    public String fixDevice(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/fixDevice.jsp";
    }

    public String fixList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/fixList.jsp";
    }
    public String fixInfoList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/device/fixInfoList.jsp";
    }

    public String addApply(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/addApply.jsp";
    }

    public String applyList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/applyList.jsp";
    }

    public String editApply(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/editApply.jsp";
    }

    public String openApply(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/openApply.jsp";
    }

    public String checkApply(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/checkApply.jsp";
    }

    public String checkList(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/apply/checkList.jsp";
    }

    public String console(HttpServletRequest request,HttpServletResponse response){
        return "r:/page/console.jsp";
    }




}
