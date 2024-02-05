package org.example.crms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.crms.dao.ManagementDAO;
import org.example.crms.dao.RoomDAO;
import org.example.crms.model.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    RoomDAO roomDAO = new RoomDAO();
    ManagementDAO managementDAO=new ManagementDAO();

    public RoomService() {
    }

    public JSONArray getAllRooms() {
        try {
            List<Room> rooms = roomDAO.selectAll();
            JSONArray jsonArray = new JSONArray();
            for (Room room : rooms) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("room", room.getRoom());
                jsonObject.put("seat_num", room.getSeat_num());
                jsonObject.put("pc_num",managementDAO.selectByRoom(room.getRoom()).size());
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getRoomByName(String roomName) {
        try {
            Room room = roomDAO.selectByRoomName(roomName);
            if (room != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("room", room.getRoom());
                jsonObject.put("seat_num", room.getSeat_num());
                return jsonObject;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String addRoom(String roomName, int seatNum) {
        try {
            Room room = new Room(roomName, seatNum);
            roomDAO.add(room);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String updateRoom(String roomName, int newSeatNum) {
        try {
            int num=managementDAO.selectByRoom(roomName).size();
            if(newSeatNum<num)return "这个数字小于该房间现有电脑数";
            Room room = new Room(roomName, newSeatNum);
            roomDAO.update(room);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteRoom(String roomName) {
        try {
            roomDAO.delete(roomName);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
