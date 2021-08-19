package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Checkin;
import com.yh.pojo.Employee;
import com.yh.pojo.Orders;
import com.yh.service.CheckinService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.CheckinVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/checkin")
public class CheckinController {

    @Resource
    private CheckinService checkinService;

    @RequestMapping("/list")
    public DataGridViewResult list(CheckinVo checkinVo){

        //设置分页信息
        PageHelper.startPage(checkinVo.getPage(),checkinVo.getLimit());

        //调用查询方法
        List<Checkin> checkinList = checkinService.findCheckinList(checkinVo);

        //创建分页对象
        PageInfo<Checkin> pageInfo = new PageInfo<Checkin>(checkinList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/addCheckin")
    public String addCheckin(Checkin checkin, HttpSession session){

        Map<String, Object> map = new HashMap<String, Object>();

        //加入一些信息
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        checkin.setCreatedby(loginUser.getId());


        if (checkinService.addCheckin(checkin)> 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加失败");
        }


        return JSON.toJSONString(map);
    }

}
