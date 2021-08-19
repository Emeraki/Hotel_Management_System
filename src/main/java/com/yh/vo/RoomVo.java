package com.yh.vo;

import com.yh.pojo.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomVo extends Room {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;
}
