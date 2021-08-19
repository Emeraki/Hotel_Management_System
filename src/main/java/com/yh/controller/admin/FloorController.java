package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.Floor;
import com.yh.service.FloorService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.vo.FloorVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/floor")
public class FloorController {

    @Resource
    private FloorService floorService;

    /**
     * 查询所有楼层信息
     * @param floorVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(FloorVo floorVo){

        //设置分页信息
        PageHelper.startPage(floorVo.getPage(),floorVo.getLimit());

        //调用查询楼层列表的方法
        List<Floor> floorList = floorService.findFloorList(floorVo);

        //创建分页对象
        PageInfo<Floor> pageInfo = new PageInfo<Floor>(floorList);

        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加楼层信息
     * @param floor
     * @return
     */
    @RequestMapping("/addFloor")
    public String addFloor(Floor floor){
        Map<String, Object> map = new HashMap<String, Object>();

        if (floorService.addFloor(floor) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加楼层信息成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加楼层信息失败");
        }

        return JSON.toJSONString(map);

    }

    /**
     * 添加楼层信息
     * @param floor
     * @return
     */
    @RequestMapping("/updateFloor")
    public String updateFloor(Floor floor){
        Map<String, Object> map = new HashMap<String, Object>();

        if (floorService.updateFloor(floor) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改楼层信息成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加楼层信息失败");
        }

        return JSON.toJSONString(map);

    }

    @RequestMapping("findAll")
    public String findAll(){
        return JSON.toJSONString(floorService.findFloorList(null));
    }

}
