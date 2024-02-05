package org.example.crms.servlet;

import org.example.crms.service.PCService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pcServlet")
public class PCServlet extends HttpServlet {
    private final PCService pcService = new PCService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        response.setCharacterEncoding("UTF-8");
        switch (action) {
            case "get_all_pcs": {
                response.getWriter().print(pcService.getAllPCs());
                break;
            }
            case "get_pc_by_id": {
                int id = Integer.parseInt(request.getParameter("id"));
                response.getWriter().print(pcService.getPCById(id));
                break;
            }
            case "add_pc": {
                String CPU = request.getParameter("CPU");
                String GPU = request.getParameter("GPU");
                String RAM = request.getParameter("RAM");
                String storageDevice = request.getParameter("storageDevice");
                String motherBoard = request.getParameter("motherBoard");
                String info = pcService.addPC(CPU, GPU, RAM, storageDevice, motherBoard, "闲置中");
                response.getWriter().print(info);
                break;
            }
            case "update_pc": {
                int id = Integer.parseInt(request.getParameter("id"));
                String CPU = request.getParameter("CPU");
                String GPU = request.getParameter("GPU");
                String RAM = request.getParameter("RAM");
                String storageDevice = request.getParameter("storageDevice");
                String motherBoard = request.getParameter("motherBoard");
                String state = request.getParameter("state");
                String info = pcService.updatePC(id, CPU, GPU, RAM, storageDevice, motherBoard, state);
                String room=request.getParameter("room");
                info=pcService.ChangeState(id,room);
                response.getWriter().print(info);
                break;
            }
            case "delete_pc": {
                int id = Integer.parseInt(request.getParameter("id"));
                String info = pcService.deletePC(id);
                response.getWriter().print(info);
                break;
            }
            case "multi_select":{
                String state=request.getParameter("state");
                String room=request.getParameter("room");
                response.getWriter().print(pcService.getAllPCs_ByString(state,room));
                break;
            }
        }
    }
}
