package com.yh.service;

import com.yh.pojo.Department;
import com.yh.vo.DeptVo;

import java.util.List;

public interface DeptService {

    /**
     * 查询部门列表
     * @param deptVo
     * @return
     */
    List<Department> findDeptByPage(DeptVo deptVo);


    /**
     * 添加一个部门
     * @param department
     * @return
     */
    int addDept(Department department);

    /**
     * 修改部门
     * @param department
     * @return
     */
    int updateDept(Department department);

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    int deleteDeptById(Integer id);

    /**
     * 这里的查询所有部门列表是为了在员工管理界面下拉菜单进行查找
     * @return
     */
    List<Department> findDeptList();
}
