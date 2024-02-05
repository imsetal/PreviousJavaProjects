package com.example.yachtclub.service;

import com.example.yachtclub.dao.UserDAO;
import com.example.yachtclub.model.User;

import java.sql.SQLException;
import java.util.List;

public class Login {
    UserDAO userDAO=new UserDAO();

    public String Login(String user,String password){
        try {
            if(!userDAO.select_byUser_exist(user)){
                return "The user does not exist.";
            }else{
                User user1=userDAO.select_byUser(user);
                if(user1.getPassword().equals(password)){
                    return "success";
                }else{
                    return "The username or password is incorrect.";
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Registered(String user,String password){
        try {
            if(!userDAO.select_byUser_exist(user)){
                User new_user=new User(user,password,"user");
                userDAO.addUser(new_user);
                return "success";
            }else{
                return "User already exists";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String name){
        try {
            User user=userDAO.select_byUser(name);
            user.setPassword(null);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
