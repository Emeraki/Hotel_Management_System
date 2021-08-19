package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        Map<String, Object> map = new HashMap<String, Object>();

        if (userService.addUser(user) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"注册用户成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"注册用户失败");
        }

        return JSON.toJSONString(map);

    }

    /**
     * 通过名字查询用户
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUserByName")
    public String findUserByName(String loginName){
        Map<String, Object> map = new HashMap<String, Object>();

        if (userService.findUserByName(loginName) != null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该用户已经注册");
        }else {
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.login(loginName, password);
        if (user != null){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"登录成功");
            //保存用户信息到session中
            session.setAttribute(SystemConstant.FRONT_LOGINUSER,user);
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"登录失败，用户名或密码错误");
        }

        return JSON.toJSONString(map);

    }
}
