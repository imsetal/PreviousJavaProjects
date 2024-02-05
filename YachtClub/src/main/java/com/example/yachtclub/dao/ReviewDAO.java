package com.example.yachtclub.dao;

import com.example.yachtclub.model.Review;
import com.example.yachtclub.model.User;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private Connection conn=null;

    public ReviewDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Review> select() throws SQLException {
        List<Review> reviewList=new ArrayList<>();
        String sql = "select * from review";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Review review=new Review(
                    rs.getInt("id"),
                    rs.getString("user"),
                    rs.getInt("coob"),
                    rs.getString("time"),
                    rs.getString("content")
            );
            reviewList.add(review);
        }
        return reviewList;
    }

    public void addReview(Review review) throws SQLException {
        String sql = "INSERT INTO review(user,coob,time,content) values(?,?,now(),?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1,review.getUser());
        ptmt.setInt(2,review.getCoob());
        ptmt.setString(3,review.getContent());
        ptmt.execute();
    }

    public void del_byId(int id) throws SQLException {
        String sql="delete from review where id="+id;
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }
}
