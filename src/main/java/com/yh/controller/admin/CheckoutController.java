package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.yh.pojo.Checkin;
import com.yh.pojo.Checkout;
import com.yh.pojo.Employee;
import com.yh.service.CheckoutService;
import com.yh.service.OrdersService;
import com.yh.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/checkout")
public class CheckoutController {

    @Resource
    private CheckoutService checkoutService;

    @Resource
    private OrdersService ordersService;

    @RequestMapping("/addCheckout")
    public String addCheckin(Checkout checkout, HttpSession session){

        Map<String, Object> map = new HashMap<String, Object>();

        //添加当前操作人
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        checkout.setCreatedBy(loginUser.getId());

        if (checkoutService.addCheckout(checkout)> 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"退房成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"退房失败");
        }


        return JSON.toJSONString(map);
    }


}
