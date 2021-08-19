package com.yh.service.impl;

import com.yh.dao.Role_EmployeeMapper;
import com.yh.service.Role_EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class Role_EmployeeServiceImpl implements Role_EmployeeService {

    @Resource
    private Role_EmployeeMapper role_employeeMapper;

    public int checkRoleHasEmployee(Integer id) {
        return role_employeeMapper.checkRoleHasEmployee(id);
    }
}
