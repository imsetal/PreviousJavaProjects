package com.example.yachtclub.dao;

import com.example.yachtclub.model.Member;
import com.example.yachtclub.model.User;
import com.example.yachtclub.model.Yacht;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YachtDAO {
    private Connection conn=null;

    public YachtDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Yacht select_byId(int id) throws SQLException {
        Yacht yacht=new Yacht();
        String sql = "select * from yacht where id="+id+";";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            yacht.setId(rs.getInt("id"));
            yacht.setName(rs.getString("name"));
            yacht.setColor(rs.getString("color"));
            yacht.setState(rs.getString("state"));
            yacht.setDaily_price(rs.getInt("daily_price"));
        }
        return yacht;
    }

    public boolean select_byId_exist(int id) throws SQLException {
        String sql = "select * from yacht where id="+id+";";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
        }
        if(i==0)return false;
        else return true;
    }

    public List<Yacht> select() throws SQLException {
        List<Yacht> yachtList=new ArrayList<>();
        String sql = "select * from yacht";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Yacht yacht=new Yacht();
            yacht.setId(rs.getInt("id"));
            yacht.setName(rs.getString("name"));
            yacht.setColor(rs.getString("color"));
            yacht.setState(rs.getString("state"));
            yacht.setDaily_price(rs.getInt("daily_price"));
            yachtList.add(yacht);
        }
        return yachtList;
    }

    public void addYacht(Yacht yacht) throws SQLException {
        String sql = "INSERT INTO yacht(name,color,state,daily_price) values('"+yacht.getName()+"','"+yacht.getColor()+"','"+yacht.getState()+"',"+yacht.getDaily_price()+")";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.execute();
    }

    public void del_byId(int id) throws SQLException {
        String sql="delete from yacht where id="+id;
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }

    public void update(Yacht yacht) throws SQLException {
        String sql="UPDATE yacht set name='"+yacht.getName()+"',color='"+yacht.getColor()+"',state='"+yacht.getState()+"',daily_price="+yacht.getDaily_price()+" where id="+yacht.getId();
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }
}
