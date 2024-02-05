package com.example.yachtclub.controller;

import com.example.yachtclub.service.AccountManagement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accountServlet")
public class AccountServlet extends HttpServlet {
    public AccountManagement accountManagement=new AccountManagement();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action=request.getParameter("action");

        response.setCharacterEncoding("UTF-8");
        System.out.println(action);
        switch (action){
            case "select":{
                response.getWriter().print(accountManagement.select());
                break;
            }
            case "update":{
                String user=request.getParameter("user");
                String password=request.getParameter("password");
                String user_group=request.getParameter("user_group");
                String info=accountManagement.update(user,password,user_group);
                response.getWriter().print(info);
                break;
            }
            case "del":{
                String user=request.getParameter("user");
                String info = accountManagement.del_User(user);
                response.getWriter().print(info);
                break;
            }
        }
    }
}
