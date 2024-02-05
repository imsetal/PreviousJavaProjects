package com.example.yachtclub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yachtclub.dao.ReviewDAO;
import com.example.yachtclub.model.Review;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReviewManagement {
    ReviewDAO reviewDAO=new ReviewDAO();

    public JSONArray select(){
        try {
            List<Review> reviews=reviewDAO.select();
            Collections.reverse(reviews);
            JSONArray jsonArray=new JSONArray();
            for(Review r:reviews){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",r.getId());
                jsonObject.put("user",r.getUser());
                jsonObject.put("coob",r.getCoob());
                jsonObject.put("time",r.getTime());
                jsonObject.put("content",r.getContent());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String add(String user,int coob,String content){
        try {
            Review review=new Review(-1,user,coob,"NaN",content);
            reviewDAO.addReview(review);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String del(int id){
        try {
            reviewDAO.del_byId(id);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
