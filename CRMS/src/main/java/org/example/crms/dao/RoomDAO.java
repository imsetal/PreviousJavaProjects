package org.example.crms.dao;

import org.example.crms.model.Room;
import org.example.crms.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    Connection conn;

    public RoomDAO(){
        DbUtil dbUtil = new DbUtil();
        try {
            conn = dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Room> selectAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            Room room = new Room(
                    rs.getString("room"),
                    rs.getInt("seat_num")
            );
            rooms.add(room);
        }
        return rooms;
    }

    public Room selectByRoomName(String roomName) throws SQLException {
        Room room = null;
        String sql = "SELECT * FROM room WHERE room=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, roomName);
        ResultSet rs = ptmt.executeQuery();
        if (rs.next()) {
            room = new Room(
                    rs.getString("room"),
                    rs.getInt("seat_num")
            );
        }
        return room;
    }

    public void add(Room room) throws SQLException {
        String sql = "INSERT INTO room(room, seat_num) VALUES (?, ?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, room.getRoom());
        ptmt.setInt(2, room.getSeat_num());
        ptmt.execute();
    }

    public void update(Room room) throws SQLException {
        String sql = "UPDATE room SET seat_num=? WHERE room=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, room.getSeat_num());
        ptmt.setString(2, room.getRoom());
        ptmt.execute();
    }

    public void delete(String roomName) throws SQLException {
        String sql = "DELETE FROM room WHERE room=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, roomName);
        ptmt.execute();
    }
}
