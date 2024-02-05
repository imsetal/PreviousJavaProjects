package org.example.crms.servlet;

import org.example.crms.service.RoomService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/roomServlet")
public class RoomServlet extends HttpServlet {
    RoomService roomService = new RoomService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        response.setCharacterEncoding("UTF-8");
        switch (action) {
            case "get_all_rooms": {
                response.getWriter().print(roomService.getAllRooms());
                break;
            }
            case "get_room_by_name": {
                String roomName = request.getParameter("roomName");
                response.getWriter().print(roomService.getRoomByName(roomName));
                break;
            }
            case "add_room": {
                String roomName = request.getParameter("roomName");
                int seatNum = Integer.parseInt(request.getParameter("seatNum"));
                String info = roomService.addRoom(roomName, seatNum);
                response.getWriter().print(info);
                break;
            }
            case "update_room": {
                String roomName = request.getParameter("roomName");
                int newSeatNum = Integer.parseInt(request.getParameter("newSeatNum"));
                String info = roomService.updateRoom(roomName, newSeatNum);
                response.getWriter().print(info);
                break;
            }
            case "delete_room": {
                String roomName = request.getParameter("roomName");
                String info = roomService.deleteRoom(roomName);
                response.getWriter().print(info);
                break;
            }
        }
    }
}
