package com.yh.service;

import com.yh.pojo.User;

public interface UserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    User findUserByName(String loginName);

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    User login(String loginName,String password);
}
