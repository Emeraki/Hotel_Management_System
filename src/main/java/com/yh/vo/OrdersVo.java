package com.yh.vo;

import com.yh.pojo.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersVo extends Orders {
    //这个类主要是用来分页

    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    //下面这俩是为了在分页搜索的时候根据日期来进行模糊查找
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
}
