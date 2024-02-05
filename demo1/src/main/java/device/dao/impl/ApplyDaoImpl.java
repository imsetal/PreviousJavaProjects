package cn.yzw.device.dao.impl;

import cn.yzw.device.dao.BaseDao;
import cn.yzw.device.vo.Apply;

import java.util.List;

public class ApplyDaoImpl extends AbstractBaseDaoImpl<Apply> implements BaseDao<Apply, Integer> {

    private final static String BASE_FIELD = "id,name,count,applyDate,applyPerson,amount," +
            "reason,category,state,specs,price,cateNum,isShow,opinion,checkDate,checkPerson";

    @Override
    public List<Apply> findAll() throws Exception {
        String sql = "select "+BASE_FIELD+" from t_apply";
        return this.findListObj(sql,Apply.class, (Object) null);
    }

    @Override
    public int update(Apply apply) throws Exception {
        String sql = "update t_apply set name = ?,count = ?,applyPerson = ?,amount = ?," +
                "state = ?,specs = ?,price = ?,isShow = ?,opinion = ?,checkDate = ?," +
                "checkPerson = ? where id = ?";
        return queryRunner.update(sql,apply.getName(),apply.getCount(),apply.getApplyPerson(),apply.getAmount(),
                apply.getState(),apply.getSpecs(),apply.getPrice(),apply.getIsShow(),apply.getOpinion(),
                apply.getCheckDate(),apply.getCheckPerson(),apply.getId());
    }

    @Override
    public int insert(Apply apply) throws Exception {
        String sql = "insert into t_apply(name,cid,category,count,applyDate,applyPerson," +
                "amount,reason,specs,price,cateNum) values " +
                "(?,?,?,?,?,?,?,?,?,?,?)";
        return queryRunner.update(sql,apply.getName(),apply.getCid(),apply.getCategory(),apply.getCount(),
                apply.getApplyDate(),apply.getApplyPerson(),apply.getAmount(),apply.getReason(),apply.getSpecs(),
                apply.getPrice(),apply.getCateNum());
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        String sql = "delete from t_apply where id = ?";
        return queryRunner.update(sql,id);
    }

    @Override
    public Apply findById(Integer id) throws Exception {
        String sql = "select "+BASE_FIELD+" from t_apply where id = ?";
        return this.findObject(sql,Apply.class,id);
    }
}
