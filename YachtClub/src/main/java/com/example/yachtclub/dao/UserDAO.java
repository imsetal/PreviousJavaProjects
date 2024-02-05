package com.example.yachtclub.dao;

import com.example.yachtclub.model.User;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn=null;

    public UserDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User select_byUser(String user_string) throws SQLException {
        User user=new User();
        String sql = "select * from user where user='"+user_string+"';";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            user.setUser(rs.getString("user"));
            user.setPassword(rs.getString("password"));
            user.setUser_group(rs.getString("user_group"));
        }
        return user;
    }

    public boolean select_byUser_exist(String user_string) throws SQLException {
        String sql = "select * from user where user='"+user_string+"';";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
        }
        if(i==0)return false;
        else return true;
    }

    public List<User> select() throws SQLException {
        List<User> userList=new ArrayList<>();
        String sql = "select * from user";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            User u=new User();
            u.setUser(rs.getString("user"));
            u.setPassword(rs.getString("password"));
            u.setUser_group(rs.getString("user_group"));
            userList.add(u);
        }
        return userList;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user(user,password,user_group) values('"+user.getUser()+"','"+user.getPassword()+"','"+user.getUser_group()+"')";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.execute();
    }

    public void update(User uesr) throws SQLException {
        String sql="UPDATE user set user='"+uesr.user+"',password='"+uesr.password+"',user_group='"+uesr.user_group+"' where user='"+uesr.user+"'";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }

    public void del_byUser(String user) throws SQLException {
        String sql="delete from user where user='"+user+"'";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }
}
