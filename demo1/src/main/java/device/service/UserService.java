package cn.yzw.device.service;

import cn.yzw.device.vo.ResMessage;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户登录
     * @param account 用户账户
     * @param password 用户密码
     * @return 返回响应结果
     */
    ResMessage userLogin(HttpServletRequest request, String account, String password);
}
