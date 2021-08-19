package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Room;
import com.yh.pojo.RoomType;
import com.yh.service.RoomService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.RoomTypeVo;
import com.yh.vo.RoomVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/room")
public class RoomAdminController {
    @Resource
    private RoomService roomService;

    /**
     * 获得房屋类型列表
     * @param roomVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoomVo roomVo){

        //设置分页信息
        PageHelper.startPage(roomVo.getPage(),roomVo.getLimit());

        //调用查询方法
        List<Room> roomList = roomService.findRoomList(roomVo);

        //创建分页对象
        PageInfo<Room> pageInfo = new PageInfo<Room>(roomList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加房屋
     * @param room
     * @return
     */
    @RequestMapping("/addRoom")
    public String addRoom(Room room){
        Map<String, Object> map = new HashMap<String, Object>();

        if (roomService.addRoom(room) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加房间成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加房间失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 修改房间
     * @param room
     * @return
     */
    @RequestMapping("/updateRoom")
    public String updateRoom(Room room){
        Map<String, Object> map = new HashMap<String, Object>();

        if (roomService.updateRoom(room) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改房间成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改房间失败");
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping("/deleteRoomById")
    public String deleteRoomById(int id){
        Map<String, Object> map = new HashMap<String, Object>();

        if (roomService.deleteRoomById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"删除房间成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"删除房间失败");
        }

        return JSON.toJSONString(map);
    }
}
