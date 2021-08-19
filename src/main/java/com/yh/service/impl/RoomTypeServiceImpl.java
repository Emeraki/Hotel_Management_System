package com.yh.service.impl;

import com.yh.dao.RoomTypeMapper;
import com.yh.pojo.RoomType;
import com.yh.service.RoomTypeService;
import com.yh.vo.RoomTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    public List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo) {
        return roomTypeMapper.findRoomTypeList(roomTypeVo);
    }

    public int addRoomType(RoomType roomType) {
        roomType.setAvilablenum(roomType.getRoomnum());

        roomType.setLivednum(0);//已入住房间数量

        return roomTypeMapper.addRoomType(roomType);
    }

    public int updateRoomType(RoomType roomType) {
        roomType.setAvilablenum(roomType.getRoomnum());

        roomType.setLivednum(0);//已入住房间数量
        return roomTypeMapper.updateRoomType(roomType);
    }

    public RoomType findById(int id) {
        return roomTypeMapper.findById(id);
    }
}
