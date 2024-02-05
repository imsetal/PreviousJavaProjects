package org.example.crms.dao;

import org.example.crms.model.PC;
import org.example.crms.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PCDAO {
    Connection conn;

    public PCDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PC> selectAll() throws SQLException {
        List<PC> pcs = new ArrayList<>();
        String sql = "SELECT * FROM PC";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            PC pc = new PC(
                    rs.getInt("id"),
                    rs.getString("CPU"),
                    rs.getString("GPU"),
                    rs.getString("RAM"),
                    rs.getString("StorageDevice"),
                    rs.getString("MotherBoard"),
                    rs.getString("state")
            );
            pcs.add(pc);
        }
        return pcs;
    }

    public List<PC> select_ByString(String str) throws SQLException {
        List<PC> pcs = new ArrayList<>();
        String sql = "SELECT PC.* FROM PC,management where "+str+" GROUP BY PC.id;";
        System.out.println(sql);
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            PC pc = new PC(
                    rs.getInt("id"),
                    rs.getString("CPU"),
                    rs.getString("GPU"),
                    rs.getString("RAM"),
                    rs.getString("StorageDevice"),
                    rs.getString("MotherBoard"),
                    rs.getString("state")
            );
            pcs.add(pc);
        }
        return pcs;
    }

    public PC selectById(int id) throws SQLException {
        PC pc = null;
        String sql = "SELECT * FROM PC WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ResultSet rs = ptmt.executeQuery();
        if(rs.next()){
            pc = new PC(
                    rs.getInt("id"),
                    rs.getString("CPU"),
                    rs.getString("GPU"),
                    rs.getString("RAM"),
                    rs.getString("StorageDevice"),
                    rs.getString("MotherBoard"),
                    rs.getString("state")
            );
        }
        return pc;
    }

    public void add(PC pc) throws SQLException {
        String sql = "INSERT INTO PC(CPU, GPU, RAM, StorageDevice, MotherBoard, state) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, pc.getCPU());
        ptmt.setString(2, pc.getGPU());
        ptmt.setString(3, pc.getRAM());
        ptmt.setString(4, pc.getStorageDevice());
        ptmt.setString(5, pc.getMotherBoard());
        ptmt.setString(6, pc.getState());
        ptmt.execute();
    }

    public void update(PC pc) throws SQLException {
        String sql = "UPDATE PC SET CPU=?, GPU=?, RAM=?, StorageDevice=?, MotherBoard=?, state=? WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, pc.getCPU());
        ptmt.setString(2, pc.getGPU());
        ptmt.setString(3, pc.getRAM());
        ptmt.setString(4, pc.getStorageDevice());
        ptmt.setString(5, pc.getMotherBoard());
        ptmt.setString(6, pc.getState());
        ptmt.setInt(7, pc.getId());
        ptmt.execute();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PC WHERE id=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ptmt.execute();
    }
}
