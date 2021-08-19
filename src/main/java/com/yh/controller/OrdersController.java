package com.yh.controller;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.Orders;
import com.yh.service.OrdersService;
import com.yh.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * 添加一个订单
     * @param orders
     * @return
     */
    @RequestMapping("/addOrders")
    @ResponseBody
    public String addOrders(Orders orders){
        Map<String, Object> map = new HashMap<String, Object>();


        if (ordersService.addOrders(orders) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"创建订单成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"创建订单失败");
        }

        return JSON.toJSONString(map);
    }


}
