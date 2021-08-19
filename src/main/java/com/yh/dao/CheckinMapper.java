package com.yh.dao;

import com.yh.pojo.Checkin;
import com.yh.vo.CheckinVo;

import java.util.List;

public interface CheckinMapper {

    /**
     * 获得入住列表
     * @return
     */
    List<Checkin> findCheckinList(CheckinVo checkinVo);

    /**
     * 添加一个入住
     * @param checkin
     * @return
     */
    int addCheckin(Checkin checkin);

    /**
     * 通过id更改status信息
     * @param id
     * @return
     */
    int updateStatus(Long id);
}
