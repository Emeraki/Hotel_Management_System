package com.yh.service.impl;

import com.yh.dao.CheckinMapper;
import com.yh.dao.OrdersMapper;
import com.yh.dao.RoomTypeMapper;
import com.yh.pojo.Checkin;
import com.yh.pojo.RoomType;
import com.yh.service.CheckinService;
import com.yh.service.OrdersService;
import com.yh.vo.CheckinVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CheckinServiceImpl implements CheckinService {

    @Resource
    private CheckinMapper checkinMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    public List<Checkin> findCheckinList(CheckinVo checkinVo) {
        return checkinMapper.findCheckinList(checkinVo);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public int addCheckin(Checkin checkin) {

        //添加一些信息
        checkin.setStatus(1); //1-已入住
        checkin.setCreatedate(new Date());

        //添加入住信息
        int count = checkinMapper.addCheckin(checkin);

        if (count > 0){
            //修改t_order表的status列为3-已入住
            ordersMapper.checkinOrders(checkin.getOrdersid());

            //修改房型列表中的已入住数量+1
            RoomType roomType = roomTypeMapper.findById(checkin.getRoomtypeid());
            roomType.setLivednum(roomType.getLivednum() + 1);
            roomTypeMapper.updateRoomType(roomType);
        }

        return count;
    }
}
