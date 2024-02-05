package com.example.yachtclub.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yachtclub.dao.ConsumptionDAO;
import com.example.yachtclub.dao.MemberDAO;
import com.example.yachtclub.model.Member;

import java.sql.SQLException;
import java.util.List;

public class MemberManagement {
    private MemberDAO memberDAO=new MemberDAO();
    private ConsumptionDAO consumptionDAO=new ConsumptionDAO();

    public MemberManagement(){}

    public boolean MemberExist(String user){
        try {
            return memberDAO.select_byUser_exist(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String AddNewMember(String user,String name,String gender,String birthday,int level){
        try {
            if(!memberDAO.select_byUser_exist(user)){
                Member member=new Member(user,name,gender,birthday,level);
                memberDAO.add(member);
                return "success";
            }else{
                return "member existed";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray sclect_allMember(){
        try {
            JSONArray jsonArray=new JSONArray();
            List<Member> members=memberDAO.select();
            for(Member m:members){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("user",m.getUser());
                jsonObject.put("name",m.getName());
                jsonObject.put("gender",m.getGender());
                jsonObject.put("birthday",m.getBirthday());
                jsonObject.put("level",m.getLevel());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getName(String user){
        try {
            Member member=memberDAO.select_byUser(user);
            return member.getName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String del_MemberInfo(String user){
        try {
            if(consumptionDAO.select_exist_byUser(user)){
                return "Unable to delete, the user has already generated an order.";
            }
            if(memberDAO.select_byUser_exist(user)){
                memberDAO.del_byUser(user);
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update_MemberInfo(String user,String name,String gender,String birthday,int level){
        try {
            if(memberDAO.select_byUser_exist(user)){
                memberDAO.update(new Member(user,name,gender,birthday,level));
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String update_MemberInfo_noRoot(String user,String name,String gender,String birthday,int level){
        try {
            if(memberDAO.select_byUser_exist(user)){
                memberDAO.update(new Member(user,name,gender,birthday,level));
                return "success";
            }else{
                return "The user does not exist.";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
