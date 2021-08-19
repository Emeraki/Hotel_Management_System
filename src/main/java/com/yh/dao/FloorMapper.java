package com.yh.dao;

import com.yh.pojo.Floor;
import com.yh.vo.FloorVo;

import java.util.List;

public interface FloorMapper {

    /**
     * 查询所有楼层信息
     * @param floorVo
     * @return
     */
    List<Floor> findFloorList(FloorVo floorVo);

    /**
     * 添加楼层信息
     * @param floor
     * @return
     */
    int addFloor(Floor floor);

    /**
     * 编辑楼层信息
     * @param floor
     * @return
     */
    int updateFloor(Floor floor);
}
