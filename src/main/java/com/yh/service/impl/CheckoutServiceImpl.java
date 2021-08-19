package com.yh.service.impl;

import com.yh.dao.CheckinMapper;
import com.yh.dao.CheckoutMapper;
import com.yh.dao.OrdersMapper;
import com.yh.dao.RoomTypeMapper;
import com.yh.pojo.Checkout;
import com.yh.pojo.RoomType;
import com.yh.service.CheckoutService;
import com.yh.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    @Resource
    private CheckoutMapper checkoutMapper;

    @Resource
    private CheckinMapper checkinMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    public int addCheckout(Checkout checkout) {

        //设置创建时间
        checkout.setCreateDate(new Date());

        //设置单号
        checkout.setCheckOutNumber(UUIDUtils.randomUUID());

        //调用增加退房订单函数
        int count = checkoutMapper.addCheckout(checkout);

        if (count > 0){
            //创建成功
            //修改checkin表中对应的状态为2
            checkinMapper.updateStatus(checkout.getCheckInId());

            //修改t_orders表的状态为4（订单已完成）
            ordersMapper.checkoutOrders(checkout.getCheckInId());

            //修改t_roomtype表，可用房间+1，已入住房间-1
            RoomType roomType = roomTypeMapper.findById_2(checkout.getCheckInId());
            //已入住-1
            roomType.setLivednum(roomType.getLivednum() -1);
            //可用房间数+1
            roomType.setAvilablenum(roomType.getAvilablenum() +1);

            roomTypeMapper.updateRoomType(roomType);

        }else {
            //新增失败

        }

        return count;
    }
}
