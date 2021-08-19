package com.yh.service.impl;

import com.yh.dao.MenuMapper;
import com.yh.pojo.Menu;
import com.yh.service.MenuService;
import com.yh.vo.MenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;


    public List<Menu> findMenuList() {
        return menuMapper.findMenuList();
    }

    public List<Menu> findMenuListByEmployeeId(Integer id) {
        return menuMapper.findMenuListByEmployeeId(id);
    }

    public List<Integer> findMenuIdListByRoleId(int id) {
        return menuMapper.findMenuIdListByRoleId(id);
    }

    public List<Menu> findMenuByMenuId(List<Integer> menuId) {
        return menuMapper.findMenuByMenuId(menuId);
    }

    public List<Menu> findMenuListByPage(MenuVo menuVo) {
        return menuMapper.findMenuListByPage(menuVo);
    }

    public int addMenu(Menu menu) {
        menu.setTarget("_self");

        //如果没有选择父菜单，就该把pid置为0
        if (menu.getPid() == null){
            menu.setPid(0);
        }

        return menuMapper.addMenu(menu);
    }

    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    public int menuHaveChild(Integer id) {
        return menuMapper.menuHaveChild(id);
    }

    public int deleteMenu(Integer id) {
        return menuMapper.deleteMenu(id);
    }
}
