package cn.yzw.device.service.impl;

import cn.yzw.device.dao.UserDao;
import cn.yzw.device.dao.impl.UserDaoImpl;
import cn.yzw.device.service.UserService;
import cn.yzw.device.tool.ResMessageBuilder;
import cn.yzw.device.vo.ResMessage;
import cn.yzw.device.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao = new UserDaoImpl();

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public ResMessage userLogin(HttpServletRequest request,String account, String password) {

        logger.info("user login account ["+account+"],password ["+password+"]");

        String sql = "select * from t_user where account = ?";

        try {
            User user = userDao.findObject(sql,User.class,account);
            if (user != null){
                if (user.getAccount().equals(account) && user.getPassword().equals(password)){
                    request.getSession().setAttribute("USER",user);
                    logger.info("login success user["+user+"]");
                    return ResMessageBuilder.resMessage(200,"success","登录成功");
                } else{
                    logger.info("login fail user["+user+"] 用户名或密码错误");
                    return ResMessageBuilder.resMessage(200,"fail","用户名或密码错误");
                }
            }else {
                logger.info("login fail 未找到该用户");
                return ResMessageBuilder.resMessage(200,"fail","未找到该用户");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("login fail ["+e.getMessage()+"]");
            return ResMessageBuilder.resMessage(200,"fail","系统错误");
        }
    }
}
