package org.example.crms.servlet;

import org.example.crms.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService=new UserService();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action=request.getParameter("action");
        System.out.println(action);
        response.setCharacterEncoding("UTF-8");
        switch (action){
            case "login": {
                String user = request.getParameter("user");
                String password = request.getParameter("password");
                String info = userService.Login(user, password);
                if(info.equals("success")){
                    request.getSession().setAttribute("user",user);
                }
                response.getWriter().print(info);
                break;
            }
            case "singup": {
                String user = request.getParameter("user");
                String password = request.getParameter("password");
                String info = userService.Registered(user, password);
                if(info.equals("success")){
                    request.getSession().setAttribute("user",user);
                }
                response.getWriter().print(info);
                break;
            }
            case "get_user":{
                response.getWriter().print(request.getSession().getAttribute("user"));
                break;
            }
            case "get_user_group":{
                String info= userService.SelectUserGroup((String) request.getSession().getAttribute("user"));
                response.getWriter().print(info);
                break;
            }
            case "select":{
                response.getWriter().print(userService.Select());
                break;
            }
            case "update":{
                String user = request.getParameter("user");
                String password = request.getParameter("password");
                String user_group=request.getParameter("user_group");
                String info=userService.UpdateInfo(user,password,user_group);
                response.getWriter().print(info);
                break;
            }
            case "del":{
                String user = request.getParameter("user");
                String info=userService.Del(user);
                response.getWriter().print(info);
                break;
            }
        }
    }
}
