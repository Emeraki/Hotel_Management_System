package com.yh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;//订单主键
    private String ordersno;//订单号-
    private Integer accountid;//用户id
    private Integer roomtypeid;//房型ID
    private Integer roomid;//房间ID
    private String reservationname;//预订人姓名-
    private String idcard;//身份证号码-
    private String phone;//电话-
    private Integer status;//订单状态 1-待确认 2-已确认-
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reservedate;//预订时间-
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivedate;//入住时间-
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leavedate;//离店时间-
    private Double reserveprice;//预订房价-
    private String remark;//备注 -

    //房间
    private Room room;
    //房间类型
    private RoomType roomType;
}
