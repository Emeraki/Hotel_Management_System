package com.yh.controller.admin;

import com.yh.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 这个控制类是用来负责后台页面之间的跳转
 */
@Controller
@RequestMapping("/admin")
public class SystemController {

    /**
     * 到登录页面
     *
     * @return 登录页面名字
     */
    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 到 后台管理系统主界面
     *
     * @return 主界面名字
     */
    @RequestMapping("/home")
    public String home() {
        return "admin/home";
    }

    /**
     * employee的退出
     *
     * @return 返回一个退出的页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SystemConstant.LOGINUSER);
        //session.invalidate();
        //重定向到登录页面
        return "redirect:/admin/login";
    }

    /**
     * 到部门管理页面
     *
     * @return
     */
    @RequestMapping("/to_dept")
    public String to_dept() {
        return "admin/dept/deptManager";
    }

    /**
     * 到角色管理页面
     *
     * @return
     */
    @RequestMapping("/to_role")
    public String to_role() {
        return "admin/role/roleManager";
    }

    /**
     * 到员工管理页面
     *
     * @return
     */
    @RequestMapping("/to_employee")
    public String to_employee() {
        return "admin/employee/employeeManager";
    }

    /**
     * 去到菜单管理页面
     *
     * @return
     */
    @RequestMapping("/to_menu")
    public String to_menu() {
        return "admin/menu/menuManager";
    }

    /**
     * 去到楼层管理页面
     *
     * @return
     */
    @RequestMapping("/to_floor")
    public String to_floor() {
        return "admin/floor/floorManager";
    }

    /**
     * 去房型管理页面
     *
     * @return
     */
    @RequestMapping("/to_roomTypeManager")
    public String to_roomTypeManager() {
        return "admin/roomType/roomTypeManager";
    }

    /**
     * 去房间管理页面
     *
     * @return
     */
    @RequestMapping("/to_roomManager")
    public String to_roomManager() {
        return "admin/room/roomManager";
    }

    /**
     * 到订单管理界面
     *
     * @return
     */
    @RequestMapping("/to_orderManager")
    public String to_orderManager() {
        return "admin/orders/ordersManager";
    }

    /**
     * 到入住管理界面
     *
     * @return
     */
    @RequestMapping("/to_checkin")
    public String to_checkin() {
        return "admin/checkin/checkinManager";
    }

    @RequestMapping("/to_desktop")
    public String to_desktop(){
        return "admin/desktop/desktop";
    }
}
