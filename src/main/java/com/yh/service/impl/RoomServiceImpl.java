package com.yh.service.impl;

import com.yh.dao.RoomMapper;
import com.yh.pojo.Room;
import com.yh.service.RoomService;
import com.yh.vo.RoomVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    public List<Room> findRoomList(RoomVo roomVo) {
        return roomMapper.findRoomList(roomVo);
    }

    public int addRoom(Room room) {
        return roomMapper.addRoom(room);
    }

    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    public int deleteRoomById(int id) {
        return roomMapper.deleteRoomById(id);
    }

    public List<Room> findRoomByFloorId() {
        return roomMapper.findRoomByFloorId();
    }

    public Room findById(int id) {
        return roomMapper.findById(id);
    }
}
