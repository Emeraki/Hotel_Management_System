package com.yh.dao;

import com.yh.pojo.User;

public interface UserMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名查询用户
     * @param
     * @return
     */
    User findUserByName(String loginName);

}
