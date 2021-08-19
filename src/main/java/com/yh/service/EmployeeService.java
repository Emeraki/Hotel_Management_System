package com.yh.service;

import com.yh.pojo.Employee;
import com.yh.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     * @param name 员工名
     * @param pwd 密码
     * @return 一个员工
     */
    Employee login(String name,String pwd);

    /**
     * 根据部门编号查询员工数量
     * @param dept_id
     * @return
     */
    int getEmployeeCountByDeptId(Integer dept_id);


    /**
     * 查询所有的员工
     * @return
     */
    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    /**
     * 添加新员工
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);


    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 重置密码
     * @param id
     * @return
     */
    int resetPwd(Integer id,String newPwd);

}
