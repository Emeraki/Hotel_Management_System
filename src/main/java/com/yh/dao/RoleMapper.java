package com.yh.dao;

import com.yh.pojo.Role;
import com.yh.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

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
     * 根据id删除角色对应的菜单
     * @param id
     * @return
     */
    int deleteRoleMenuById(Integer id);

    /**
     * 添加这个角色的新的菜单信息
     * @param rid
     * @param mid
     * @return
     */
    int addRoleMenu(@Param("roleId") Integer rid, @Param("menuId") Integer mid);

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
     * @param rid
     * @return
     */
    int addRoleEmployee(@Param("eid") Integer eid,@Param("rid") Integer rid);

    /**
     * 根据id删除eid对应的role
     * @param eid
     * @return
     */
    int deleteRoleEmployeeByEid(Integer eid);
}
