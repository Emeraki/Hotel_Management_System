package com.yh.vo;

import com.yh.pojo.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVo extends Department {

    //这个类主要是用来分页

    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

}
