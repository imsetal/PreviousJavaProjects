package com.example.yachtclub.controller;

import com.example.yachtclub.model.User;
import com.example.yachtclub.service.MemberManagement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/memberServlet")
public class MemberServlet extends HttpServlet {
    MemberManagement memberService=new MemberManagement();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");

        System.out.println(action);
        switch (action){
            case "isExisted":{
                if(memberService.MemberExist(request.getParameter("user"))){
                    response.getWriter().print("true");
                }else{
                    response.getWriter().print("false");
                }
                break;
            }
            case "add":{
                User u= (User) request.getSession().getAttribute("user");
                String user=u.getUser();
                String name=request.getParameter("name");
                String gender=request.getParameter("gender");
                String birthday=request.getParameter("birthday");
                if(!user.isEmpty()) {
                    String info = memberService.AddNewMember(user, name, gender, birthday, 1);
                    response.getWriter().print(info);
                }
                break;
            }
            case "select":{
                response.getWriter().print(memberService.sclect_allMember());
                break;
            }
            case "del":{
                String user=request.getParameter("user");
                String info = memberService.del_MemberInfo(user);
                response.getWriter().print(info);
                break;
            }
            case "update":{
                String user=request.getParameter("user");
                String name=request.getParameter("name");
                System.out.println(name);
                String gender=request.getParameter("gender");
                String birthday=request.getParameter("birthday");
                int level= Integer.parseInt(request.getParameter("level"));
                String info=memberService.update_MemberInfo(user,name,gender,birthday,level);
                response.getWriter().print(info);
                break;
            }
            case "getName":{
                response.getWriter().print(memberService.getName(((User) request.getSession().getAttribute("user")).getUser()));
                break;
            }
        }
    }
}
