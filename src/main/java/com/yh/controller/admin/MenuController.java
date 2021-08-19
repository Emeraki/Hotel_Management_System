package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Employee;
import com.yh.pojo.Menu;
import com.yh.service.MenuService;
import com.yh.utils.*;
import com.yh.vo.MenuVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    //注入MenuService
    @Resource
    private MenuService menuService;

    /**
     * 加载首页左侧菜单
      * @param session
     * @return
     */
    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session){

        //得到当前登录的用户
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);

        //调用查询所有菜单的方法
        //这个查询会查询所有菜单
        //List<Menu> menuList = menuService.findMenuList();

        List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());

        //创建集合 保存菜单关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();

        //对照init.json，创建三个map，分别是homeInfo，logoInfo，menuInfo
        Map<String, Object> homeInfo = new LinkedHashMap<String, Object>();
        Map<String, Object> logoInfo = new LinkedHashMap<String, Object>();
        //这里的menuInfo单独在map里创建，之后再将homeInfo，logoInfo整合到map里。
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        //循环遍历菜单列表，目的是创建菜单之间的父子关系
        for (Menu menu : menuList) {
            //创建一个菜单节点对象
            MenuNode menuNode = new MenuNode();

            //往这个节点里面加入数据
            menuNode.setHref(menu.getHref());//链接
            menuNode.setIcon(menu.getIcon());//图标
            menuNode.setTitle(menu.getTitle());//菜单标题
            menuNode.setId(menu.getId());//菜单id
            menuNode.setPid(menu.getPid());//菜单pid
            menuNode.setSpread(menu.getSpread());//菜单是否展开

            //将这个menuNode添加到list里
            menuNodeList.add(menuNode);

        }


        //保存homeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/to_desktop");

        //保存logoInfo信息
        logoInfo.put("title","酒店管理系统");
        logoInfo.put("image","/statics/layui/images/logo.png");
        logoInfo.put("href","/admin/home");

        //保存menuInfo信息
        map.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);

        return JSON.toJSONString(map);
    }

    /**
     * 加载菜单管理页面的左侧导航树
     * @return
     */
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuList();

        //创建集合保存节点信息
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();

        //循坏遍历菜单列表集合
        for (Menu menu : menuList) {
            //判断当前菜单是否展开
            Boolean spread = menu.getSpread()==1? false : true;
            //将菜单信息保存到treeNodes集合中
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }

        //返回数据
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 加载右侧的table数据
     * @param menuVo
     * @return
     */
    @RequestMapping("/findMenuListByPage")
    public DataGridViewResult findMenuListByPage(MenuVo menuVo){
        //设置分页信息
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());

        System.out.println("======================");
        System.out.println(menuVo.getId());
        System.out.println(menuVo.getPid());
        System.out.println("======================");

        //调用查询菜单列表的方法
        List<Menu> menuList = menuService.findMenuListByPage(menuVo);

        //创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);

        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public String addMenu(Menu menu){

        Map<String, Object> map = new HashMap<String, Object>();

        if (menuService.addMenu(menu) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加菜单成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加菜单失败");
        }

        return JSON.toJSONString(map);

    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        Map<String, Object> map = new HashMap<String, Object>();

        if (menuService.updateMenu(menu) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改菜单成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改菜单失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 当前菜单是否有子菜单
     * @param id
     * @return
     */
    @RequestMapping("/menuHaveChild")
    public String menuHaveChild(Integer id){

        Map<String, Object> map = new HashMap<String, Object>();

        if (menuService.menuHaveChild(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该菜单存在子菜单，无法删除");
        }else {
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }

    /**
     * 删除指定菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenu")
    public String deleteMenu(Integer id){

        Map<String, Object> map = new HashMap<String, Object>();

        if (menuService.deleteMenu(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除菜单成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除失败");
        }

        return JSON.toJSONString(map);
    }
}
