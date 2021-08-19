package com.yh.dao;

import com.yh.pojo.Employee;
import com.yh.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    /**
     * 根据登录账号查询员工信息
     * @param loginName
     * @return
     */
    Employee findEmployeeByLoginName(@Param("login_name") String loginName);

    /**
     * 根据部门编号查询员工数量
     * @param dept_id
     * @return
     */
    int getEmployeeCountByDeptId(Integer dept_id);

    /**
     * 查询所有的员工
     * 为什么这里对应的mapper.xml要写成模糊查询，
     * 因为这个方法不单单只是要查出来所有的员工名单
     * 搜索框也要用这个方法，点击搜索会把搜索框里面的一些数据封装到employeeVo里面，进行查询，这样就可以做到更新这个表单
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
     * 删除这个员工对应的角色信息
     * @param id
     * @return
     */
    int deleteRole_employee(Integer id);
}
