package cn.yzw.device.dao.impl;

import cn.yzw.device.dao.FixInfoDao;
import cn.yzw.device.vo.FixInfo;

import java.util.List;

public class FixInfoDaoImpl extends AbstractBaseDaoImpl<FixInfo> implements FixInfoDao {
    @Override
    public List<FixInfo> findAll() throws Exception {
        return null;
    }

    @Override
    public int update(FixInfo fixInfo) throws Exception {
        return 0;
    }

    @Override
    public int insert(FixInfo fixInfo) throws Exception {

        String sql = "insert into t_fixInfo(number,name,fixFactory,fixDate,fixPrice," +
                "fixPerson,opinion) values(?,?,?,?,?,?,?)";

        return queryRunner.update(sql,fixInfo.getNumber(),fixInfo.getName(),fixInfo.getFixFactory(),
                fixInfo.getFixDate(),fixInfo.getFixPrice(),fixInfo.getFixPerson(),fixInfo.getOpinion());
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public FixInfo findById(Integer id) throws Exception {
        return null;
    }
}
