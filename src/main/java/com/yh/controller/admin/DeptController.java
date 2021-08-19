package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Department;
import com.yh.service.DeptService;
import com.yh.service.EmployeeService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.DeptVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @Resource
    private EmployeeService employeeService;

    /**
     * 查询部门列表
     * @param deptVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo deptVo){

        //设置分页信息
        PageHelper.startPage(deptVo.getPage(),deptVo.getLimit());

        //调用分页查询的方法
        List<Department> deptList = deptService.findDeptByPage(deptVo);

        //创建分页对象
        PageInfo<Department> pageInfo = new PageInfo<Department>(deptList);

        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @RequestMapping("/addDept")
    public String addDept(Department department){

        Map<String, Object> map = new HashMap<String, Object>();

        if (deptService.addDept(department) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }


        return JSON.toJSONString(map);
    }

    /**
     * 修改部门
     * @param department
     * @return
     */
    @RequestMapping("/updateDept")
    public String updateDept(Department department){

        Map<String, Object> map = new HashMap<String, Object>();

        if (deptService.updateDept(department) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改失败");
        }


        return JSON.toJSONString(map);
    }

    /**
     * 查询这个部门下是否有员工
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasEmployee")
    public String checkDeptHasEmployee(Integer id){

        Map<String, Object> map = new HashMap<String, Object>();

        if (employeeService.getEmployeeCountByDeptId(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"该部门下存在员工，无法删除");
        }else {
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }

    /**
     * 删除员工
     * @param id 员工id
     * @return
     */
    @RequestMapping("/deleteDeptById")
    public String deleteDeptById(Integer id){

        Map<String, Object> map = new HashMap<String, Object>();

        System.out.println("=============================");
        System.out.println("idididididid=="+id);
        System.out.println("=============================");

        if (deptService.deleteDeptById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除部门成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除部门失败");
        }

        return JSON.toJSONString(map);


    }

    /**
     * 这里的查询所有部门列表是为了在员工管理界面下拉菜单进行查找
     * @return
     */
    @RequestMapping("/deptList")
    public String findDeptList(){
        return JSON.toJSONString(deptService.findDeptList());
    }
}
