package com.example.yachtclub.controller;

import com.example.yachtclub.model.User;
import com.example.yachtclub.service.YachtManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/yachtServlet")
public class YachtServlet extends HttpServlet {
    YachtManager yachtManager=new YachtManager();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");

        System.out.println(action);
        switch (action){
            case "isExisted":{
                if(yachtManager.YachtExist(Integer.parseInt(request.getParameter("id")))){
                    response.getWriter().print("true");
                }else{
                    response.getWriter().print("false");
                }
                break;
            }
            case "add":{
                String name=request.getParameter("name");
                String color=request.getParameter("color");
                int daily_price= Integer.parseInt(request.getParameter("daily_price"));
                String info = yachtManager.AddNewYacht(name,color,daily_price);
                response.getWriter().print(info);
                break;
            }
            case "select":{
                response.getWriter().print(yachtManager.sclect_allYacht());
                break;
            }
            case "del":{
                int id= Integer.parseInt(request.getParameter("id"));
                String info = yachtManager.del_Yacht(id);
                response.getWriter().print(info);
                break;
            }
            case "update":{
                int id= Integer.parseInt(request.getParameter("id"));
                String name=request.getParameter("name");
                String color=request.getParameter("color");
                String state=request.getParameter("state");
                int daily_price= Integer.parseInt(request.getParameter("daily_price"));
                String info=yachtManager.update_Yacht(id,name,color,state,daily_price);
                response.getWriter().print(info);
                break;
            }
            case "get_colors":{
                response.getWriter().print(yachtManager.getColor());
                break;
            }
        }
    }
}
