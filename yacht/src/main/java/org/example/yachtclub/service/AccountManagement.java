package com.example.yachtclub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yachtclub.dao.ConsumptionDAO;
import com.example.yachtclub.dao.MemberDAO;
import com.example.yachtclub.dao.UserDAO;
import com.example.yachtclub.model.Member;
import com.example.yachtclub.model.User;

import java.sql.SQLException;
import java.util.List;

public class AccountManagement {
    UserDAO userDAO=new UserDAO();
    MemberDAO memberDAO=new MemberDAO();
    ConsumptionDAO consumptionDAO=new ConsumptionDAO();

    public AccountManagement(){}

    public JSONArray select(){
        try {
            List<User> users=userDAO.select();
            JSONArray jsonArray=new JSONArray();
            for(User u:users){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("user",u.user);
                jsonObject.put("password",u.password);
                jsonObject.put("user_group",u.user_group);
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update(String user,String password,String user_group){
        try {
            if(userDAO.select_byUser_exist(user)){
                userDAO.update(new User(user,password,user_group));
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String del_User(String user) {
        try {
            if(consumptionDAO.select_exist_byUser(user)){
                return "Unable to delete, the user has already generated an order.";
            }
            if(memberDAO.select_byUser_exist(user)){
                memberDAO.del_byUser(user);
            }
            if(userDAO.select_byUser_exist(user)){
                userDAO.del_byUser(user);
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
