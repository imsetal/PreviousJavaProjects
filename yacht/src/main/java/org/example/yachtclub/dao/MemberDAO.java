package com.example.yachtclub.dao;

import com.example.yachtclub.model.Member;
import com.example.yachtclub.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn=null;
    public MemberDAO(){
        DbUtil dbUtil=new DbUtil();
        try {
            conn=dbUtil.getConn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> select() throws SQLException {
        List<Member> members=new ArrayList<>();
        String sql = "select * from member";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            Member member=new Member();
            member.setUser(rs.getString("user"));
            member.setName(rs.getString("name"));
            member.setGender(rs.getString("gender"));
            member.setBirthday(rs.getString("birthday"));
            member.setLevel(rs.getInt("level"));
            members.add(member);
//            System.out.println(member.getName());
        }
        return members;
    }

    public Member select_byUser(String user_string) throws SQLException {
        Member member=new Member();
        String sql = "select * from member where user='"+user_string+"';";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            member.setUser(rs.getString("user"));
            member.setName(rs.getString("name"));
            member.setGender(rs.getString("gender"));
            member.setBirthday(rs.getString("birthday"));
            member.setLevel(rs.getInt("level"));
        }
        return member;
    }

    public boolean select_byUser_exist(String user_string) throws SQLException {
        String sql = "select * from member where user='"+user_string+"';";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        int i=0;
        while(rs.next()){
            i++;
        }
        if(i==0)return false;
        else return true;
    }

    public void add(Member member) throws SQLException {
        String sql="INSERT INTO member(user,name,gender,birthday,level) values('"+member.getUser()+"','"+member.getName()+"','"+member.getGender()+"','"+member.getBirthday()+"',"+member.getLevel()+")";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.execute();
    }

    public void del_byUser(String user){
        String sql="delete from member where user='"+user+"'";
        PreparedStatement ptmt= null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Member member) throws SQLException {
        String sql="UPDATE member set user='"+member.getUser()+"',name='"+member.getName()+"',gender='"+member.getGender()+"',birthday='"+member.getBirthday()+"',level="+member.getLevel()+" where user='"+member.getUser()+"'";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.execute();
    }
}
