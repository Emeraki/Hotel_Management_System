package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    //员工实体类
    private Integer id;
    private String login_name;
    private String login_pwd;
    private String name;
    private Integer sex;
    private Integer dept_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hire_date;
    private String salt;
    private Integer create_by;
    private Date create_date;
    private Integer modify_by;
    private Date modify_date;
    private String remark;
    private String dept_name;
}
