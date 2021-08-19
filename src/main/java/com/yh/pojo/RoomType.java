package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    private Integer id;
    private String typename;
    private String photo;
    private BigDecimal price;
    private Integer livenum;
    private Integer bednum;
    private Integer roomnum;
    private Integer reservednum;
    private Integer avilablenum;
    private Integer livednum;
    private Integer status;
    private String remark;

}
