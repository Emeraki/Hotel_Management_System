package com.yh.service.impl;

import com.yh.dao.EmployeeMapper;
import com.yh.pojo.Employee;
import com.yh.service.EmployeeService;
import com.yh.utils.PasswordUtil;
import com.yh.utils.SystemConstant;
import com.yh.utils.UUIDUtils;
import com.yh.vo.EmployeeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    public Employee login(String name, String pwd) {
        Employee employee = employeeMapper.findEmployeeByLoginName(name);
        if (employee != null){
            //将密码加密处理
            String newPwd = PasswordUtil.md5(pwd,employee.getSalt(), SystemConstant.PASSWORD_COUNT);

            //和数据库里的密码作比较
            if (employee.getLogin_pwd().equals(newPwd)){
                return employee;
            }

        }
        return null;
    }

    public int getEmployeeCountByDeptId(Integer dept_id) {
        return employeeMapper.getEmployeeCountByDeptId(dept_id);
    }

    public List<Employee> findEmployeeList(EmployeeVo employeeVo) {
        return employeeMapper.findEmployeeList(employeeVo);
    }

    public int addEmployee(Employee employee) {
        //添加创建的时间
        employee.setCreate_date(new Date());

        //密码信息
        employee.setSalt(UUIDUtils.randomUUID());//设置盐值
        employee.setLogin_pwd(PasswordUtil.md5(SystemConstant.DEFAULT_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Employee employee) {

        //设置修改时间
        employee.setModify_date(new Date());

        return employeeMapper.updateEmployee(employee);
    }

    public int deleteById(Integer id) {
        //删除之前应该先删除这个员工对应的角色信息
        //员工都没了，就不保存他的角色信息了
        employeeMapper.deleteRole_employee(id);

        return employeeMapper.deleteById(id);
    }

    public int resetPwd(Integer id, String newPwd) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setSalt(UUIDUtils.randomUUID());
        employee.setLogin_pwd(PasswordUtil.md5(newPwd,employee.getSalt(),SystemConstant.PASSWORD_COUNT));

        return employeeMapper.updateEmployee(employee);
    }

}
