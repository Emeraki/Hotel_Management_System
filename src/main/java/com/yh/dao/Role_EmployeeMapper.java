package com.yh.dao;

public interface Role_EmployeeMapper {
    /**
     * 查询当前角色下是否存在员工
     * @param id
     * @return
     */
    int checkRoleHasEmployee(Integer id);
}
