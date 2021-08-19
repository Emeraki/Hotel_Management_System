package com.yh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checkout {
    private Long id;
    private String checkOutNumber;
    private Long checkInId;
    private Double consumePrice;
    private Date createDate;
    private Integer createdBy;
    private String remark;
}
