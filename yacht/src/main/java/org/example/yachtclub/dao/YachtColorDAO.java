package com.example.yachtclub.dao;

import com.example.yachtclub.model.Yacht;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YachtColorDAO {
    private Connection conn=null;

    public YachtColorDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> select() throws SQLException {
        List<String> colors=new ArrayList<>();
        String sql = "select * from yacht_color";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            colors.add(rs.getString("color"));
        }
        return colors;
    }
}
