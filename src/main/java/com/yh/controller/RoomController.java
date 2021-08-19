package com.yh.controller;

import com.yh.pojo.Room;
import com.yh.pojo.RoomType;
import com.yh.service.RoomService;
import com.yh.service.RoomTypeService;
import com.yh.vo.RoomVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @Resource
    private RoomTypeService roomTypeService;

    /**
     * 到房间详情页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}.html")
    public String to_detail(@PathVariable int id, Model model) {
        //调用查询房间详情的函数
        Room room = roomService.findById(id);

        model.addAttribute("room", room);

        return "detail";
    }

    /**
     * 查询房间列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {

        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);

        //创建查询VO类
        RoomVo roomVo = new RoomVo();
        roomVo.setStatus(3);

        //查询房间列表
        List<Room> roomList = roomService.findRoomList(roomVo);


        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("roomList", roomList);

        return "hotelList";
    }

    /**
     * 查询房间列表
     *
     * @param id,model
     * @return
     */
    @RequestMapping("/list/{id}")
    public String list(@PathVariable int id,Model model) {

        //调用查询所有房型列表的方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(null);

        //创建查询条件类
        RoomVo roomVo = new RoomVo();
        roomVo.setRoomtypeid(id);
        roomVo.setStatus(3);

        //查询房间列表
        List<Room> roomList = roomService.findRoomList(roomVo);


        model.addAttribute("roomTypeList", roomTypeList);
        model.addAttribute("roomList", roomList);
        //将当前选中的房型id进行保存，
        model.addAttribute("typeId",id);

        return "hotelList";
    }

}
