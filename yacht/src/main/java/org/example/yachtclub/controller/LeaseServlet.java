package com.example.yachtclub.controller;

import com.example.yachtclub.model.User;
import com.example.yachtclub.service.Lease;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/leaseServlet")
public class LeaseServlet extends HttpServlet {
    Lease lease=new Lease();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action=request.getParameter("action");

        response.setCharacterEncoding("UTF-8");
        System.out.println(action);
        switch (action){
            case "select_yacht":{
                response.getWriter().print(lease.select_Yacht());
                break;
            }
            case "rent":{
                String user=((User)request.getSession().getAttribute("user")).getUser();
                int id= Integer.parseInt(request.getParameter("id"));
                String info=lease.Rent(user,id);
                response.getWriter().print(info);
                break;
            }
            case "select_consumption_byUser":{
                response.getWriter().print(lease.select_Consumption_byUser(((User)request.getSession().getAttribute("user")).getUser()));
                break;
            }
            case "select_consumption_byOrderNum":{
                String order_num=request.getParameter("order_num");
                if(!order_num.isEmpty()) {
                    int id = Integer.parseInt(order_num);
                    System.out.println(id);
                    response.getWriter().print(lease.select_Consumption_byOrderNum(id));
                }
                else{
                    response.getWriter().print(lease.select_Consumption());
                }
                break;
            }
            case "select_consumption":{
                response.getWriter().print(lease.select_Consumption());
                break;
            }
            case "select_Consumption_byString":{
                String user=request.getParameter("user");
                String id=request.getParameter("id");
                String date=request.getParameter("date");
                String price=request.getParameter("price");
                String state=request.getParameter("state");
                response.getWriter().print(lease.select_Consumption_byString(user,id,date,price,state));
                break;
            }
            case "give_back":{
                int order_num= Integer.parseInt(request.getParameter("order_num"));
                String info=lease.GiveBack(order_num);
                response.getWriter().print(info);
                break;
            }
            case "pay":{
                int order_num= Integer.parseInt(request.getParameter("order_num"));
                String info=lease.Pay(order_num);
                response.getWriter().print(info);
                break;
            }
        }
    }
}
