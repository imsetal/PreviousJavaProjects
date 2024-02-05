package org.example.crms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.crms.dao.UserDAO;
import org.example.crms.model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDAO userDAO=new UserDAO();
    public UserService(){}
    public String Login(String user,String password){
        try {
            User u=userDAO.select_byUser(user);
            if(u==null){
                return "不存在该用户";
            }else{
                if(u.getPassword().equals(password)){
                    return "success";
                }else{
                    return "密码错误";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Registered(String user,String password){
        try {
            if(user=="" || password=="")return "账号或密码不能为空";
            if(userDAO.select_byUser(user)==null) {
                User u = new User(user, password, "user");
                userDAO.add(u);
                return "success";
            }else{
                return "用户已存在";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String SelectUserGroup(String user){
        try {
            User u=userDAO.select_byUser(user);
            if(u==null){
                return "不存在该用户";
            }else{
                return u.getUser_group();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray Select(){
        try {
            List<User> users=userDAO.select();
            JSONArray jsonArray=new JSONArray();
            for(User u:users){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("user",u.getUser());
                jsonObject.put("password",u.getPassword());
                jsonObject.put("user_group",u.getUser_group());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String UpdateInfo(String user,String password,String user_group){
        try {
            userDAO.update(new User(user,password,user_group));
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Del(String user){
        try {
            userDAO.delete(user);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
