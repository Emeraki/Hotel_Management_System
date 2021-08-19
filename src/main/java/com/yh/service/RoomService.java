package com.yh.service;

import com.yh.pojo.Room;
import com.yh.vo.RoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomService {
    /**
     * 查询所有房间列表
     * @param roomVo
     * @return
     */
    List<Room> findRoomList(RoomVo roomVo);

    /**
     * 添加房间
     * @param room
     * @return
     */
    int addRoom(Room room);

    /**
     * 修改房间
     * @param room
     * @return
     */
    int updateRoom(Room room);

    /**
     * 删除房间
     * @param id
     * @return
     */
    int deleteRoomById(int id);

    /**
     * 根据楼层查询id
     * @param
     * @return
     */
    List<Room> findRoomByFloorId();

    /**
     * 查看房间的详情
     * @param id
     * @return
     */
    Room findById(int id);
}
