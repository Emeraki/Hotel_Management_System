package com.yh.service;

import com.yh.pojo.Role;
import com.yh.vo.RoleVo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 获取角色列表
     * @param roleVo
     * @return
     */
    List<Role> findRoleList(RoleVo roleVo);

    /**
     * 添加一个角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 修改一个角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 查询当前角色下是否存在员工
     * @param id
     * @return
     */
    int checkRoleHasEmployee(Integer id);

    /**
     * 删除当前角色
     * @param id
     * @return
     */
    int deleteRoleById(Integer id);

    /**
     * 保存当前角色所拥有的的菜单
     * @param roleId
     * @param ids
     * @return
     */
    int saveRoleMenu(Integer roleId,String ids);

    /**
     * 查询所有角色列表
     * @return
     */
    List<Map<String,Object>> findRoleListByMap();

    /**
     * 根据员工id查询员工拥有的角色列表
     * @param eid
     * @return
     */
    List<Integer> findEmployeeRole(Integer eid);

    /**
     * 根据前端传回来的eid和rids，进行角色的分配
     * @param eid
     * @param rids
     * @return
     */
    int addRoleEmployee(Integer eid,List<Integer> rids);
}
