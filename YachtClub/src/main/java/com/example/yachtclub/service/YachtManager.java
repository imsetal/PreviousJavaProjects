package com.example.yachtclub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yachtclub.dao.YachtColorDAO;
import com.example.yachtclub.dao.YachtDAO;
import com.example.yachtclub.model.Yacht;

import java.sql.SQLException;
import java.util.List;

public class YachtManager {
    YachtDAO yachtDAO=new YachtDAO();
    YachtColorDAO yachtColorDAO=new YachtColorDAO();

    public YachtManager(){}

    public JSONArray getColor(){
        try {
            List<String> colors=yachtColorDAO.select();
            JSONArray jsonArray=new JSONArray();
            for(String s:colors){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("color",s);
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean YachtExist(int id){
        try {
            return yachtDAO.select_byId_exist(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String AddNewYacht(String name,String color,int daily_price){
        try {
            Yacht yacht=new Yacht(name,color,"idle",daily_price);
            yachtDAO.addYacht(yacht);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray sclect_allYacht(){
        try {
            JSONArray jsonArray=new JSONArray();
            List<Yacht> yachtList=yachtDAO.select();
            for(Yacht yacht:yachtList){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",yacht.getId());
                jsonObject.put("name",yacht.getName());
                jsonObject.put("color",yacht.getColor());
                jsonObject.put("state",yacht.getState());
                jsonObject.put("daily_price",yacht.getDaily_price());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String del_Yacht(int id){
        try {
            if(yachtDAO.select_byId_exist(id)){
                yachtDAO.del_byId(id);
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update_Yacht(int id,String name,String color,String state,int daily_price){
        try {
            if(yachtDAO.select_byId_exist(id)){
                yachtDAO.update(new Yacht(id,name,color,state,daily_price));
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
