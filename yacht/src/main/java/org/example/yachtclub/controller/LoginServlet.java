package com.example.yachtclub.controller;

import com.example.yachtclub.model.User;
import com.example.yachtclub.service.Login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    Login loginService=new Login();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action=request.getParameter("action");

        response.setCharacterEncoding("UTF-8");
        System.out.println(action);
        switch (action){
            case "login":{
                String user=request.getParameter("user");
                String password=request.getParameter("password");
                String info=loginService.Login(user,password);
                if(info.equals("success")) {
                    User user1 = loginService.getUser(user);
                    request.getSession().setAttribute("user", user1);
                    if (user1.getUser_group().equals("root")) info += " root";
                    if (user1.getUser_group().equals("staff")) info += " staff";
                    response.getWriter().print(info);
                }else{
                    response.getWriter().print(info);
                }
                break;
            }
            case "registered":{
                String user=request.getParameter("user");
                String password=request.getParameter("password");
                String info=loginService.Registered(user,password);
                User user1=loginService.getUser(user);
                request.getSession().setAttribute("user",user1);
                response.getWriter().print(info);
                break;
            }
            case "getUser":{
                response.getWriter().print(((User)request.getSession().getAttribute("user")).getUser());
                break;
            }
        }
    }
}
