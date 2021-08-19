package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer id;
    private String photo;
    private String roomnum;
    private Integer roomtypeid;
    private Integer floorid;
    //1-已预订 2-已入住 3-可预订
    private Integer status;
    private String statusStr;
    private String roomrequirement;
    private String remark;
    private String roomdesc;

    //额外新增的，房型名称和楼层名称
    private String typeName;
    private String floorName;

    //增加一个金额
    private Double price;

    //增加一个床位数
    private Integer bedNum;

    public String getStatusStr() {
        if (status!=null){
            switch (status){
                case 1:
                    statusStr="已预订";
                    break;
                case 2:
                    statusStr="已入住";
                    break;
                case 3:
                    statusStr="可预订";
                    break;
            }
        }
        return statusStr;
    }
}
