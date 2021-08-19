package com.yh.service.impl;

import com.yh.dao.DeptMapper;
import com.yh.pojo.Department;
import com.yh.service.DeptService;
import com.yh.vo.DeptVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    public List<Department> findDeptByPage(DeptVo deptVo) {
        return deptMapper.findDeptByPage(deptVo);
    }

    public int addDept(Department department) {

        //保存创建时间
        department.setCreate_time(new Date());

        return deptMapper.addDept(department);
    }

    public int updateDept(Department department) {
        return deptMapper.updateDept(department);
    }

    public int deleteDeptById(Integer id) {
        return deptMapper.deleteDeptById(id);
    }

    public List<Department> findDeptList() {
        return deptMapper.findDeptList();
    }
}
