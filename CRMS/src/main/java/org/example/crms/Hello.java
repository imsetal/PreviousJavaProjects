package org.example.crms;

import org.example.crms.dao.PCDAO;
import org.example.crms.dao.RoomDAO;
import org.example.crms.model.PC;
import org.example.crms.model.Room;

import java.sql.SQLException;

public class Hello{
    public static void main(String[] args){
        PCDAO pcDAO=new PCDAO();
        for (int i=0;i<100;i++){
            try {
                pcDAO.add(new PC(
                        i+1,
                        "Intel Core i5-10400F 六核心处理器（2.9 GHz，最高可达4.3 GHz）",
                        "Intel UHD Graphics 630",
                        "16GB DDR4 3200MHz",
                        "256GB SSD + 1TB 7200RPM SATA硬盘",
                        "华硕（ASUS）PRIME B560M-A",
                        "闲置中"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}