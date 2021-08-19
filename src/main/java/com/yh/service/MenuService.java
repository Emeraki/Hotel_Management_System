package com.yh.service;

import com.yh.pojo.Menu;
import com.yh.vo.MenuVo;

import java.util.List;

public interface MenuService {
    /**
     * 查询所有菜单
     * @return 返回菜单的一个list
     */
    List<Menu> findMenuList();

    /**
     * 根据登录的用户不同，来加载菜单
     * @param id
     * @return
     */
    List<Menu> findMenuListByEmployeeId(Integer id);

    /**
     * 根据角色iD查找其对应的menu的id
     * @param id
     * @return
     */
    List<Integer> findMenuIdListByRoleId(int id);

    /**
     * 根据菜单编号查询指定的几个菜单
     * @param menuId
     * @return
     */
    List<Menu> findMenuByMenuId(List<Integer> menuId);

    /**
     * 查询菜单列表
     * @param menuVo
     * @return
     */
    List<Menu> findMenuListByPage(MenuVo menuVo);

    /**
     * 添加一个菜单
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 查询当前菜单是否有子菜单
     * @param id
     * @return
     */
    int menuHaveChild(Integer id);

    /**
     * 删除指定菜单
     * @param id
     * @return
     */
    int deleteMenu(Integer id);
}
