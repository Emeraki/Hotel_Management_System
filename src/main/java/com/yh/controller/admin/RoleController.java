package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.yh.pojo.Menu;
import com.yh.pojo.Role;
import com.yh.service.MenuService;
import com.yh.service.RoleService;
import com.yh.service.Role_EmployeeService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.utils.TreeNode;
import com.yh.vo.RoleVo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private Role_EmployeeService role_employeeService;

    @Resource
    private MenuService menuService;

    /**
     * 查询角色列表
     *
     * @param roleVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo) {

        //设置分页信息
        PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());

        //调用查询角色列表方法
        List<Role> roleList = roleService.findRoleList(roleVo);

        //创建分页信息对象
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);

        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping("addRole")
    public String addRole(Role role) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (roleService.addRole(role) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "添加成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "添加失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping("updateRole")
    public String updateRole(Role role) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (roleService.updateRole(role) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "修改成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "修改失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 查询当前角色下是否存在员工
     *
     * @param id
     * @return
     */
    @RequestMapping("/checkRoleHasEmployee")
    public String checkRoleHasEmployee(Integer id) {

        System.out.println("======================");
        System.out.println("now now now now");
        System.out.println("======================");

        Map<String, Object> map = new HashMap<String, Object>();

        if (role_employeeService.checkRoleHasEmployee(id) > 0) {
            map.put(SystemConstant.EXIST, true);
            map.put(SystemConstant.MESSAGE, "该角色下存在员工，无法删除");
        } else {
            map.put(SystemConstant.EXIST, false);
        }

        return JSON.toJSONString(map);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoleById")
    public String deleteRoleById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (roleService.deleteRoleById(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "删除成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "删除失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 初始化一个角色的菜单栏，里面是他已有的一些菜单
     * @param roleId
     * @return
     */
    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId) {
        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();

        //根据角色id查询该角色拥有的菜单id
        List<Integer> menuId = menuService.findMenuIdListByRoleId(roleId);

        //定义集合 保存菜单信息
        List<Menu> currentMenus = new ArrayList<Menu>();

        //判断集合是否存在数据
        if (menuId != null && menuId.size() > 0) {
            //根据菜单id查询菜单信息
            currentMenus = menuService.findMenuByMenuId(menuId);
        }

        //创建结合保存树节点信息
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();

        //将数据返回到页面
        for (Menu menu : menuList) {

            //定义变量标识，是否选中
            String checkArr = "0"; //0不选中，1选中

            //嵌套内层循环，如果自己拥有的menuList和全部的menuList相同，则选中
            for (Menu currentMenu : currentMenus) {
                if (currentMenu.getId() == menu.getId()) {
                    //复选框选中
                    checkArr = "1";
                    break;
                }
            }

            //定义菜单是否展开
            Boolean spread = (menu.getSpread() == 1) ? true : false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread, checkArr));
        }

        return new DataGridViewResult(treeNodes);
    }

    /**
     * 将修改过后的菜单栏进行保存
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(Integer roleId,String ids){
        System.out.println("=====================");
        System.out.println("roleID:"+roleId+"; ids:"+ids);
        System.out.println("=====================");

        Map<String, Object> map = new HashMap<String, Object>();
        if (roleService.saveRoleMenu(roleId,ids) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"保存数据成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"保存数据失败");
        }


        return JSON.toJSONString(map);
    }

    /**
     * 根据员工id查询角色信息
     * @param id
     * @return
     */
    @RequestMapping("initEmployee_RoleTable")
    public DataGridViewResult initEmployee_RoleTable(Integer id){

        //调用查询所有角色列表的方法
        List<Map<String, Object>> roleList = roleService.findRoleListByMap();

        //调用根据员工id查询员工角色方法
        List<Integer> ridList = roleService.findEmployeeRole(id);

        for (Map<String, Object> map : roleList) {
            //定义变量：此角色是否被选中
            boolean flag = false;

            //获取每一个角色id
            Integer rid = (Integer) map.get("id");

            //循环比较
            for (Integer integer : ridList) {
                if (rid.equals(integer)){
                    flag = true;
                }
            }

            //将状态保存在map中
            map.put("LAY_CHECKED",flag);
        }

        for (Map<String, Object> map : roleList) {
            System.out.println(map);
        }

        return new DataGridViewResult(Long.valueOf(roleList.size()),roleList);
    }

    /**
     * 根据eid和rids为员工分配角色
     * @param eid
     * @param rids
     * @return
     */
    @RequestMapping("addRoleEmployee")
    public String addRoleEmployee(Integer eid,String rids){

        //声明一个List，储存前端传回来的rids
        List<Integer> ridList = new ArrayList<Integer>();
        Map<String, Object> map = new HashMap<String, Object>();

        //处理rids
        JSONArray jsonArray = JSONArray.parseArray(rids);

        //遍历数组，将数据写入ridList
        for (int i = 0; i < jsonArray.size(); i++) {
            ridList.add((Integer) jsonArray.get(i));
        }

        if (roleService.addRoleEmployee(eid,ridList) == 1){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"分配角色成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"分配角色识别");
        }


        return JSON.toJSONString(map);
    }
}
