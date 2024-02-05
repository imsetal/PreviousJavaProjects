package org.example.crms.servlet;

import org.example.crms.util.DbUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet("/mysqlServlet")
public class MySQLServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String action=request.getParameter("action");
        switch(action){
            case "bf":{
                try {
                    dbUtil.bf();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}
