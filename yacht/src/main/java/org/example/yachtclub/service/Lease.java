package com.example.yachtclub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yachtclub.dao.ConsumptionDAO;
import com.example.yachtclub.dao.YachtDAO;
import com.example.yachtclub.model.Consumption;
import com.example.yachtclub.model.Yacht;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lease {
    YachtDAO yachtDAO=new YachtDAO();
    ConsumptionDAO consumptionDAO=new ConsumptionDAO();

    public Lease(){}

    public JSONArray select_Yacht(){
        try {
            List<Yacht> yachtList=yachtDAO.select();
            JSONArray jsonArray=new JSONArray();
            for(Yacht yacht:yachtList){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("id",yacht.getId());
                jsonObject.put("name",yacht.getName());
                jsonObject.put("color",yacht.getColor());
                switch (yacht.getState()){
                    case "idle":{
                        jsonObject.put("state","空闲中");
                        break;
                    }
                    case "lease":{
                        jsonObject.put("state","已借出");
                        break;
                    }
                    case "haveSold":{
                        jsonObject.put("state","已卖出");
                        break;
                    }
                    default:{
                        jsonObject.put("state","未知");
                    }
                }
                jsonObject.put("daily_price",yacht.getDaily_price());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray select_Consumption_byUser(String user){
        try {
            List<Consumption> consumptions=consumptionDAO.select_byUser(user);
            Collections.reverse(consumptions);
            JSONArray jsonArray=new JSONArray();
            for(Consumption c:consumptions){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("order_num",c.getOrder_num());
                jsonObject.put("user",c.getUser());
                jsonObject.put("id",c.getId());
                jsonObject.put("type",c.getType());
                jsonObject.put("time",c.getTime());
                jsonObject.put("sum",c.getSum());
                jsonObject.put("state",c.getState());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray select_Consumption(){
        try {
            List<Consumption> consumptions=consumptionDAO.select();
            Collections.reverse(consumptions);
            JSONArray jsonArray=new JSONArray();
            for(Consumption c:consumptions){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("order_num",c.getOrder_num());
                jsonObject.put("user",c.getUser());
                jsonObject.put("id",c.getId());
                jsonObject.put("type",c.getType());
                jsonObject.put("time",c.getTime());
                jsonObject.put("sum",c.getSum());
                jsonObject.put("state",c.getState());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray select_Consumption_byString(String user,String id,String date,String price,String state){
        try {
            List<Consumption> consumptions = null;
            System.out.println(user.isEmpty()&&id.isEmpty()&&date.isEmpty()&&price.isEmpty()&&state.equals("null"));
            if(user.isEmpty()&&id.isEmpty()&&date.isEmpty()&&price.isEmpty()&&state.equals("null")){
                consumptions=consumptionDAO.select();
            }else{
                String str="";
                if(!user.isEmpty()){
                    if(!str.equals("")){
                        str+=" and ";
                    }
                    str+="user like '%"+user+"%'";
                }
                if(!id.isEmpty()){
                    if(!str.equals("")){
                        str+=" and ";
                    }
                    str+="id="+id;
                }
                if(!date.isEmpty()){
                    if(!str.equals("")){
                        str+=" and ";
                    }
                    str+="time>='"+date+"'";
                }
                if(!price.isEmpty()){
                    if(!str.equals("")){
                        str+=" and ";
                    }
                    str+="sum>="+price;
                }
                if(!state.equals("null")){
                    if(!str.equals("")){
                        str+=" and ";
                    }
                    str+="state='"+state+"'";
                }
                consumptions=consumptionDAO.select_byString(str);
            }
            JSONArray jsonArray=new JSONArray();
            for (Consumption c : consumptions) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("order_num", c.getOrder_num());
                jsonObject.put("user", c.getUser());
                jsonObject.put("id", c.getId());
                jsonObject.put("type", c.getType());
                jsonObject.put("time", c.getTime());
                jsonObject.put("sum", c.getSum());
                jsonObject.put("state", c.getState());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray select_Consumption_byOrderNum(int id){
        try {
            if(consumptionDAO.select_exist(id)) {
                Consumption c = consumptionDAO.select_byOrderNum(id);
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("order_num", c.getOrder_num());
                jsonObject.put("user", c.getUser());
                jsonObject.put("id", c.getId());
                jsonObject.put("type", c.getType());
                jsonObject.put("time", c.getTime());
                jsonObject.put("sum", c.getSum());
                jsonObject.put("state", c.getState());
                jsonArray.add(jsonObject);
                return jsonArray;
            }else{
                return new JSONArray();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Rent(String user,int id){
        try {
            Yacht yacht=yachtDAO.select_byId(id);
            if(yacht.getState().equals("idle")){
                yacht.setState("lease");
                yachtDAO.update(yacht);
                consumptionDAO.add(new Consumption(0,user,yacht.getId(),"lease",0,"not_billed"));
                return "success";
            }else{
                return "It's not idle";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String GiveBack(int order_num){
        try {
            if(consumptionDAO.select_exist(order_num)){
                Consumption consumption=consumptionDAO.select_byOrderNum(order_num);
                Yacht yacht=yachtDAO.select_byId(consumption.getId());
                int time=consumptionDAO.time(order_num)+1;
                consumption.setSum(time*yacht.getDaily_price());
                consumption.setState("unpaid");
                consumptionDAO.update(consumption);
                yacht.setState("idle");
                yachtDAO.update(yacht);
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String Pay(int order_num){
        try {
            if(consumptionDAO.select_exist(order_num)){
                Consumption consumption=consumptionDAO.select_byOrderNum(order_num);
                consumption.setState("paid");
                consumptionDAO.update(consumption);
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
