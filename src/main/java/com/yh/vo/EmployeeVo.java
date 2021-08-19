package com.yh.vo;

import com.yh.pojo.Department;
import com.yh.pojo.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo extends Employee {

    //这个类主要是用来分页

    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    //EmployeeVo类需要再加上下面两个属性
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
