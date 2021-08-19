package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //前台界面 注册用户的实体类
    private Long id;
    private String loginName;
    private String password;
    private String realName;
    private String idCard;
    private String phone;
    private String email;
    private Integer status;
    private Date createDate;
    private String salt;
}
