package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Orders;
import com.yh.pojo.Room;
import com.yh.service.OrdersService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.OrdersVo;
import com.yh.vo.RoomVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/orders")
public class OrdersAdminController {

    @Resource
    private OrdersService ordersService;

    @RequestMapping("/list")
    public DataGridViewResult list(OrdersVo ordersVo){

        //设置分页信息
        PageHelper.startPage(ordersVo.getPage(),ordersVo.getLimit());

        //调用查询方法
        List<Orders> ordersList = ordersService.findOrdersList(ordersVo);

        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/confirmOrders")
    public String confirmOrders(int id){
        Map<String, Object> map = new HashMap<String, Object>();

        if (ordersService.confirmOrders(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "确认成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "确认失败");
        }

        return JSON.toJSONString(map);
    }
}
