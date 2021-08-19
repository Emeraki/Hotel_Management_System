package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    //部门实体类
    private Integer id;
    private String dept_name;
    private String address;
    private Date create_time;
    private String remark;
}
