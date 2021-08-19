package com.yh.service.impl;

import com.yh.dao.UserMapper;
import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.utils.PasswordUtil;
import com.yh.utils.SystemConstant;
import com.yh.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public int addUser(User user) {

        //加密盐值
        user.setSalt(UUIDUtils.randomUUID());

        //对密码进行加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(), SystemConstant.PASSWORD_COUNT));

        return userMapper.addUser(user);
    }

    public User findUserByName(String loginName) {
        return userMapper.findUserByName(loginName);
    }

    public User login(String loginName, String password) {
        User user = userMapper.findUserByName(loginName);

        if (user!=null){
            String salt = user.getSalt();
            String password1 = user.getPassword();

            if (password1.equals(PasswordUtil.md5(password,salt,SystemConstant.PASSWORD_COUNT))){
                return user;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }
}
