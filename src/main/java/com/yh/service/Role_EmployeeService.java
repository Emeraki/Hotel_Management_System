package com.yh.service;

public interface Role_EmployeeService {
    /**
     * 查询当前角色下是否存在员工
     * @param id
     * @return
     */
    int checkRoleHasEmployee(Integer id);
}
