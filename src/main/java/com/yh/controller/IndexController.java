package com.yh.controller;

import com.yh.pojo.Floor;
import com.yh.pojo.Room;
import com.yh.pojo.RoomType;
import com.yh.service.FloorService;
import com.yh.service.RoomService;
import com.yh.service.RoomTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private RoomTypeService roomTypeService;

    @Resource
    private FloorService floorService;

    @Resource
    private RoomService roomService;


    @RequestMapping({"/","/index"})
    public String index(Model model){

        //调用查询房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);

        //调用查询楼层列表方法
        List<Floor> floorList = floorService.findFloorList(null);

        //调用根据楼层查询列表方法
        List<Room> roomList = roomService.findRoomByFloorId();


        //将数据放到模型中
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("roomList",roomList);


        return "forward:/index.jsp";
    }
}
