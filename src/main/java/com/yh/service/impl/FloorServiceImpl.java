package com.yh.service.impl;

import com.yh.dao.FloorMapper;
import com.yh.pojo.Floor;
import com.yh.service.FloorService;
import com.yh.vo.FloorVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class FloorServiceImpl implements FloorService {

    @Resource
    private FloorMapper floorMapper;

    public List<Floor> findFloorList(FloorVo floorVo) {
        return floorMapper.findFloorList(floorVo);
    }

    public int addFloor(Floor floor) {

        return floorMapper.addFloor(floor);
    }

    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }


}
