package cn.yzw.device.dao.impl;

import cn.yzw.device.dao.UserDao;
import cn.yzw.device.tool.TxQueryRunner;
import cn.yzw.device.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import java.util.List;

public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {



    private QueryRunner queryRunner = new TxQueryRunner();

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }


}
