package com.yh.service;

import com.yh.pojo.Orders;
import com.yh.vo.OrdersVo;

import java.util.List;

public interface OrdersService {
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


}
