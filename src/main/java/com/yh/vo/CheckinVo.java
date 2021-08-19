package com.yh.vo;

import com.yh.pojo.Checkin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinVo extends Checkin {

    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;
}
