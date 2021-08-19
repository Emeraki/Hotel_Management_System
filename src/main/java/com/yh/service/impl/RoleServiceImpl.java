package com.yh.service.impl;

import com.yh.dao.RoleMapper;
import com.yh.pojo.Role;
import com.yh.service.RoleService;
import com.yh.vo.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> findRoleList(RoleVo roleVo) {
        return roleMapper.findRoleList(roleVo);
    }

    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    public int checkRoleHasEmployee(Integer id) {
        return roleMapper.checkRoleHasEmployee(id);
    }

    public int deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    public int saveRoleMenu(Integer roleId, String ids) {
        //删除原有的菜单关系
        roleMapper.deleteRoleMenuById(roleId);
        //将ids拆分成数组
        String[] idsStr = ids.split(",");

        try {
            //循环调用添加
            for (int i = 0; i < idsStr.length; i++) {
                roleMapper.addRoleMenu(roleId,Integer.valueOf(idsStr[i]));
            }
            //成功返回1
            return 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //失败返回0
        return 0;
    }

    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    public List<Integer> findEmployeeRole(Integer eid) {
        return roleMapper.findEmployeeRole(eid);
    }

    public int addRoleEmployee(Integer eid, List<Integer> rids) {
        int flag = 0;

        try {
            //首先要进行删除，把role_employee表里和eid有关的删除
            roleMapper.deleteRoleEmployeeByEid(eid);

            //之后进行循环添加
            for (int i = 0; i < rids.size(); i++) {
                roleMapper.addRoleEmployee(eid,rids.get(i));
            }

            flag = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
}
