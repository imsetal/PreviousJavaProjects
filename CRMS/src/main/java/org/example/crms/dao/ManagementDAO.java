package org.example.crms.dao;

import org.example.crms.model.Management;
import org.example.crms.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagementDAO {
    private Connection conn;

    public ManagementDAO() {
        DbUtil dbUtil = new DbUtil();
        try {
            conn = dbUtil.getConn();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Management> selectAll() throws SQLException {
        List<Management> managementList = new ArrayList<>();
        String sql = "SELECT * FROM management";
        try (PreparedStatement ptmt = conn.prepareStatement(sql);
             ResultSet rs = ptmt.executeQuery()) {

            while (rs.next()) {
                int pcId = rs.getInt("pc_id");
                String room = rs.getString("room");

                Management management = new Management(pcId, room);
                managementList.add(management);
            }
        }
        return managementList;
    }

    public Management selectByPcId(int pcId) throws SQLException {
        Management management = null;
        String sql = "SELECT * FROM management WHERE pc_id=?";
        try (PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setInt(1, pcId);

            try (ResultSet rs = ptmt.executeQuery()) {
                if (rs.next()) {
                    String room = rs.getString("room");
                    management = new Management(pcId, room);
                }
            }
        }
        return management;
    }

    public List<Management> selectByRoom(String room) throws SQLException {
        List<Management> managementList = new ArrayList<>();
        String sql = "SELECT * FROM management WHERE room=?";
        try (PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setString(1, room);

            try (ResultSet rs = ptmt.executeQuery()) {
                while (rs.next()) {
                    int pcId = rs.getInt("pc_id");
                    Management management = new Management(pcId, room);
                    managementList.add(management);
                }
            }
        }
        return managementList;
    }

    public void add(Management management) throws SQLException {
        String sql = "INSERT INTO management(pc_id, room) VALUES (?, ?)";
        try (PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setInt(1, management.getPcId());
            ptmt.setString(2, management.getRoom());
            ptmt.execute();
        }
    }

    public void update(Management management) throws SQLException {
        String sql = "UPDATE management SET room=? WHERE pc_id=?";
        try (PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setString(1, management.getRoom());
            ptmt.setInt(2, management.getPcId());
            ptmt.execute();
        }
    }

    public void delete(int pcId) throws SQLException {
        String sql = "DELETE FROM management WHERE pc_id=?";
        try (PreparedStatement ptmt = conn.prepareStatement(sql)) {
            ptmt.setInt(1, pcId);
            ptmt.execute();
        }
    }
}
