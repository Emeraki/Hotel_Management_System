package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Employee;
import com.yh.service.EmployeeService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.EmployeeVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    /**
     * 登录的时候检测密码用，之后登陆成功，设置一个session
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){

        Map<String, Object> map = new HashMap<String, Object>();

        //调用员工登录的方法
        Employee employee = employeeService.login(username, password);

        //对象不为空，则代表登录成功
        if (employee != null){
            //保存当前登录用户
            session.setAttribute(SystemConstant.LOGINUSER,employee);
            map.put(SystemConstant.SUCCESS,true);//成功
        }else {
            map.put(SystemConstant.SUCCESS,false);//失败
            map.put(SystemConstant.MESSAGE,"登录失败");
        }

        return JSON.toJSONString(map);

    }

    /**
     * 在员工管理页面分页展示所有的employee员工
     * @param employeeVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(EmployeeVo employeeVo){

        //设置分页信息
        PageHelper.startPage(employeeVo.getPage(),employeeVo.getLimit());

        //调用查询方法
        List<Employee> employeeList = employeeService.findEmployeeList(employeeVo);

        //创建分页对象
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());

    }

    /**
     * 添加一个新的员工
     * @param employee
     * @param session
     * @return
     */
    @RequestMapping("/addEmployee")
    public String addEmployee(Employee employee,HttpSession session){

        System.out.println("查看接收到的employee");
        System.out.println(employee.toString());

        Map<String, Object> map = new HashMap<String, Object>();

        //通过session获取当前登录用户
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);

        //设置创建人
        employee.setCreate_by(loginUser.getId());


        //调用新增员工的方法
        if (employeeService.addEmployee(employee) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }

        return JSON.toJSONString(map);

    }

    /**
     * 修改员工信息
     * @param employee
     * @param session
     * @return
     */
    @RequestMapping("/updateEmployee")
    public String updateEmployee(Employee employee,HttpSession session){
        Map<String, Object> map = new HashMap<String, Object>();

        //通过session获取当前登录用户
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);

        //设置修改人
        employee.setModify_by(loginUser.getId());

        //调用新增员工的方法
        if (employeeService.updateEmployee(employee) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 通过id删除员工
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(int id){
        Map<String, Object> map = new HashMap<String, Object>();

        if (employeeService.deleteById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 重置密码
     * @param id 员工id
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(Integer id,String newPwd){

        Map<String, Object> map = new HashMap<String, Object>();

        if (employeeService.resetPwd(id,newPwd) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"重置成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"重置失败");
        }

        return JSON.toJSONString(map);
    }
}
