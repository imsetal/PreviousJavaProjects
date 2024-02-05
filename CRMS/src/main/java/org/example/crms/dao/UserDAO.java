package org.example.crms.dao;

import org.example.crms.model.User;
import org.example.crms.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    Connection conn;

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

    public List<User> select() throws SQLException {
        List<User> users=new ArrayList<>();
        String sql="select * from user";
        PreparedStatement ptmt= conn.prepareStatement(sql);
        ResultSet rs= ptmt.executeQuery();
        while(rs.next()){
            User user=new User(
                    rs.getString("user"),
                    rs.getString("password"),
                    rs.getString("user_group")
            );
            users.add(user);
        }
        return users;
    }

    public User select_byUser(String user) throws SQLException {
        User u;
        String sql="select * from user where user=?";
        PreparedStatement ptmt= conn.prepareStatement(sql);
        ptmt.setString(1,user);
        ResultSet rs= ptmt.executeQuery();
        while(rs.next()){
            u=new User(
                    rs.getString("user"),
                    rs.getString("password"),
                    rs.getString("user_group")
            );
            return u;
        }
        return null;
    }

    public void add(User u) throws SQLException {
        String sql="INSERT INTO user(user,password,user_group) values(?,?,?)";
        PreparedStatement ptmt= conn.prepareStatement(sql);
        ptmt.setString(1,u.getUser());
        ptmt.setString(2,u.getPassword());
        ptmt.setString(3,u.getUser_group());
        ptmt.execute();
    }

    public void update(User u) throws SQLException {
        String sql="update user set password=?,user_group=? where user=?";
        PreparedStatement ptmt= conn.prepareStatement(sql);
        ptmt.setString(1,u.getPassword());
        ptmt.setString(2,u.getUser_group());
        ptmt.setString(3,u.getUser());
        ptmt.execute();
    }

    public void delete(String user) throws SQLException {
        String sql = "DELETE FROM user WHERE user=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, user);
        ptmt.execute();
    }

}
