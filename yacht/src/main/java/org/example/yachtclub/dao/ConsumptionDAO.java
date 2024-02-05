package com.example.yachtclub.dao;

import com.example.yachtclub.model.Consumption;
import com.example.yachtclub.model.Yacht;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionDAO {
    private Connection conn=null;
    public ConsumptionDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consumption> select() throws SQLException {
        List<Consumption> consumptions=new ArrayList<>();
        String sql = "select * from consumption";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Consumption consumption=new Consumption();
            consumption.setOrder_num(rs.getInt("order_num"));
            consumption.setUser(rs.getString("user"));
            consumption.setId(rs.getInt("id"));
            consumption.setType(rs.getString("type"));
            consumption.setTime(rs.getString("time"));
            consumption.setSum(rs.getFloat("sum"));
            consumption.setState(rs.getString("state"));
            consumptions.add(consumption);
        }
        return consumptions;
    }

    public List<Consumption> select_byString(String str) throws SQLException {
        List<Consumption> consumptions=new ArrayList<>();
        String sql = "select * from consumption where "+str;
        System.out.println(sql);
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Consumption consumption=new Consumption();
            consumption.setOrder_num(rs.getInt("order_num"));
            consumption.setUser(rs.getString("user"));
            consumption.setId(rs.getInt("id"));
            consumption.setType(rs.getString("type"));
            consumption.setTime(rs.getString("time"));
            consumption.setSum(rs.getFloat("sum"));
            consumption.setState(rs.getString("state"));
            consumptions.add(consumption);
        }
        return consumptions;
    }

    public List<Consumption> select_byUser(String user) throws SQLException {
        List<Consumption> consumptions=new ArrayList<>();
        String sql = "select * from consumption where user='"+user+"'";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Consumption consumption=new Consumption();
            consumption.setOrder_num(rs.getInt("order_num"));
            consumption.setUser(rs.getString("user"));
            consumption.setId(rs.getInt("id"));
            consumption.setType(rs.getString("type"));
            consumption.setTime(rs.getString("time"));
            consumption.setSum(rs.getFloat("sum"));
            consumption.setState(rs.getString("state"));
            consumptions.add(consumption);
        }
        return consumptions;
    }

    public Consumption select_byOrderNum(int order_num) throws SQLException {
        Consumption consumption=new Consumption();
        String sql = "select * from consumption where order_num="+order_num;
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            consumption.setOrder_num(rs.getInt("order_num"));
            consumption.setUser(rs.getString("user"));
            consumption.setId(rs.getInt("id"));
            consumption.setType(rs.getString("type"));
            consumption.setTime(rs.getString("time"));
            consumption.setSum(rs.getFloat("sum"));
            consumption.setState(rs.getString("state"));
        }
        return consumption;
    }

    public void add(Consumption consumption) throws SQLException {
        String sql = "INSERT INTO consumption(user,id,type,time,sum,state) values('"+ consumption.getUser()+"',"+consumption.getId()+",'"+consumption.getType()+"',now(),"+consumption.getSum()+",'"+consumption.getState()+"')";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.execute();
    }

    public boolean select_exist(int order_num) throws SQLException {
        String sql = "select * from consumption where order_num="+order_num+";";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
        }
        if(i==0)return false;
        else return true;
    }

    public boolean select_exist_byUser(String user) throws SQLException {
        String sql = "select * from consumption where user='"+user+"';";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
        }
        if(i==0)return false;
        else return true;
    }

    public int time(int order_num) throws SQLException {
        String sql="select timestampdiff(day,(select time from yachtclub.consumption where order_num="+order_num+"),now()) as 'time';";
        PreparedStatement ptmt= conn.prepareStatement(sql);
        ResultSet rs= ptmt.executeQuery();
        int time=0;
        while(rs.next()){
            time=rs.getInt("time");
        }
        return time;
    }

    public void update(Consumption consumption) throws SQLException {
        String sql="UPDATE consumption set user='"+consumption.getUser()+"',id="+consumption.getId()+",type='"+consumption.getType()+"',time='"+consumption.getTime()+"',sum="+consumption.getSum()+",state='"+consumption.getState()+"' where order_num="+consumption.getOrder_num();
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }
}
