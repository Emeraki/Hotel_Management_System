package com.yh.service;

import com.yh.pojo.RoomType;
import com.yh.vo.RoomTypeVo;

import java.util.List;

public interface RoomTypeService {
    /**
     * 查询所有房间类型信息
     * @param roomTypeVo
     * @return
     */
    List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);

    /**
     * 添加房型
     * @param roomType
     * @return
     */
    int addRoomType(RoomType roomType);

    /**
     * 修改房型
     * @param roomType
     * @return
     */
    int updateRoomType(RoomType roomType);

    /**
     * 根据id查询房屋类型
     * @param id
     * @return
     */
    RoomType findById(int id);
}
