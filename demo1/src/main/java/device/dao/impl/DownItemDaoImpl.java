package cn.yzw.device.dao.impl;

import cn.yzw.device.vo.DownItem;

import java.sql.SQLException;

public class DownItemDaoImpl extends AbstractBaseDaoImpl<DownItem> {

    public int add(DownItem downItem) throws SQLException {
        String sql = "insert into t_downItem(name,number,category,price,QGP,specs,factory," +
                "buyDate,downDate,opinion,doPerson)values(?,?,?,?,?,?,?,?,?,?,?)";

        return queryRunner.update(sql,downItem.getName(),downItem.getNumber(),downItem.getCategory(),
                downItem.getPrice(),downItem.getQGP(),downItem.getSpecs(),downItem.getFactory(),
                downItem.getBuyDate(),downItem.getDownDate(),downItem.getOpinion(),downItem.getDoPerson());
    }

}
