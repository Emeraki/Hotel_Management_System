package com.yh.service.impl;

import com.yh.dao.OrdersMapper;
import com.yh.dao.RoomMapper;
import com.yh.dao.RoomTypeMapper;
import com.yh.pojo.Orders;
import com.yh.pojo.Room;
import com.yh.pojo.RoomType;
import com.yh.service.OrdersService;
import com.yh.service.RoomTypeService;
import com.yh.utils.UUIDUtils;
import com.yh.vo.OrdersVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    //这里加个事务，只要是出发了异常就回滚
    @Transactional(rollbackFor = RuntimeException.class)
    public int addOrders(Orders orders) {
        //设定状态为1，待确认
        orders.setStatus(1);

        //设定订单编号
        orders.setOrdersno(UUIDUtils.randomUUID());

        //设定预定时间
        orders.setReservedate(new Date());

        System.out.println("==============");
        System.out.println(orders.toString());
        System.out.println("==============");


        int count = ordersMapper.addOrders(orders);

        if (count > 0) {
            //订单添加成功
            //修改房间状态为已预定
            Room room = roomMapper.findById(orders.getRoomid());
            room.setStatus(1);
            roomMapper.updateRoom(room);

            //修改房型信息，可用房间数减一，已预订数量加一
            RoomType roomType = roomTypeMapper.findById(orders.getRoomtypeid());
            roomType.setAvilablenum(roomType.getAvilablenum() - 1);
            roomType.setReservednum(roomType.getReservednum() + 1);
            roomTypeMapper.updateRoomType(roomType);

        } else {
            //添加失败
        }


        return count;
    }

    public List<Orders> findOrdersList(OrdersVo ordersVo) {
        return ordersMapper.findOrdersList(ordersVo);
    }

    public int confirmOrders(int id) {
        return ordersMapper.confirmOrders(id);
    }

}
