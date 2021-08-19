package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkin implements Serializable {
    private Long id;
    private Integer roomtypeid;
    private Long roomid;
    private String customername;
    private String idcard;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leavedate;
    private BigDecimal payprice;
    private Integer status;
    private String remark;
    private Integer ordersid;
    private Integer createdby;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    private Integer modifyby;
    private Date modifydate;

    //房型对象
    private RoomType roomType;
    //房间对象
    private Room room;
}
