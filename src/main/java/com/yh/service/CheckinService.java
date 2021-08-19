package com.yh.service;

import com.yh.pojo.Checkin;
import com.yh.vo.CheckinVo;

import java.util.List;

public interface CheckinService {

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
}
