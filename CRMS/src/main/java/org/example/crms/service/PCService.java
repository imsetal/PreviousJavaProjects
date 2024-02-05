package org.example.crms.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.crms.dao.ManagementDAO;
import org.example.crms.dao.PCDAO;
import org.example.crms.dao.RoomDAO;
import org.example.crms.model.Management;
import org.example.crms.model.PC;

import java.sql.SQLException;
import java.util.List;

public class PCService {
    private final PCDAO pcDAO = new PCDAO();
    private final RoomDAO roomDAO=new RoomDAO();
    private final ManagementDAO managementDAO=new ManagementDAO();

    public PCService() {
    }

    public JSONArray getAllPCs() {
        try {
            List<Management> ms=managementDAO.selectAll();
            List<PC> pcs = pcDAO.selectAll();
            JSONArray jsonArray = new JSONArray();
            for (PC pc : pcs) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", pc.getId());
                jsonObject.put("CPU", pc.getCPU());
                jsonObject.put("GPU", pc.getGPU());
                jsonObject.put("RAM", pc.getRAM());
                jsonObject.put("StorageDevice", pc.getStorageDevice());
                jsonObject.put("MotherBoard", pc.getMotherBoard());
                jsonObject.put("state", pc.getState());
                jsonObject.put("room", "-");
                for(Management m:ms){
                    if(pc.getId()==m.getPcId()){
                        jsonObject.put("room", m.getRoom());
                        break;
                    }
                }
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getAllPCs_ByString(String state,String room) {
        try {
            String str="";
            int n=0;
            if(!state.equals("全部")){
                if(n>0)str+=" and ";
                str+="PC.state='"+state+"'";
                n++;
            }
            if(!room.equals("全部")){
                if(n>0)str+=" and ";
                if(room.equals("-"))str+="PC.id not in (select pc_id from management)";
                else str+="PC.id in (select pc_id from management where room='"+room+"')";
                n++;
            }
            List<Management> ms=managementDAO.selectAll();
            List<PC> pcs;
            if(n==0)pcs = pcDAO.selectAll();
            else pcs = pcDAO.select_ByString(str);
            JSONArray jsonArray = new JSONArray();
            for (PC pc : pcs) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", pc.getId());
                jsonObject.put("CPU", pc.getCPU());
                jsonObject.put("GPU", pc.getGPU());
                jsonObject.put("RAM", pc.getRAM());
                jsonObject.put("StorageDevice", pc.getStorageDevice());
                jsonObject.put("MotherBoard", pc.getMotherBoard());
                jsonObject.put("state", pc.getState());
                jsonObject.put("room", "-");
                for(Management m:ms){
                    if(pc.getId()==m.getPcId()){
                        jsonObject.put("room", m.getRoom());
                        break;
                    }
                }
                jsonArray.add(jsonObject);
            }
            return jsonArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getPCById(int id) {
        try {
            PC pc = pcDAO.selectById(id);
            if (pc != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", pc.getId());
                jsonObject.put("CPU", pc.getCPU());
                jsonObject.put("GPU", pc.getGPU());
                jsonObject.put("RAM", pc.getRAM());
                jsonObject.put("StorageDevice", pc.getStorageDevice());
                jsonObject.put("MotherBoard", pc.getMotherBoard());
                jsonObject.put("state", pc.getState());
                return jsonObject;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String addPC(String CPU, String GPU, String RAM, String storageDevice, String motherBoard, String state) {
        try {
            PC pc = new PC(0, CPU, GPU, RAM, storageDevice, motherBoard, state); // Assuming 0 for id as it might be auto-generated
            pcDAO.add(pc);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String updatePC(int id, String CPU, String GPU, String RAM, String storageDevice, String motherBoard, String state) {
        try {
            PC pc = new PC(id, CPU, GPU, RAM, storageDevice, motherBoard, state);
            pcDAO.update(pc);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String deletePC(int id) {
        try {
            pcDAO.delete(id);
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String ChangeState(int id,String room){
        try {
            if(room.equals("-")){
                if (managementDAO.selectByPcId(id) != null) {
                    managementDAO.delete(id);
                }
                PC pc = pcDAO.selectById(id);
                if(pc.getState().equals("使用中")) pc.setState("闲置中");
                pcDAO.update(pc);
            }else {
                if (managementDAO.selectByPcId(id) == null) {
                    List<Management> managements=managementDAO.selectByRoom(room);
                    int num=roomDAO.selectByRoomName(room).getSeat_num();
                    if(managements.size()>=num){
                        return "这个课室已满";
                    }
                    managementDAO.add(new Management(id, room));
                }else{
                    managementDAO.update(new Management(id,room));
                }
                PC pc = pcDAO.selectById(id);
                if(pc.getState().equals("闲置中")) pc.setState("使用中");
                pcDAO.update(pc);
            }
            return "success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
