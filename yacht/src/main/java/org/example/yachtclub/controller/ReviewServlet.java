package com.example.yachtclub.controller;

import com.example.yachtclub.model.User;
import com.example.yachtclub.service.ReviewManagement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reviewServlet")
public class ReviewServlet extends HttpServlet {
    ReviewManagement reviewManagement=new ReviewManagement();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action=request.getParameter("action");

        response.setCharacterEncoding("UTF-8");
        System.out.println(action);
        switch (action){
            case "select":{
                response.getWriter().print(reviewManagement.select());
                break;
            }
            case "review":{
                String user=request.getParameter("user");
                if(user.equals(((User)request.getSession().getAttribute("user")).user)){
                    String info=reviewManagement.add(user,-1,request.getParameter("content"));
                    response.getWriter().print(info);
                }else{
                    response.getWriter().print("fail");
                }
                break;
            }
            case "del":{
                String user=request.getParameter("user");
                if(user.equals(((User)request.getSession().getAttribute("user")).user)){
                    String info=reviewManagement.del(Integer.parseInt(request.getParameter("id")));
                    response.getWriter().print(info);
                }else{
                    response.getWriter().print("fail");
                }
            }
        }
    }
}
