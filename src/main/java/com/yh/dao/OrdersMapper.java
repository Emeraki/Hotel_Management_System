package com.yh.dao;

import com.yh.pojo.Orders;
import com.yh.vo.OrdersVo;

import java.util.List;

public interface OrdersMapper {

    /**
     * 添加订单
     * @param orders
     * @return
     */
    int addOrders(Orders orders);

    /**
     * 查询所有订单列表
     * @param ordersVo
     * @return
     */
    List<Orders> findOrdersList(OrdersVo ordersVo);

    /**
     * 根据id确认订单
     *
     * @param id
     * @return
     */
    int confirmOrders(int id);

    /**
     * 根据id 订单改为入住
     * @param id
     * @return
     */
    int checkinOrders(int id);

    /**
     * 根据id 订单改为已离店
     * @param id
     * @return
     */
    int checkoutOrders(Long id);

}
